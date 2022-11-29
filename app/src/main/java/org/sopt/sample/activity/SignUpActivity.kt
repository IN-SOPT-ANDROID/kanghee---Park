package org.sopt.sample.activity

import android.content.Intent
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
    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val signUpService: AuthService = ServicePool.authService

        id = binding.etId.text.toString()
        pw = binding.etPassword.text.toString()
        name = binding.etName.text.toString()
        binding.btnSignup.isEnabled = false

        val textWatcher = tw()
        binding.etId.addTextChangedListener(textWatcher)
        binding.etPassword.addTextChangedListener(textWatcher)
        binding.etName.addTextChangedListener(textWatcher)

        binding.btnSignup.setOnClickListener() {

            signUpService.signUp(
                RequestSignUpDTO(
                    id, pw, name
                )
            ).receive()
        }
    }

    fun <T> Call<T>.receive() {
        this.enqueue(object : Callback<T> {
            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                if (response.isSuccessful) {
                    Snackbar.make(binding.root, "회원가입 성공", Snackbar.LENGTH_SHORT).show()
                    if (!isFinishing) finish()
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                Snackbar.make(binding.root, "에러 발생", Snackbar.LENGTH_SHORT).show()
                Log.e("stellar", "fail")
            }
        })
    }

    inner class tw : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            id = binding.etId.text.toString()
            pw = binding.etPassword.text.toString()
            name = binding.etName.text.toString()
            binding.btnSignup.isEnabled = id.isNotBlank() && pw.isNotBlank() && name.isNotBlank()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            binding.btnSignup.isEnabled = id.isNotBlank() && pw.isNotBlank() && name.isNotBlank()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.btnSignup.isEnabled = id.isNotBlank() && pw.isNotBlank() && name.isNotBlank()
        }
    }
}



