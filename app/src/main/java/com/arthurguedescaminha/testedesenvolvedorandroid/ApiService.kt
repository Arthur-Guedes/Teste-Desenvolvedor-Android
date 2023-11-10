package com.arthurguedescaminha.testedesenvolvedorandroid

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiService {

    var retrofit = Retrofit.Builder()
        .baseUrl("https://db.ygoprodeck.com/api/v7/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: ApiEndpoint = retrofit.create(ApiEndpoint::class.java)

    interface ApiEndpoint {

        @GET("cardinfo.php")
        fun listCards(): Call<JsonObject>

    }

}