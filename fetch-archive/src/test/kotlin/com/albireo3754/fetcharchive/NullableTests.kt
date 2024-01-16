package com.albireo3754.fetcharchive

import net.bytebuddy.asm.Advice
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class Schedule() {
    fun date(): Number {
        return 1
    }
}

class NullableTests {

    @Test
    fun elvis_operator_tests() {
        var a = 1
        val test_lambda: () -> Schedule? = {
            a += 1
            Schedule()
        }

        val test_expression: () -> Int = {
            a += 1
            3
        }

        var b = test_lambda()?.date() ?: test_expression()
        assertEquals(a, 2)
        assertEquals(b, 1)

        var c = test_lambda()?.date() ?: test_expression()
        assertEquals(a, 3)
        assertEquals(c, 1)
    }
}