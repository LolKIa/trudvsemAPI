package com.example.trudvsemapi.retrofit

import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String?// название вакансии
)

// Модель для объекта "vacancy"
data class VacancyDetails(
    @SerializedName("company") val company: Company,
    @SerializedName("contact_list") val contactList: List<Contact>// Данные о компании
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

data class Contact(
    @SerializedName("contact_type") val contactType: String,
    @SerializedName("contact_value") val contactValue: String
)

data class CompanyContact(
    val name: String,
    val phone: String?,
    val email: String?
)