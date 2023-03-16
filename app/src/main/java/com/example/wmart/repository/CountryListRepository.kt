package com.example.wmart.repository

import com.example.wmart.model.Country
import com.example.wmart.retrofit.CountryApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CountryListRepository {
    suspend fun getCountries() : Flow<List<Country>> {
        return flow {
            emit(CountryApi.countryApi.getCountries())
        }.flowOn(Dispatchers.IO)
    }
}