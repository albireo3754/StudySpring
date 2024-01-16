package com.albireo3754.fetcharchive

import com.albireo3754.fetcharchive.millenium.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.IOException
import kotlin.jvm.Throws

class CollectionTests {
    @Test
    fun whenExcludeItems_thenRemoved () {
        val firstList = listOf("one", "two", "three")
        val secondList = listOf("one", "three")
        val resultList = firstList - secondList

        assertEquals(1, resultList.size)
        assertTrue(resultList.contains("two"))
    }
    @Test
    fun whenExcludeItems_which_has_different_order_thenRemoved () {
        val firstList = listOf("one", "two", "three")
        val secondList = listOf("three", "one")
        val resultList = firstList - secondList

        assertEquals(1, resultList.size)
        assertTrue(resultList.contains("two"))
    }

    @Test
    fun whenExludeItems_which_is_class_then_Removed() {
        val firstList = listOf(Mika(), Seiya(), Himari())
        val secondList = listOf(Mika(), Himari())
        val resultList = firstList - secondList

        assertEquals(3, resultList.size)
        assertEquals(firstList, resultList)
    }
    @Test
    fun whenExludeItems_which_is_class_reference_then_Removed() {
        val mika = Mika()
        val seiya = Seiya()
        val himari = Himari()

        val firstList = listOf(mika, seiya, himari)
        val secondList = listOf(mika, himari)
        val resultList = firstList - secondList

        assertEquals(1, resultList.size)
        assertTrue(resultList.contains(seiya))
    }

    @Test
    fun whenExcludeItems_whichIsDataClass_thenRemoved() {
        val firstList = listOf(NagisaDTO("nagisa"), NagisaDTO("nagisa2"), NagisaDTO("nagisa3"))
        val secondList = listOf(NagisaDTO("nagisa"), NagisaDTO("nagisa3"))
        val resultList = firstList - secondList

        assertEquals(1, resultList.size)
        assertTrue(resultList.contains(NagisaDTO("nagisa2")))
    }
    @Test
    fun equals_test() {
        assertEquals(NagisaDTO("nagisa"), NagisaDTO("nagisa"))
        assertNotEquals(NagisaDTO("nagisa"), KoharuDTO("nagisa"))
    }
    @Test
    fun equals_test2() {
        val mika = Mika()
        val mika2 = Mika()
        assertEquals(AzusaDTO("azusa", enemy = mika), AzusaDTO("azusa", enemy = mika))
        assertNotEquals(AzusaDTO("azusa", enemy = mika), AzusaDTO("azusa", enemy = mika2))
    }


    fun new_exeption() {
        throw IOException()
    }

    @Throws(IOException::class)
    fun new_runtimeException() {
        throw IOException()
    }
    @Test
    fun checked_exception() {
        try {
//            new_exeption()
            new_runtimeException()
        } catch (error: IOException) {
            println(error)
        }
    }

    @Test
    fun whenFilterNullValues_thenSuccess () {
        val theList = listOf("one", null, "two", null, "three")
        val resultList = theList.filterNotNull() // compactMap

        assertEquals(3, resultList.size)
    }
    @Test
    fun whenApplyingPartialWindowedWithTwoSteps_thenShouldCreateSlidingWindowsOfElements() {
        // [1, 2, 3, 4, 5, 6]
        // [1, 2, 3] [3, 4, 5] [5, 6]
        val theList = (1..6).toList()
        val windowed = theList.windowed(size = 3, step = 2, partialWindows = true)

        assertThat(windowed.size).isEqualTo(3)
        assertThat(windowed.first()).contains(1, 2, 3)
        assertThat(windowed[1]).contains(3, 4, 5)
        assertThat(windowed.last()).contains(5, 6)
    }
}