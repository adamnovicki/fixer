package pl.adamnowicki.fixer.list

import pl.adamnowicki.fixer.data.FixerItem
import pl.adamnowicki.fixer.data.FixerRsp
import pl.adamnowicki.fixer.exception.EmptyRatesException
import pl.adamnowicki.fixer.exception.FixerException
import pl.adamnowicki.fixer.exception.NetworkException
import pl.adamnowicki.fixer.network.FixerApi
import pl.adamnowicki.fixer.utils.DateUtils
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListRepository @Inject constructor(
    private val fixerApi: FixerApi,
    private val dateUtils: DateUtils
) {

    var daysBefore = -1

    @Throws(FixerException::class, NetworkException::class)
    suspend fun getRates(): FixerRsp {
        try {
            val rsp = fixerApi.getByDate(nextDay())
            if (!rsp.success) {
                throw FixerException()
            }
            if (rsp.rates.isEmpty()) {
                throw EmptyRatesException()
            }
            return rsp

        } catch (e: Exception) {
            throw NetworkException()
        }
    }

    private fun nextDay(): String {
        daysBefore++
        return dateUtils.minusDays(daysBefore)
    }
}
