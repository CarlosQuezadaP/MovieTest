package com.merqueo.co.data.resource

import com.merqueo.co.CORE.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response

open class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {

                val result = apiCall.invoke()
                if (result.isSuccessful) {
                    Resource.Success(apiCall.invoke().body()!!)
                } else {

                    val message = JSONObject(
                        result.errorBody()!!.string().toString()
                    ).getString("status_message")

                    Resource.Failure(false, result.code(), message)
                }
            } catch (throwable: Exception) {
                Resource.Failure(
                    true,
                    null,
                    null,
                )
            }
        }
    }

}