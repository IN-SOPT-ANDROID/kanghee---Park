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
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivitySignUpBinding.inflate(layoutInflater)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setContentView(binding.root)
        binding.viewModel = viewModel

        binding.btnSignup.isEnabled = true

//        binding.btnSignup.setOnClickListener{
//            viewModel.signUp()
//        }
        viewModel.signUpData.observe(this){
            if(it.status == 201){
                Snackbar.make(binding.root, "회원가입 성공", Snackbar.LENGTH_SHORT).show()
                if (!isFinishing) finish()
            }
            else{
                Snackbar.make(binding.root, "에러 발생", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}



