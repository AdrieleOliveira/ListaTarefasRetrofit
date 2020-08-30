package com.example.listatarefas.api

import com.example.listatarefas.model.Tarefa
import retrofit2.Call
import retrofit2.http.*

interface TarefaService{
    @GET("tarefa")
    fun getAll(): Call<List<Tarefa>>

    @GET("tarefa/{id}")
    fun get(@Path("id") id: Long): Call<Tarefa>

    @Headers("Content-Type: application/json")
    @POST("tarefa")
    fun insert(@Body tarefa: Tarefa): Call<Tarefa>

    @Headers("Content-Type: application/json")
    @PATCH("tarefa/{id}")
    fun update(@Path("id") id: Long, @Body tarefa: Tarefa): Call<Tarefa>

    @DELETE("tarefa/{id}")
    fun delete(@Path("id") id: Long): Call<Void>
}