package org.sopt.sample.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface UserDataService {
    @GET("api/users?page=2")
    fun userData() : Call<ResponseUserDTO>
}