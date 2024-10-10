package com.example.trudvsemapi.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface TrudVsemApiService {
    @GET("vacancies")
    fun getVacancies(): Call<VacanciesResponse>
}