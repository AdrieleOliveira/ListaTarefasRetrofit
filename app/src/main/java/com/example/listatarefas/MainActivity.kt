package com.example.listatarefas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listatarefas.adapters.TarefaAdapter
import com.example.listatarefas.adapters.TarefaAdapterListener
import com.example.listatarefas.model.Tarefa
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TarefaAdapterListener {

    private lateinit var adapter: TarefaAdapter
    private var tarefaSelected: Tarefa? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = TarefaAdapter(this, applicationContext)
        listTarefa.adapter = adapter
        listTarefa.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        btAdicionar.setOnClickListener{
            adapter.novo()
        }

    }

    override fun save(tarefa: Tarefa) {
        adapter.save(tarefa)
    }

    override fun editState(tarefa: Tarefa){
        adapter.edit(tarefa)
    }

    override fun remove(tarefa: Tarefa){
        adapter.remove(tarefa)
    }

    override fun share(tarefa: Tarefa){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, getString(R.string.compartilhar) + " " + tarefa.titulo)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(sendIntent, null))
    }
}