package spring.com.service.impl;

import org.springframework.stereotype.Component;
import spring.com.dto.UserResponseDto;
import spring.com.model.User;

@Component
public class UserMapper {
    public UserResponseDto dtoBoxing(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setSurname(user.getSurname());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
