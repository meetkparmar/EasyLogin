package com.meet.project.easylogin.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.meet.project.easylogin.databinding.ActivityComposeBinding
import com.meet.project.easylogin.utils.Prefs

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComposeBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        binding = ActivityComposeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.composeView.setContent {
            HomeActivityScreen(
                onLogoutClick = ::onLogoutClick,
            )
        }
    }

    private fun onLogoutClick() {
        val intent = Intent(this, LoginActivity::class.java)
        Prefs.clearAllData()
        startActivity(intent)
        finish()
    }
}