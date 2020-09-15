package pl.adamnowicki.fixer.list

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.adamnowicki.fixer.R
import pl.adamnowicki.fixer.data.*
import pl.adamnowicki.fixer.utils.DateUtils

@ActivityScoped
class ListViewModel @ViewModelInject constructor(
    private val useCase: ListUseCase,
    @ApplicationContext private val context: Context,
) : ViewModel() {

    var fixerLiveData = MutableLiveData<MutableList<FixerAdapterData>>()

    fun getNextDay() {
        viewModelScope.launch(Dispatchers.IO) {
            val fixerData = useCase.getRates()

            val listAdapterData = mutableListOf<FixerAdapterData>()

            fixerData.forEach {
                listAdapterData.add(
                    HeaderAdapterData(
                        context.getString(R.string.day_header, it.date),
                        FixerListRowType.FIXER_TYPE_HEADER
                    )
                )

                it.rates.forEach {rate ->
                    listAdapterData.add(
                        ItemAdapterData(
                            rate.currency,
                            rate.value,
                            it.date,
                            FixerListRowType.FIXER_TYPE_ITEM
                        )
                    )
                }
            }

            fixerLiveData.postValue(listAdapterData)
        }
    }
}