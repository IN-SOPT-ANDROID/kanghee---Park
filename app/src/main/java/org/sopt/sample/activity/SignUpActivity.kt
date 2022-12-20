package org.sopt.sample.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var name: String
//    private val idPattern = "^[a-z|A-Z|0-9]{6,10}$"
//    private val pwPattern = "^[a-z|A-Z|0-9|[@#$%&*?!]]{6,10}$"
//    val idCheck: Pattern = Pattern.compile(idPattern)
//    val pwCheck: Pattern = Pattern.compile(pwPattern)
//    private val signUpService: AuthService = ServicePool.authService
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivitySignUpBinding.inflate(layoutInflater)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setContentView(binding.root)

        id = binding.etId.text.toString()
        pw = binding.etPassword.text.toString()
        name = binding.etName.text.toString()
        binding.btnSignup.isEnabled = true

//        watchEditText()
//        signUp()
        binding.btnSignup.setOnClickListener{
            viewModel.signUp(
                id, pw, name
            )
            Snackbar.make(binding.root, id+pw+name, Snackbar.LENGTH_SHORT).show()
        }
        viewModel.signUpData.observe(this){
            if(it.status == 200){
                Snackbar.make(binding.root, "회원가입 성공", Snackbar.LENGTH_SHORT).show()
                if (!isFinishing) finish()
            }
            else{
                Snackbar.make(binding.root, "에러 발생", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

//    private fun signUp() {
//        binding.btnSignup.setOnClickListener() {
//
//            signUpService.signUp(
//                RequestSignUpDTO(
//                    id, pw, name
//                )
//            ).receive()
//        }
//    }
//
//    private fun <T> Call<T>.receive() {
//        this.enqueue(object : Callback<T> {
//            override fun onResponse(
//                call: Call<T>,
//                response: Response<T>
//            ) {
//                if (response.isSuccessful) {
//                    Snackbar.make(binding.root, "회원가입 성공", Snackbar.LENGTH_SHORT).show()
//                    if (!isFinishing) finish()
//                }
//            }
//
//            override fun onFailure(call: Call<T>, t: Throwable) {
//                Snackbar.make(binding.root, "에러 발생", Snackbar.LENGTH_SHORT).show()
//                Log.e("stellar", "fail")
//            }
//        })
//    }

//    private fun watchEditText() {
//        val textWatcher = tw()
//        binding.etId.addTextChangedListener(textWatcher)
//        binding.etPassword.addTextChangedListener(textWatcher)
//        binding.etName.addTextChangedListener(textWatcher)
//    }

//
//    inner class tw : TextWatcher {
//        override fun afterTextChanged(s: Editable?) {
//            id = binding.etId.text.toString()
//            pw = binding.etPassword.text.toString()
//            name = binding.etName.text.toString()
//            binding.btnSignup.isEnabled =
//                idCheck.matcher(id).find() && pwCheck.matcher(pw).find() && name.isNotBlank()
//            binding.tvIdWarn.visibility = View.INVISIBLE
//            binding.tvPwWarn.visibility = View.INVISIBLE
//
//            checker()
//        }
//
//        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            checker()
//        }
//
//        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            checker()
//        }
//
//        private fun checker() {
//            binding.btnSignup.isEnabled =
//                idCheck.matcher(id).find() && pwCheck.matcher(pw).find() && name.isNotBlank()
//            if (binding.btnSignup.isEnabled) {
//                binding.btnSignup.setBackgroundColor(Color.parseColor("#003366"))
//            } else {
//                binding.btnSignup.setBackgroundColor(Color.parseColor("#808080"))
//                if (!idCheck.matcher(id).find() && id.isNotBlank()) {
//                    binding.tvIdWarn.visibility = View.VISIBLE
//                }
//                if (!pwCheck.matcher(pw).find() && pw.isNotBlank()) {
//                    binding.tvPwWarn.visibility = View.VISIBLE
//                }
//            }
//        }
//    }
}



