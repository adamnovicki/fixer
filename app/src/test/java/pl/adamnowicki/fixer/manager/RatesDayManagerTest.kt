package pl.adamnowicki.fixer.manager

import com.nhaarman.mockitokotlin2.*
import org.hamcrest.core.IsEqual
import org.junit.Test

import org.junit.Assert.*
import pl.adamnowicki.fixer.utils.DateUtils

/**
 * Created by adamnowicki on 15/09/2020.
 */
class RatesDayManagerTest {

    val dateUtils = mock<DateUtils> {
        on { daysBefore(any()) } doReturn "2020-09-15"
    }

    @Test
    fun nextDay() {
        val dayManager = RatesDayManager(dateUtils)
        dayManager.nextDay()
        assertThat(dayManager.daysBefore, IsEqual(0))
        verify(dateUtils, times(1)).daysBefore(any())
    }
}