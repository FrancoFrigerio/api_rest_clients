package ar.com.frigeriofranco.practic.controller;


import ar.com.frigeriofranco.practic.dto.UserRequestDto;
import ar.com.frigeriofranco.practic.dto.UserResponseDto;
import ar.com.frigeriofranco.practic.dto.UserToUpdateDto;
import ar.com.frigeriofranco.practic.model.Role;
import ar.com.frigeriofranco.practic.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    ModelMapper mapper;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_BOSS')")
    public ResponseEntity<UserResponseDto> saveUser(@Valid @RequestBody UserRequestDto userRequestDto)throws Exception{
        return new ResponseEntity<>(mapper.map(userService.saveUser(userRequestDto),UserResponseDto.class),CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_BOSS')")
    public ResponseEntity<?>deleteUser(@PathVariable("id")Long id){
        return ResponseEntity.ok(userService.deleteById(id));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ROLE_BOSS','ROLE_ADMIN')")
    public ResponseEntity<?>updateUser(@RequestBody @Valid UserToUpdateDto userToUpdateDto, HttpServletRequest request,@PathVariable Long id){
        return ResponseEntity.ok(userService.updateUser(userToUpdateDto,request.getHeader(AUTHORIZATION),id));
    }

    @PostMapping("/role")
    @PreAuthorize("hasRole('ROLE_BOSS')")
    public ResponseEntity<?>saveRole(@RequestBody Role role){
        return new ResponseEntity<>(userService.saveRole(role),CREATED);
    }

}
