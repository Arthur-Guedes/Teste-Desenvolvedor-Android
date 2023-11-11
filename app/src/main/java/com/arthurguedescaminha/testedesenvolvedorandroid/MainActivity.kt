package com.arthurguedescaminha.testedesenvolvedorandroid

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val model: CardViewModel by viewModels()

    lateinit var searchView: SearchView
    lateinit var recyclerView: RecyclerView
    lateinit var textView: TextView
    lateinit var progressBar: ProgressBar

    // Indica se usuário já fez alguma filtragem de cards ou não, para, num primeiro momento, mostrar todos os cards de uma vez
    var firstTimeLoading = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model.getCards().observe(this) { cards ->
            if (cards.isEmpty()) {
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
        val cardAdapter = CardAdapter()
        cardAdapter.submitList(model.getCards().value!!)
        val layoutManager = GridLayoutManager(this, 2)

        textView = findViewById(R.id.text_view)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = cardAdapter

        searchView = findViewById(R.id.search_view)
        searchView.isActivated = true
        searchView.queryHint = "Digite aqui o nome do card"
        searchView.onActionViewExpanded()
        searchView.isIconified = false
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(firstTimeLoading) {
                    firstTimeLoading = false
                }

                model.updateCardsList(newText)

                return false
            }
        })

        progressBar = findViewById(R.id.progress_bar)

        // Num carregamento inicial, mostra todos os cards
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE

        model.getOrderedCards().observe(this) { cards ->
            if(!firstTimeLoading) {
                cardAdapter.submitList(model.getOrderedCards().value!!)

                progressBar.visibility = View.GONE
                if(cards.isEmpty()) {
                    recyclerView.visibility = View.GONE
                    textView.visibility = View.VISIBLE
                } else {
                    recyclerView.visibility = View.VISIBLE
                    textView.visibility = View.GONE
                }
            }
        }

        model.isSorting().observe(this) { isSorting ->
            if(isSorting == true) {
                recyclerView.visibility = View.GONE
                textView.visibility = View.GONE

                progressBar.visibility = View.VISIBLE
            }
        }
    }

}