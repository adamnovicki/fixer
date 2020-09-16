package pl.adamnowicki.fixer.manager

import pl.adamnowicki.fixer.utils.DateUtils
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by adamnowicki on 15/09/2020.
 */
@Singleton
class RatesDayManager @Inject constructor(private val dateUtils: DateUtils) {

    var daysBefore = -1

    fun nextDay() = dateUtils daysBefore ++daysBefore
}