package pl.adamnowicki.fixer.fragment.list

import pl.adamnowicki.fixer.data.FixerRsp
import pl.adamnowicki.fixer.exception.EmptyRatesException
import pl.adamnowicki.fixer.exception.FixerException
import pl.adamnowicki.fixer.exception.NetworkException
import pl.adamnowicki.fixer.manager.RatesDayManager
import pl.adamnowicki.fixer.network.FixerApi
import pl.adamnowicki.fixer.utils.DateUtils
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RatesRepository @Inject constructor(
    private val fixerApi: FixerApi,
    private val ratesDayManager: RatesDayManager
) {

    @Throws(FixerException::class, NetworkException::class, EmptyRatesException::class)
    suspend fun getRates(): FixerRsp {
        try {
            val rsp = fixerApi.getByDate(ratesDayManager.nextDay())
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
}
