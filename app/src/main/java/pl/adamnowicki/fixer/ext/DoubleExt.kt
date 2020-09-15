package pl.adamnowicki.fixer.ext

/**
 * Created by adamnowicki on 15/09/2020.
 */
fun Double.format(digits: Int) = "%.${digits}f".format(this)