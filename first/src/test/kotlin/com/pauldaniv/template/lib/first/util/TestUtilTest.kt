package com.pauldaniv.template.lib.first.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TestUtilTest {
    //todo add loging
  @Test
  fun join() {
    assertThat(TestUtil.join("one", "two")).isEqualTo("onetwo")
  }
}
