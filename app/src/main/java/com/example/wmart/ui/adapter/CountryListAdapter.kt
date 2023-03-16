package com.example.wmart.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wmart.R
import com.example.wmart.model.Country
import com.google.android.material.textview.MaterialTextView

class CountryListAdapter : ListAdapter<Country, CountryListAdapter.CountryListViewHolder>(diffUtil) {
    companion object {
        val diffUtil  = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_country_item, parent, false)
        return CountryListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        val country = getItem(position)
        holder.view.findViewById<MaterialTextView>(R.id.tvCountry).text = holder.view.resources.getString(R.string.country_region, country.name, country.region)
        holder.view.findViewById<MaterialTextView>(R.id.tvCapital).text = country.capital
        holder.view.findViewById<MaterialTextView>(R.id.tvLanguage).text = country.language.name
        holder.view.findViewById<MaterialTextView>(R.id.tvCurrency).text = country.currency.name
        holder.view.findViewById<MaterialTextView>(R.id.tvCode).text = country.code
    }

    class CountryListViewHolder(val view : View) : RecyclerView.ViewHolder(view){}

}