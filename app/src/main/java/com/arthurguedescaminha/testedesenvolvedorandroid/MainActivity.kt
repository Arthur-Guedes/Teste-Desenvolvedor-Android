package com.arthurguedescaminha.testedesenvolvedorandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ApiService.service.listCards().enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                // TODO t.message
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                // TODO response.body()!!["data"].asJsonArray
            }
        })
    }

}