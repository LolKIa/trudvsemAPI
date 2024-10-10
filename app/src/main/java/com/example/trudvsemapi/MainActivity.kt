package com.example.trudvsemapi

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trudvsemapi.databinding.ActivityMainBinding
import com.example.trudvsemapi.retrofit.TrudVsemApiService
import com.example.trudvsemapi.retrofit.VacanciesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val companyList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rcView.layoutManager = LinearLayoutManager(this)
        val adapter = CompanyAdapter(companyList)
        binding.rcView.adapter = adapter

// Вызов метода для получения данных
        fetchVacancies()

        binding.btnGet.setOnClickListener{
            adapter.notifyDataSetChanged()
        }
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
// Получаем список вакансий
                    response.body()?.let { vacanciesResponse ->
                        vacanciesResponse.results.vacancies.forEach { vacancy ->
                            vacancy.details.company.name?.let { companyName -> companyList.add(companyName)
                                    Log.d(
                                    "Company Name",
                                    companyName
                                ) // Выводим название вакансии в лог
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<VacanciesResponse>, t: Throwable) {
                Log.e("Error", "Failed to fetch vacancies: ${t.message}")
            }
        })
    }
}