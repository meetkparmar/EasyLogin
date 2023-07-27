package com.meet.project.easylogin.ui.activity

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.meet.project.easylogin.models.Country
import com.meet.project.easylogin.utils.COUNTRY_JSON

class MainViewModel(context: Application) : AndroidViewModel(context) {

    var showError by mutableStateOf(false)
    var loading by mutableStateOf(false)
    var errorMsg by mutableStateOf(value = "")
    var loginName by mutableStateOf(value = "")
    var loginPassword by mutableStateOf(value = "")
    var signUpName by mutableStateOf(value = "")
    var signUpPassword by mutableStateOf(value = "")
    var expanded by mutableStateOf(false)
    var selectedCountry by mutableStateOf("")
    var countries = mutableListOf<String>()
    var database: DatabaseReference = Firebase.database.reference
    var nameList = mutableListOf<String>()
    var passwordList = mutableListOf<String>()

    val REGEX_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$".toRegex()

    fun onLoginNameTextChange(newText: String) {
        if (newText.length < 20) {
            loginName = newText
        }
    }

    fun onLoginPasswordTextChange(newText: String) {
        if (newText.length < 20) {
            loginPassword = newText
        }
    }

    fun onSignUpNameTextChange(newText: String) {
        if (newText.length < 20) {
            signUpName = newText
        }
    }

    fun onSignUpPasswordTextChange(newText: String) {
        if (newText.length < 20) {
            signUpPassword = newText
        }
    }

    fun onCountryTextChange(newText: String) {
        selectedCountry = newText
    }

    fun getCountryData() {
        countries.clear()
        val countryData: Country = Gson().fromJson(COUNTRY_JSON, Country::class.java)
        countryData.data.forEach { (t, u) ->
            countries.add(u.country)
        }

        selectedCountry = countries[0]
    }

    fun validateLogin(): Boolean {
        return if (loginName.isEmpty() || loginPassword.isEmpty()) {
            errorMsg = "Please enter Name and Password"
            false
        } else {
            true
        }
    }

    fun validateSignUp(): Boolean {
        return if (signUpName.isEmpty() || signUpPassword.isEmpty() || selectedCountry.isEmpty()) {
            errorMsg = "Please enter all the details"
            false
        } else if (!REGEX_PASSWORD.containsMatchIn(signUpPassword)) {
            errorMsg = "Please enter valid Password"
            false
        } else {
            true
        }
    }

    fun clear() {
        loginName = ""
        loginPassword = ""
        signUpName = ""
        signUpPassword = ""

        database.child("users").get()
            .addOnSuccessListener {
                val map = it.value as Map<String, Map<String, String>>
                map.entries.forEachIndexed { _, entry ->
                    entry.value.entries.forEachIndexed { _, e ->
                        if (e.key == "name") nameList.add(e.value)
                        if (e.key == "password") passwordList.add(e.value)
                    }
                }
            }
            .addOnFailureListener {
                Log.e("Firebase", "Something went wrong on fetch data")
            }
    }
}
