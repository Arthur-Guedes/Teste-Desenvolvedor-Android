package com.arthurguedescaminha.testedesenvolvedorandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardViewModel : ViewModel() {

    private val cards: MutableLiveData<List<Card>> by lazy {
        MutableLiveData<List<Card>>().also {
            loadCards()
        }
    }

    fun getCards(): LiveData<List<Card>> {
        return cards
    }

    private fun loadCards() {
        val gson = Gson()
        val tempCards = mutableListOf<Card>()

        ApiService.service.listCards().enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                // TODO t.message
                cards.postValue(listOf())
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                response.body()!!["data"].asJsonArray.forEach { card ->
                    tempCards.add(gson.fromJson(card.asJsonObject, Card::class.java))
                }

                cards.postValue(tempCards)
            }
        })
    }

}