package com.pauldaniv.template.lib.second.consume;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void createDeployment() {
        String first = "testOne, ";
        String second = "testTwo";
        User user = new User(first, second);
        assertThat(user.doTest()).isEqualTo(first + second);
    }
}
