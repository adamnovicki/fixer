package pl.ing.mojeing.coroutines

import kotlinx.coroutines.Dispatchers
import pl.adamnowicki.fixer.data.CoroutinesContextProvider
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by adamnowicki on 2020-04-07.
 */
class TestContextProvider @Inject constructor() : CoroutinesContextProvider() {
    override val Main: CoroutineContext by lazy { Dispatchers.Unconfined }
    override val IO: CoroutineContext by lazy { Dispatchers.Unconfined }
}