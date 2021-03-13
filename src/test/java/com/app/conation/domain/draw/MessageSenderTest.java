package com.app.conation.domain.draw;

import com.app.conation.domain.User;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONObject;
import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MessageSenderTest {

    private final MessageSender messageSender = new MessageSender();

    @DisplayName("메세지 보내기 테스트")
    @Test
    @Ignore
    void messageTest() {
        // given
        User user = User.builder()
            .id(1L)
            .nickname("tomas")
            .phoneNumber("01045692804")
            .password("123")
            .experiencePoint(10000L)
            .build();
        UserMessageParameters userMessageParameters = messageSender.userInformationSettingToPrize(user, DayPrice.FRI);

        // when
        JSONObject jsonObject = messageSender.sendSMS(userMessageParameters);

        // then
        Assertions.assertThat(jsonObject).isNotNull();
    }
}