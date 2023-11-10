package com.arthurguedescaminha.testedesenvolvedorandroid

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val model: CardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model.getCards().observe(this) { cards ->
            if(cards.isEmpty()) {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Não foi possível encontrar nenhum card no banco de dados. Sua internet pode estar indisponível, o aplicativo será encerrado. Verifique a rede e tente novamente.")
                    .setPositiveButton("Ok") { dialog, id ->
                        finish()
                    }
                builder.create()
                builder.show()
            } else {
                initUi()
            }
        }
    }

    fun initUi() {
        model.getCards("teste").observe(this) { cards ->
        }
    }

}