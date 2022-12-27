package org.sopt.sample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.AuthService
import org.sopt.sample.data.remote.RequestSignUpDTO
import org.sopt.sample.data.remote.ResponseSignUpDTO
import org.sopt.sample.data.remote.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {
    private val _signUpData: MutableLiveData<ResponseSignUpDTO> = MutableLiveData()
    val signUpData: LiveData<ResponseSignUpDTO> get() = _signUpData
    private val signUpService: AuthService = ServicePool.authService

    val idText = MutableLiveData<String>("")
    val pwText = MutableLiveData<String>("")
    val nameText = MutableLiveData<String>("")

    private val idPattern = "^[a-z|A-Z|0-9]{6,10}$"
    private val pwPattern = "^[a-z|A-Z|0-9|[@#$%&*?!]]{6,10}$"
    private val namePattern = "^..*$"

    private val idCheck: Pattern = Pattern.compile(idPattern)
    private val pwCheck: Pattern = Pattern.compile(pwPattern)
    private val nameCheck: Pattern = Pattern.compile(namePattern)

    private val _btnEnabled = MutableLiveData<Boolean>()
    val btnEnabled: LiveData<Boolean> = _btnEnabled

    fun signUp() {
        signUpService.signUp(
            RequestSignUpDTO(
                idText.value!!, pwText.value!!, nameText.value!!
            )
        ).enqueue(object : Callback<ResponseSignUpDTO> {
            override fun onResponse(
                call: Call<ResponseSignUpDTO>, response: Response<ResponseSignUpDTO>
            ) {
                if (response.isSuccessful) {
                    _signUpData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDTO>, t: Throwable) {
                Log.e("stellar", "fail")
            }
        })
    }

    fun btnEnabledChecker() {
        _btnEnabled.value =
            idCheck.matcher(idText.value).find() && pwCheck.matcher(pwText.value)
                .find() && nameCheck.matcher(nameText.value).find()
//        Log.e(
//            "kang",
//            "${idText.value}, ${
//                idCheck.matcher(idText.value).find()
//            } ,${pwText.value}, ${
//                pwCheck.matcher(pwText.value).find()
//            }, ${nameText.value}, ${
//                nameCheck.matcher(nameText.value).find()
//            },btn ${btnEnabled.value}"
//        )
    }

    fun idWarningChecker(): Boolean {
        return idCheck.matcher(idText.value).find() || (idText.value == "")
    }

    fun pwWarningChecker(): Boolean {
        return pwCheck.matcher(pwText.value).find() || (pwText.value == "")
    }

    fun nameWarningChecker(): Boolean {
        return nameCheck.matcher(nameText.value).find() || (nameText.value == "")
    }


}
