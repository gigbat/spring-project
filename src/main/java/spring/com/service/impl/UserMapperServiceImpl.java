package spring.com.service.impl;

import org.springframework.stereotype.Service;
import spring.com.dto.UserResponseDto;
import spring.com.model.User;

@Service
public class UserMapperServiceImpl {
    public static UserResponseDto dtoBoxing(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setSurname(user.getSurname());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }

    public static User dtoUnboxing(UserResponseDto userResponseDto) {
        User user = new User();
        user.setName(userResponseDto.getName());
        user.setSurname(userResponseDto.getSurname());
        user.setEmail(userResponseDto.getEmail());
        return user;
    }
}
