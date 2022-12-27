package org.sopt.sample.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setContentView(binding.root)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        textViewObserve()
        signUpDataObserve()

    }

    private fun textViewObserve() {
        viewModel.idText.observe(this) {
            warning(viewModel.idWarningChecker(), binding.tvIdWarn)
        }
        viewModel.pwText.observe(this) {
            warning(viewModel.pwWarningChecker(), binding.tvPwWarn)
        }
        viewModel.nameText.observe(this) {
            warning(viewModel.nameWarningChecker(), binding.tvNameWarn)
        }
    }

    private fun warning(boolean: Boolean, textView: TextView) {
        viewModel.btnEnabledChecker()
        if (boolean) {
            textView.visibility = View.INVISIBLE
        } else {
            textView.visibility = View.VISIBLE
        }
    }

    private fun signUpDataObserve() {
        viewModel.signUpData.observe(this) {
            if (it.status == 201) {
                if (!isFinishing) finish()
            } else {
            }
        }
    }

}



