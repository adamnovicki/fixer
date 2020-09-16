package pl.adamnowicki.fixer.data

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by adamnowicki on 2020-04-07.
 */
open class CoroutinesContextProvider @Inject constructor() {
    open val Main: CoroutineContext by lazy { Dispatchers.Main }
    open val IO: CoroutineContext by lazy { Dispatchers.IO }
}