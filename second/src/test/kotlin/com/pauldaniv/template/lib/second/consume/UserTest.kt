package com.pauldaniv.template.lib.second.consume

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserTest {

  @Test
  fun createDeployment() {
    val first = "testOne, "
    val second = "testTwo"
    val user = User(first, second)
    assertThat(user.doTest()).isEqualTo(first + second)
  }
}
