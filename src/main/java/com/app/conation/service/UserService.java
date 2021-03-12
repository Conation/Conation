package com.app.conation.service;

import com.app.conation.domain.Region;
import com.app.conation.domain.RegionRepository;
import com.app.conation.domain.User;
import com.app.conation.domain.UserRepository;
import com.app.conation.dto.SignInRequestDto;
import com.app.conation.dto.SignUpRequestDto;
import com.app.conation.exception.*;
import com.app.conation.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RegionRepository regionRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public void userSignUp(SignUpRequestDto signUpRequestDto) {
        isPasswordsEqual(signUpRequestDto);

        isValidId(signUpRequestDto);

        User signUpUser = User.builder()
            .userId(signUpRequestDto.getId())
            .nickname(signUpRequestDto.getNickname())
            .regionId(getRegionByCityId(signUpRequestDto.getRegionId()))
            .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
            .phoneNumber(signUpRequestDto.getPhoneNumber())
            .build();

        userRepository.save(signUpUser);
    }

    private void isValidId(SignUpRequestDto signUpRequestDto) {
        Optional<User> userFound = userRepository.findByUserId(signUpRequestDto.getId());
        if (userFound.isPresent()) {
            throw new AlreadyExistIdException();
        }
    }

    private void isPasswordsEqual(SignUpRequestDto signUpRequestDto) {
        if ( !signUpRequestDto.getPassword().equals(signUpRequestDto.getPasswordRepeat())) {
            throw new PasswordsNotEqualException();
        }
    }

    private Region getRegionByCityId(Long cityId) {
        return regionRepository.findById(cityId).orElseThrow(RegionNotFoundException::new);
    }

    public String userSignIn(SignInRequestDto signInRequestDto) {
        User user = userRepository.findByUserId(signInRequestDto.getUserId()).orElseThrow(NotRegisteredIdException::new);
        isValidPassword(signInRequestDto.getPassword(), user.getPassword());
        return jwtProvider.getJWT(user, user.getRoles());
    }

    private void isValidPassword(String password, String passwordRe) {
        if (!passwordEncoder.matches(password, passwordRe)) {
            throw new InvalidPasswordException();
        }
    }
}
