package org.sopt.sample

import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.databinding.ActivityMypageBinding

class MyPageActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMypageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var idData : String? = null
        var mbtiData : String? = null
        idData = intent.getStringExtra("ID")
        mbtiData = intent.getStringExtra("MBTI")
        binding.tvName.text = "ID: $idData"
        binding.tvMbti.text = "MBTI: $mbtiData"
    }
}