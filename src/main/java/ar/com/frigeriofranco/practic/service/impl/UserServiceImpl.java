package ar.com.frigeriofranco.practic.service.impl;

import ar.com.frigeriofranco.practic.dto.UserRequestDto;
import ar.com.frigeriofranco.practic.dto.UserResponseDto;
import ar.com.frigeriofranco.practic.dto.UserToUpdateDto;
import ar.com.frigeriofranco.practic.exceptions.RolesNotFoundException;
import ar.com.frigeriofranco.practic.model.Role;
import ar.com.frigeriofranco.practic.model.User;
import ar.com.frigeriofranco.practic.repository.RoleRepository;
import ar.com.frigeriofranco.practic.repository.UserRepository;
import ar.com.frigeriofranco.practic.service.UserService;
import ar.com.frigeriofranco.practic.util.UtilJWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

import static java.util.Arrays.stream;


@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UtilJWT utilJWT;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
            if(user == null){
                log.error("username not found in the database");
                throw new UsernameNotFoundException("username not found in the database");
            }else{
                log.error("username found in the database: {}", username);
            }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }


    @Override
    public User saveUser(UserRequestDto userDto){
        log.info("Saving user to the database");
        if(userRepository.findByUsername(userDto.getUsername())==null){
            Set<Role> roles = new HashSet<>();
            userDto.getRoles().forEach(e-> {
                Role role = roleRepository.findByDescription(e);
                    if(role== null){
                        throw new RolesNotFoundException("Role "+ e +" not found ");
                    }else{
                        roles.add(role);
                    }
            });
            User userToSave = mapper.map(userDto,User.class);
            userToSave.setRoles(roles);
            userToSave.setPassword(bCryptPasswordEncoder.encode(userToSave.getPassword()));
            return userRepository.save(userToSave);
        }else{
            throw new RuntimeException("user alredy exist");
        }

    }


    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching username {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserResponseDto> getUsers() {
        log.info("Fetching all users");
        List<UserResponseDto> usersDto = new ArrayList<>();
        userRepository.findAll().forEach(user -> usersDto.add(mapper.map(user,UserResponseDto.class)));
        return usersDto;
    }

    @Override
    public Map<String,Object> deleteById(Long id) {
        userRepository.deleteById(id);
        Map<String,Object> body = new HashMap<>();
            body.put("message","Element was successfully deleted");
            body.put("date", new Date());

        return body;
    }

    @Override
    public UserResponseDto updateUser(UserToUpdateDto user, String authorizationHeader,Long id) {
        DecodedJWT decodedJWT = utilJWT.verifyInfo(authorizationHeader);
        List<String> rolEs = Arrays.asList(decodedJWT.getClaim("roles").asArray(String.class));
        //String usernameToken =;
            if(user.getUsername().equalsIgnoreCase(decodedJWT.getSubject())||rolEs.contains("ROLE_BOSS")){
                User userToUpdate = userRepository.findById(id).orElseThrow();
                userToUpdate.setUsername(user.getUsername());
                userToUpdate.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                userToUpdate.setName(user.getName());
                userToUpdate.setSurname(user.getSurname());
                User resp = userRepository.save(userToUpdate);
                }else{
                    log.info("solo los jefes pueden modificar datos de otros usuarios");
                }
            log.info("username from token -->" );
        return null;
    }

}
