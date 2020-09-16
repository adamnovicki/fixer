package pl.adamnowicki.fixer.fragment.list

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import pl.adamnowicki.fixer.data.*
import pl.ing.mojeing.coroutines.TestContextProvider
import pl.ing.mojeing.coroutines.TestCoroutineRule

/**
 * Created by adamnowicki on 16/09/2020.
 */
@ExperimentalCoroutinesApi
class RatesViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    val ratesData = mutableListOf<FixerData>()

    val context = mock<Context> {
        on { getString(any(), any()) } doReturn "2020-09-14"
    }

    val fixerDataObserver: Observer<MutableList<FixerAdapterData>> = mock()
    val errorDataObserver: Observer<Boolean> = mock()

    @Before
    fun setUp() {

        if (ratesData.isEmpty()) {
            ratesData.add(
                FixerData(
                    "2020-09-14", listOf(
                        FixerItem("PLN", "4,4435"),
                        FixerItem("BND", "1,6254"),
                        FixerItem("BOB", "8,2150"),
                        FixerItem("BRL", "6,3152")
                    )
                )
            )
        }
    }

    @Test
    fun getNextDay() {

        val useCase = mock<RatesUseCase> {
            onBlocking { getRates() } doReturn ratesData
        }

        val viewModel = RatesViewModel(
            useCase,
            context,
            TestContextProvider()
        ).apply {
            fixerLiveData.observeForever(fixerDataObserver)
            errorLiveData.observeForever(errorDataObserver)
        }

        val expectData = mutableListOf<FixerAdapterData>(
            HeaderAdapterData("2020-09-14"),
            ItemAdapterData("PLN", "4,4435", "2020-09-14"),
            ItemAdapterData("BND", "1,6254", "2020-09-14"),
            ItemAdapterData("BOB", "8,2150", "2020-09-14"),
            ItemAdapterData("BRL", "6,3152", "2020-09-14"),
        )

        testCoroutineRule.runBlockingTest {
            viewModel.getNextDay()
            verify(fixerDataObserver).onChanged(expectData)
        }
    }
}