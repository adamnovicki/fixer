package pl.adamnowicki.fixer.data

/**
 * Created by adamnowicki on 15/09/2020.
 */

sealed class FixerAdapterData

data class ItemAdapterData(
    val currency: String,
    val value: String,
    val date: String
) :
    FixerAdapterData()

data class HeaderAdapterData(
    val date: String
) : FixerAdapterData()

data class LoadingAdapterData(val loading: Boolean) : FixerAdapterData()
