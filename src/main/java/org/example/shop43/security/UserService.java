package org.example.shop43.security;

import java.util.List;

public interface UserService  {

    public List<UserResponseDto> getUsers();
    //public UserResponseDto getUserById();

    public UserResponseDto createUser(UserRequestDto dto);
    public UserResponseDto setAdminRole(String username);


}
