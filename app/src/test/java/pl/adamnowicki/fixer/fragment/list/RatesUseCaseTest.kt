package pl.adamnowicki.fixer.fragment.list

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNull
import org.junit.Test

import org.junit.Assert.*
import pl.adamnowicki.fixer.data.FixerData
import pl.adamnowicki.fixer.data.FixerItem
import pl.adamnowicki.fixer.data.FixerRsp

/**
 * Created by adamnowicki on 15/09/2020.
 */
class RatesUseCaseTest {

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

    @Test
    fun getRates() {

        val repository = mock<RatesRepository> {
            onBlocking { getRates() } doReturn successRsp
        }

        val expectData = mutableListOf<FixerData>()
        expectData.add(
            FixerData(
                "2020-09-14", listOf(
                    FixerItem("PLN", "4,4435"),
                    FixerItem("BND", "1,6254"),
                    FixerItem("BOB", "8,2150"),
                    FixerItem("BRL", "6,3152")
                )
            )
        )

        val useCase = RatesUseCase(repository)

        runBlocking {
            val data = useCase.getRates()

            MatcherAssert.assertThat(data, IsEqual(expectData))
        }
    }
}