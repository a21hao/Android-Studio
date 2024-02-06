package com.a21hao.myproject_monster_hunter

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

// Modelo de datos para el objeto Item
data class Item(
    val id: Int,
    val rarity: Int,
    @SerializedName("carryLimit")
    val carryLimit: Int,
    val value: Int,
    val name: String,
    val description: String
)

// Interfaz para la API
interface ApiService {
    @GET("items")
    suspend fun getItems(): List<Item>

}
