package com.be24.api.user;

import com.be24.api.user.model.UserLoginDto;
import com.be24.api.user.model.UserLoginDtoRes;
import com.be24.api.user.model.UserSignupDtoRes;
import com.be24.api.user.model.UserSignupDto;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserSignupDtoRes signup(UserSignupDto dto) {
        UserSignupDtoRes returnDto = userRepository.save(dto);
        return returnDto;
    }

    public UserLoginDtoRes login(UserLoginDto dto) {
        UserLoginDtoRes returnDto = userRepository.findByEmailAndPasssword(dto);

        return returnDto;
    }
}
