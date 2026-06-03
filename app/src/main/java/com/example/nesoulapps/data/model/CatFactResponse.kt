package com.example.nesoulapps.data.model

import com.google.gson.annotations.SerializedName

data class CatFactResponse(
    @SerializedName("fact")
    val fact: String,
    @SerializedName("length")
    val length: Int
)