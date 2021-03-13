package com.app.conation.service;

import com.app.conation.domain.*;
import com.app.conation.domain.draw.RandomPrize;
import com.app.conation.repository.RegionRepository;
import com.app.conation.repository.UserRepository;
import com.app.conation.response.dto.MyScoreRes;
import com.app.conation.requestdto.SignInReq;
import com.app.conation.requestdto.SignUpReq;
import com.app.conation.advice.exceptions.*;
import com.app.conation.jwt.JwtProvider;
import com.app.conation.util.StringUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RegionRepository regionRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public void userSignUp(SignUpReq signUpReq) {
        isPasswordsEqual(signUpReq);
        isValidId(signUpReq);

        User signUpUser = User.builder()
            .userId(signUpReq.getId())
            .nickname(signUpReq.getNickname())
            .regionId(getRegionByCityId(signUpReq.getRegionId()))
            .password(passwordEncoder.encode(signUpReq.getPassword()))
            .phoneNumber(StringUtility.subHyphen(signUpReq.getPhoneNumber()))
            .experiencePoint(0L)
            .state(State.ACTIVE)
            .roles(Collections.singletonList("ROLE_USER"))
            .build();

        userRepository.save(signUpUser);
    }

    private void isValidId(SignUpReq signUpReq) {
        Optional<User> userFound = userRepository.findByUserId(signUpReq.getId());
        if (userFound.isPresent()) {
            throw new AlreadyExistIdException();
        }
    }

    private void isPasswordsEqual(SignUpReq signUpReq) {
        if ( !signUpReq.getPassword().equals(signUpReq.getPasswordRepeat())) {
            throw new PasswordsNotEqualException();
        }
    }

    private Region getRegionByCityId(Long cityId) {
        return regionRepository.findById(cityId).orElseThrow(RegionNotFoundException::new);
    }

    public String userSignIn(SignInReq signInReq) {
        User user = userRepository.findByUserId(signInReq.getUserId()).orElseThrow(NotRegisteredIdException::new);
        isValidPassword(signInReq.getPassword(), user.getPassword());
        return jwtProvider.getJWT(user, user.getRoles());
    }

    private void isValidPassword(String password, String passwordRe) {

        if (!passwordEncoder.matches(password, passwordRe)) {
            throw new InvalidPasswordException();
        }
    }

    public void deleteUser(Object principal) {
        Long userIdx = ((User) principal).getId();
        userRepository.deleteById(userIdx);
    }

    public MyScoreRes getMyPrice(Object principal) {
        Long userIdx = ((User) principal).getId();
        User selectedUser = userRepository.findById(userIdx).orElseThrow(NotExistUserException::new);
        return MyScoreRes.builder()
            .id(selectedUser.getId())
            .nickname(selectedUser.getNickname())
            .myRegion(selectedUser.getRegionId().getRegionName())
            .point(selectedUser.getExperiencePoint())
            .todayPrize(RandomPrize.getDayPrice().getPrize())
            .prizeWinRate(RandomPrize.getDayPrice().getWinningRate())
            .build();
    }
}
