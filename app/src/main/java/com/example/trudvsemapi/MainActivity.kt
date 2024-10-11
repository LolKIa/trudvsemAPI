package com.example.trudvsemapi

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trudvsemapi.databinding.ActivityMainBinding
import com.example.trudvsemapi.retrofit.CompanyContact
import com.example.trudvsemapi.retrofit.TrudVsemApiService
import com.example.trudvsemapi.retrofit.VacanciesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val companyContacts = mutableListOf<CompanyContact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rcView.layoutManager = LinearLayoutManager(this)
        val adapter = CompanyAdapter(companyContacts)
        binding.rcView.adapter = adapter

        fetchVacancies()

    }

    object RetrofitInstance {
        val api: TrudVsemApiService by lazy {
            Retrofit.Builder()
                .baseUrl("https://opendata.trudvsem.ru/api/v1/") // Базовый URL для API
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TrudVsemApiService::class.java)
        }
    }

    private fun fetchVacancies() {
        val call = RetrofitInstance.api.getVacancies()

        call.enqueue(object : Callback<VacanciesResponse> {
            override fun onResponse(call: Call<VacanciesResponse>, response: Response<VacanciesResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { vacanciesResponse ->
                        vacanciesResponse.results.vacancies.forEach { vacancy ->
                            val companyName = vacancy.details.company.name
                            var phone: String? = null
                            var email: String? = null

                            vacancy.details.contactList.forEach { contact ->
                                when (contact.contactType) {
                                    "Телефон" -> phone = contact.contactValue
                                    "Эл. почта" -> email = contact.contactValue
                                }
                            }

                            companyContacts.add(CompanyContact(companyName, phone, email))
                        }

                        binding.rcView.adapter?.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<VacanciesResponse>, t: Throwable) {
                Log.e("Error", "Failed to fetch vacancies: ${t.message}")
            }
        })
    }

}