package com.app.conation.domain.draw;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

class TodayPrizeTest {

    @DisplayName("날짜에 맞는 enum 빼오는지 테스트")
    @Test
    void getDayPrize() {
        // given
        Calendar calendar = Calendar.getInstance();
        DayPrice dayPrice = RandomPrize.getDayPrice();

        // when
        List<Integer> collect = Arrays.stream(DayPrice.values())
            .filter(day -> calendar.get(Calendar.DAY_OF_WEEK) == day.getDayOfWeek())
            .map(DayPrice::getDayOfWeek)
            .collect(Collectors.toList());
        boolean containWeekDay = collect.contains(calendar.get(Calendar.DAY_OF_WEEK));

        // then
        Assertions.assertThat(containWeekDay).isTrue();
    }
}
