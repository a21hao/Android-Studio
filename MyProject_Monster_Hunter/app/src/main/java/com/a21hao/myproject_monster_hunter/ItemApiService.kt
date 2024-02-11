package com.a21hao.myproject_monster_hunter

import retrofit2.Call
import retrofit2.http.GET

interface ItemApiService {
    @GET("https://mhw-db.com/items")
    fun getItems(): Call<List<Item>> // Asegúrate de tener una clase Item que represente la estructura de tus datos de items
}
