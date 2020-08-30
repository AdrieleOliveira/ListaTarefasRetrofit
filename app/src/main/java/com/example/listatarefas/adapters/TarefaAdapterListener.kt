package com.example.listatarefas.adapters

import com.example.listatarefas.model.Tarefa

interface TarefaAdapterListener {
    fun save(tarefa: Tarefa)
    fun editState(tarefa: Tarefa)
    fun remove(tarefa: Tarefa)
    fun share(tarefa: Tarefa)
}