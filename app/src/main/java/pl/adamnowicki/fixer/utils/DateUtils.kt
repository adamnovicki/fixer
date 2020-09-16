package pl.adamnowicki.fixer.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by adamnowicki on 15/09/2020.
 */
@Singleton
class DateUtils @Inject constructor() {
    private val FIXER_DAY_FORMAT = "yyyy-MM-dd"

    infix fun daysBefore(days: Int): String = DateTime().minusDays(days).toString(fixerFormatter())

    private fun fixerFormatter() = DateTimeFormat.forPattern(FIXER_DAY_FORMAT)
        .withLocale(Locale.getDefault())
}