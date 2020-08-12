package com.pauldaniv.template.lib.first.util;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestUtilTest {
    @Test
    public void join() {
        assertThat(TestUtil.join("one", "two")).isEqualTo("onetwo");
        log.info("Ok");
    }
}
