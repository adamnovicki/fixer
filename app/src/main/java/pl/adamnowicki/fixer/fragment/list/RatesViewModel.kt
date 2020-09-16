package pl.adamnowicki.fixer.fragment.list

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.*
import pl.adamnowicki.fixer.R
import pl.adamnowicki.fixer.data.*
import pl.adamnowicki.fixer.exception.EmptyRatesException
import pl.adamnowicki.fixer.exception.FixerException
import pl.adamnowicki.fixer.exception.NetworkException
import timber.log.Timber
import java.lang.Exception

@ActivityScoped
class RatesViewModel @ViewModelInject constructor(
    private val useCase: RatesUseCase,
    @ApplicationContext private val context: Context,
    private val contextProvider: CoroutinesContextProvider
) : ViewModel() {

    var fixerLiveData = MutableLiveData<MutableList<FixerAdapterData>>()
    var errorLiveData = MutableLiveData<Boolean>()

    fun getNextDay() {
        viewModelScope.launch(contextProvider.IO) {
            try {
                val fixerData = useCase.getRates()
                val listAdapterData = mutableListOf<FixerAdapterData>()

                fixerData.forEach {
                    listAdapterData.add(
                        HeaderAdapterData(
                            context.getString(R.string.day_header, it.date)
                        )
                    )

                    it.rates.forEach { rate ->
                        listAdapterData.add(
                            ItemAdapterData(
                                rate.currency,
                                rate.value,
                                it.date
                            )
                        )
                    }
                }

                fixerLiveData.postValue(listAdapterData)
            } catch (e: Exception) {
                Timber.e(e, "ex rates")
                errorLiveData.postValue(true)
            }
        }
    }
}