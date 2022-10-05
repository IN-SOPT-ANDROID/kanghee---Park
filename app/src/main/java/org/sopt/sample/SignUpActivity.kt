package org.sopt.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivitySignUpBinding

class SignUpActivity:AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener(){
            var id = binding.etId.text.toString()
            var pw = binding.etPassword.text.toString()
            binding.tvIdWarn.visibility = View.INVISIBLE
            binding.tvPwWarn.visibility = View.INVISIBLE

            if(id.length in 6..10 && pw.length in 8 .. 12){
                val intent = Intent(this,LoginActivity::class.java).apply {
                    putExtra("ID", binding.etId.text.toString())
                    putExtra("PW", binding.etPassword.text.toString())
                    putExtra("MBTI", binding.etMbti.text.toString())
                }
                setResult(RESULT_OK, intent)
                Snackbar.make(binding.root,"회원가입 성공",Snackbar.LENGTH_SHORT).show()
                if(!isFinishing) finish()
            }

            else{
                Snackbar.make(binding.root,"회원가입 조건 불충분(ID: 6~10 글자 이내, PW: 8~12글자 이내)",Snackbar.LENGTH_SHORT).show()
                if(id.length !in 6..10){
                    binding.tvIdWarn.visibility = View.VISIBLE
                }
                if (pw.length !in 8 .. 12){
                    binding.tvPwWarn.visibility = View.VISIBLE
                }
            }
        }
    }
}