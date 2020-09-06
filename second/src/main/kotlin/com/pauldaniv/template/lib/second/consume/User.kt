package com.pauldaniv.template.lib.second.consume

import com.pauldaniv.template.lib.first.util.TestUtil

data class User(val first: String, val second: String) {
  fun doTest(): String {
    return TestUtil.join(first, second)
  }
}
