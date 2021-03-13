package com.app.conation.service;

import com.app.conation.domain.User;
import com.app.conation.repository.UserRepository;
import com.app.conation.domain.draw.RandomPrize;
import com.app.conation.response.dto.DrawRes;
import com.app.conation.advice.exceptions.NotEnoughPointException;
import com.app.conation.advice.exceptions.NotExistUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RandomDrawService {

    private static final long COST_FOR_DRAW = 60000;

    private final UserRepository userRepository;
    private final RandomPrize randomPrize;

    public DrawRes randomDraw(Object principal) {
        User user = (User) principal;
        String drawResult = randomPrize.draw(user);
        User userInDatabase = userRepository.findById(user.getId()).orElseThrow(NotExistUserException::new);
        isEnoughPoint(userInDatabase);
        long remainPoint = userInDatabase.getExperiencePoint() - COST_FOR_DRAW;
        userRepository.updateExperiencePoint(remainPoint, user.getId());
        return DrawRes.builder()
            .drawResult(drawResult)
            .remainPoint(remainPoint)
            .build();
    }

    private void isEnoughPoint(User userInDatabase) {
        if (userInDatabase.getExperiencePoint() < 60000) {
            throw new NotEnoughPointException();
        }
    }
}
