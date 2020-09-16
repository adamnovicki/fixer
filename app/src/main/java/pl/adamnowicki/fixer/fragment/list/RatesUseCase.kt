package pl.adamnowicki.fixer.fragment.list

import pl.adamnowicki.fixer.data.FixerItem
import pl.adamnowicki.fixer.data.FixerData
import pl.adamnowicki.fixer.exception.EmptyRatesException
import pl.adamnowicki.fixer.exception.FixerException
import pl.adamnowicki.fixer.exception.NetworkException
import pl.adamnowicki.fixer.ext.format
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by adamnowicki on 15/09/2020.
 */
@Singleton
class RatesUseCase @Inject constructor(private val repository: RatesRepository) {

    private val ROUND_DIGITS = 4

    private val fixerList = mutableListOf<FixerData>()

    suspend fun getRates(): List<FixerData> {

        val rsp = repository.getRates()

        val list = mutableListOf<FixerItem>()
        rsp.rates.forEach {
            list.add(FixerItem(it.key, it.value.format(ROUND_DIGITS)))
        }

        fixerList.add(FixerData(rsp.date, list))

        return fixerList
    }
}