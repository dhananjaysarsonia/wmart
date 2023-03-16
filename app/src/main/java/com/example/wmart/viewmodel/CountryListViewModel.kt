package com.example.wmart.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.wmart.MyApplication
import com.example.wmart.model.Countries
import com.example.wmart.repository.CountryListRepository
import com.example.wmart.retrofit.CountryApi
import com.example.wmart.retrofit.RetrofitService
import com.example.wmart.util.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CountryListViewModel(val countryListRepo : CountryListRepository) : ViewModel() {
    private val countryStateFlow : MutableStateFlow<Response> = MutableStateFlow(Response.Empty())
    val _countryStateFlow : StateFlow<Response> = countryStateFlow

    fun getCountries() {
        countryStateFlow.value = Response.Loading()
        viewModelScope.launch {
            countryListRepo.getCountries().catch {
                countryStateFlow.value = Response.Error(it.localizedMessage?: "")
            }.collect() {
                countryStateFlow.value = Response.Loaded(it)
            }

        }
    }

    @Suppress("UNCHECKED_CAST")
    class CountryListFactory(val repo : CountryListRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CountryListViewModel(repo) as T
        }
    }

}