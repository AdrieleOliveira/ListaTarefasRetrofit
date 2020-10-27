package com.example.listatarefas.model

data class Tarefa(
    var titulo: String,
    var descricao: String,
    var finalizada: Boolean = false,
    var latitude: Double,
    var longitude: Double,
    var acao: String = "cadastrado"
) {
    var id: Long? = null
}
