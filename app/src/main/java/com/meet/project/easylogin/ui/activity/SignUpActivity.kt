package com.meet.project.easylogin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.meet.project.easylogin.databinding.ActivityComposeBinding
import com.meet.project.easylogin.models.SignUpDetail
import com.meet.project.easylogin.utils.Prefs

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComposeBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        binding = ActivityComposeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getCountryData()
        binding.composeView.setContent {
            SignUpActivityScreen(
                viewModel = viewModel,
                onLoginClick = ::onLoginClick,
                onSignUpClick = ::onSignUpClick,
            )
        }
    }

    private fun onSignUpClick() {
        viewModel.showError = false
        viewModel.loading = true
        if (viewModel.validateSignUp()) {
            val signUpDetail = SignUpDetail(
                id = System.currentTimeMillis().toString(),
                name = viewModel.signUpName,
                password = viewModel.signUpPassword,
                country = viewModel.selectedCountry
            )
            viewModel.database.child("users").child(signUpDetail.id).setValue(signUpDetail)
                .addOnSuccessListener {
                    Prefs.putBoolean(Prefs.LOGGED_IN, true)
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    viewModel.loading = false
                    finish()
                }
                .addOnFailureListener {
                    viewModel.loading = false
                    Toast.makeText(this, "Something Went Wrong!", Toast.LENGTH_SHORT).show()
                }
        } else {
            viewModel.showError = true
            viewModel.loading = false
        }
    }

    private fun onLoginClick() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}