package com.example.trudvsemapi.retrofit

import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("name") val name: String // название вакансии
)
// Модель для объекта "vacancy"
data class VacancyDetails(
    @SerializedName("company") val company: Company // Данные о компании
)

// Модель для объекта "vacancy"
data class Vacancy(
    @SerializedName("vacancy") val details: VacancyDetails
)

// Модель данных для ответа API
data class VacanciesResponse(
    val results: Results
)

// Модель для результатов, содержащих вакансии
data class Results(
    val vacancies: List<Vacancy>
)
