package pl.adamnowicki.fixer.data

/**
 * Created by adamnowicki on 14/09/2020.
 */
data class FixerRsp (
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)