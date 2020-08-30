package com.example.listatarefas.model

data class Tarefa(
    var titulo: String,
    var descricao: String,
    var finalizada: Boolean = false,
    var acao: String = "cadastrado"
) {
    var id: Long? = null
}
