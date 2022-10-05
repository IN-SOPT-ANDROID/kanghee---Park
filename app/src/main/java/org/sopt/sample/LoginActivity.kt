package org.sopt.sample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivityLoginBinding

class LoginActivity :AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var idData : String? = null
        var pwData : String? = null
        var mbtiData : String? = null
        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK) {
                idData = result.data?.getStringExtra("ID")
                pwData = result.data?.getStringExtra("PW")
                mbtiData = result.data?.getStringExtra("MBTI")
                Log.d("stellar", "data $idData $pwData $mbtiData")
            }
        }

        binding.btnSignup.setOnClickListener(){
            val intent = Intent(this,SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
        binding.btnLogin.setOnClickListener(){
            if((binding.etId.text.toString() == idData) && (binding.etPassword.text.toString() == pwData)){
                val mIntent = Intent(this,MyPageActivity::class.java).apply {
                    putExtra("ID", idData)
                    putExtra("MBTI", mbtiData)
                }
                Snackbar.make(binding.root,"로그인 성공",Snackbar.LENGTH_SHORT).show()
                finish()
                startActivity(mIntent)
            }
            else{
                Snackbar.make(binding.root,"로그인 실패",Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}