package org.sopt.sample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.ResponseUserDTO
import org.sopt.sample.data.remote.UserDataService
import org.sopt.sample.data.remote.UserServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    private val _users: MutableLiveData<List<ResponseUserDTO.UserData>> = MutableLiveData()
    val users: LiveData<List<ResponseUserDTO.UserData>> get() = _users
    private val userService: UserDataService = UserServicePool.userDataService

    init{
        loadUsers()
    }

    fun loadUsers(){
        userService.userData().enqueue(object : Callback<ResponseUserDTO> {
            override fun onResponse(
                call: Call<ResponseUserDTO>,
                response: Response<ResponseUserDTO>
            ) {
                if (response.isSuccessful){
                        _users.value = response.body()?.data
                }
            }

            override fun onFailure(call: Call<ResponseUserDTO>, t: Throwable) {
                Log.e("stellar", "fail")
            }
        })
    }
}