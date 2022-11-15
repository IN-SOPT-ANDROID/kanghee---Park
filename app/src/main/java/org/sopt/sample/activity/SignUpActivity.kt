package org.sopt.sample.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.data.remote.*
import org.sopt.sample.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val signUpService: AuthService = ServicePool.authService

        var id = binding.etId.text.toString()
        var pw = binding.etPassword.text.toString()
        var name = binding.etName.text.toString()
        binding.btnSignup.isEnabled = false

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                id = binding.etId.text.toString()
                pw = binding.etPassword.text.toString()
                name = binding.etName.text.toString()
                binding.btnSignup.isEnabled = id != "" && pw != "" && name != ""
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.btnSignup.isEnabled = id != "" && pw != "" && name != ""
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnSignup.isEnabled = id != "" && pw != "" && name != ""
            }
        }

        binding.etId.addTextChangedListener(textWatcher)
        binding.etPassword.addTextChangedListener(textWatcher)
        binding.etName.addTextChangedListener(textWatcher)

        binding.btnSignup.setOnClickListener() {

            signUpService.signUp(
                RequestSignUpDTO(
                    id, pw, name
                )
            ).enqueue(object : Callback<ResponseSignUpDTO> {
                override fun onResponse(
                    call: Call<ResponseSignUpDTO>,
                    response: Response<ResponseSignUpDTO>
                ) {
                    if (response.isSuccessful) {
                        Snackbar.make(binding.root, "회원가입 성공", Snackbar.LENGTH_SHORT).show()
                        if (!isFinishing) finish()
                    }
                }

                override fun onFailure(call: Call<ResponseSignUpDTO>, t: Throwable) {
                    Snackbar.make(binding.root, "에러 발생", Snackbar.LENGTH_SHORT).show()
                    Log.e("stellar", "fail")
                }
            })
        }


    }
}