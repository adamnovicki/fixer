package pl.adamnowicki.fixer.network

import pl.adamnowicki.fixer.data.FixerRsp
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by adamnowicki on 14/09/2020.
 */
interface FixerApi {
    companion object {
        const val ACCESS_KEY = "81a430ac33c26bffaa6c35082e16b7a1"
    }

    @POST("api/{date}?access_key=$ACCESS_KEY")
    suspend fun getByDate(@Path("date") date: String): FixerRsp
}