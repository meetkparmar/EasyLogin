package com.meet.project.easylogin.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.meet.project.easylogin.databinding.ActivityComposeBinding
import com.meet.project.easylogin.utils.Prefs
import com.meet.project.easylogin.utils.Prefs.Companion.LOGGED_IN

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComposeBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Prefs.initPrefs(applicationContext)
        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        binding = ActivityComposeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (Prefs.getBoolean(LOGGED_IN, false)) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        viewModel.clear()

        binding.composeView.setContent {
            LoginActivityScreen(
                viewModel = viewModel,
                onLoginClick = ::onLoginClick,
                onSignUpClick = ::onSignUpClick
            )
        }
    }

    private fun onSignUpClick() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun onLoginClick() {
        viewModel.loading = true
        viewModel.showError = false
        if (viewModel.validateLogin()) {
            if (viewModel.nameList.contains(viewModel.loginName)) {
                val index = viewModel.nameList.indexOf(viewModel.loginName)
                if (viewModel.passwordList[index] == viewModel.loginPassword) {
                    Prefs.putBoolean(LOGGED_IN, true)
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    viewModel.loading = false
                    finish()
                } else {
                    viewModel.errorMsg = "Account not found!"
                    viewModel.showError = true
                    viewModel.loading = false
                }
            } else {
                viewModel.errorMsg = "Account not found!"
                viewModel.showError = true
                viewModel.loading = false
            }
        } else {
            viewModel.showError = true
            viewModel.loading = false
        }
    }
}