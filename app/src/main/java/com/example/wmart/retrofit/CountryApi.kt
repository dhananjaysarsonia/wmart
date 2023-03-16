package com.example.wmart.retrofit
import androidx.lifecycle.LiveData
import com.example.wmart.model.Country
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface CountryApi {
    companion object {
        val countryApi : CountryApi = RetrofitService.getInstance().create(CountryApi::class.java)
    }

    @GET("countries.json")
    suspend fun getCountries() : List<Country>


}