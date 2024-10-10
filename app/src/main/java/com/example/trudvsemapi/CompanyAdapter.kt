package com.example.trudvsemapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CompanyAdapter(private val companyList: List<String>) :
    RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>() {

    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companyNameTextView: TextView = view.findViewById(R.id.company_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.company_item, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.companyNameTextView.text = companyList[position]
    }

    override fun getItemCount(): Int = companyList.size
}