package com.example.wmart.retrofit

import com.example.wmart.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private lateinit var retrofitService : Retrofit
    val lock = Any()

    fun getInstance() : Retrofit{
        if(!this::retrofitService.isInitialized) {
            synchronized(lock) {
                if(!this::retrofitService.isInitialized) {
                    retrofitService = Retrofit.Builder()
                        .baseUrl(Constants.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
        }
        return retrofitService
    }

}