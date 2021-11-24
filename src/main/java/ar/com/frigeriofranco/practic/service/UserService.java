package ar.com.frigeriofranco.practic.service;

import ar.com.frigeriofranco.practic.dto.UserRequestDto;
import ar.com.frigeriofranco.practic.dto.UserResponseDto;
import ar.com.frigeriofranco.practic.dto.UserToUpdateDto;
import ar.com.frigeriofranco.practic.model.Role;
import ar.com.frigeriofranco.practic.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User saveUser(UserRequestDto user);

    Role saveRole(Role role);

    User getUser(String username);

    List<UserResponseDto> getUsers();

    Map<String,Object> deleteById(Long id);

    UserResponseDto updateUser(UserToUpdateDto user, String token,Long id);

}
