package com.arthurguedescaminha.testedesenvolvedorandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    val model: CardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model.getCards().observe(this) {
            initUi()
        }
    }

    fun initUi() {
        model.getCards("teste").observe(this) { cards ->
        }
    }

}