package pl.adamnowicki.fixer.fragment.list

import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNull
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import pl.adamnowicki.fixer.data.FixerRsp
import pl.adamnowicki.fixer.manager.RatesDayManager
import pl.adamnowicki.fixer.network.FixerApi

/**
 * Created by adamnowicki on 15/09/2020.
 */
class RatesRepositoryTest {

    val successRsp = FixerRsp(
        true,
        1600180715,
        "EUR",
        "2020-09-14",
        mapOf(
            "PLN" to 4.443522,
            "BND" to 1.625389,
            "BOB" to 8.214994,
            "BRL" to 6.315217,
        )
    )

    val failRsp = FixerRsp(
        false,
        1600180715,
        "EUR",
        "2020-09-14",
        mapOf()
    )

    @Test
    fun ratesSuccess() {
        val api = mock<FixerApi> {
            onBlocking { getByDate(any()) } doReturn successRsp
        }

        val ratesDayManager = mock<RatesDayManager> {
            on { nextDay() } doReturn "2020-09-15"
        }

        val repository = RatesRepository(api, ratesDayManager)


        runBlocking {
            val rates = repository.getRates()

            assertThat(rates, IsEqual(successRsp))
            verify(ratesDayManager, times(1)).nextDay()
            verify(api, times(1)).getByDate(any())

        }

    }

    @Test
    fun ratesFail() {
        val api = mock<FixerApi> {
            onBlocking { getByDate(any()) } doReturn failRsp
        }

        val ratesDayManager = mock<RatesDayManager> {
            on { nextDay() } doReturn "2020-09-15"
        }

        val repository = RatesRepository(api, ratesDayManager)


        runBlocking {
            var rates: FixerRsp? = null
            try {
                rates = repository.getRates()
            } catch (e: Exception) {
            }

            verify(ratesDayManager, times(1)).nextDay()
            verify(api, times(1)).getByDate(any())
            assertThat(rates, IsNull())
        }

    }
}