package com.example.trudvsemapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trudvsemapi.retrofit.CompanyContact

class CompanyAdapter(private val companyList: List<CompanyContact>) :
    RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>() {

    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companyNameTextView: TextView = view.findViewById(R.id.company_name)
        val companyPhoneTextView: TextView = view.findViewById(R.id.company_phone)
        val companyEmailTextView: TextView = view.findViewById(R.id.company_email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.company_item, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val contactInfo = companyList[position]
        holder.companyNameTextView.text = contactInfo.name
        holder.companyPhoneTextView.text = contactInfo.phone ?: "Нет телефона"
        holder.companyEmailTextView.text = contactInfo.email ?: "Нет email"
    }

    override fun getItemCount(): Int = companyList.size
}