package com.arthurguedescaminha.testedesenvolvedorandroid

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    var name : String,
    @SerializedName("type")
    var type : String,
    @SerializedName("frameType")
    var frameType : String,
    @SerializedName("desc")
    var desc : String,
    @SerializedName("race")
    var race : String,
    @SerializedName("archetype")
    var archetype : String,
    @SerializedName("card_sets")
    var cardSets : JsonObject,
    @SerializedName("card_images")
    var cardImages : JsonObject,
    @SerializedName("card_prices")
    var cardPrices : JsonObject
)