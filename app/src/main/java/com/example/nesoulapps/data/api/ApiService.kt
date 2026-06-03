package com.example.nesoulapps.data.api

import com.example.nesoulapps.data.model.CatFactResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("fact")
    fun getCatFact(): Call<CatFactResponse>
}