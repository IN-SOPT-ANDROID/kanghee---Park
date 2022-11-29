package org.sopt.sample.data.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/user/signin")
    fun signIn(@Body request: RequestSignInDTO): Call<ResponseSignInDTO>

    @POST("api/user/signup")
    fun signUp(@Body request: RequestSignUpDTO) : Call<ResponseSignUpDTO>
}
