package com.app.conation.domain.draw;

import com.app.conation.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class RandomPrizeTest {

    @Autowired
    private RandomPrize randomPrize;

    @DisplayName("뽑기 테스트")
    @Test
    void drawPrize() {
        // given
        User user = User.builder()
            .id(1L)
            .nickname("tomas")
            .phoneNumber("01045692804")
            .password("123")
            .experiencePoint(10000L)
            .build();

        // when
        String draw = randomPrize.draw(user);
        List<String> collect = Arrays.stream(RandomPrize.WinningStatus.values())
            .filter(result -> result.getStatus().equals(draw))
            .map(RandomPrize.WinningStatus::getStatus)
            .collect(Collectors.toList());
        Boolean resultStatus = collect.contains(draw);

        // then
        Assertions.assertThat(resultStatus).isTrue();
    }
}