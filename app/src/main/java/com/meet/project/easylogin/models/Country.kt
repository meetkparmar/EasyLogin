package com.meet.project.easylogin.models

data class Country(
    var data: HashMap<String, CountryDetail> = hashMapOf()
)

data class CountryDetail(
    var country: String = "",
    var region: String = "",
)
