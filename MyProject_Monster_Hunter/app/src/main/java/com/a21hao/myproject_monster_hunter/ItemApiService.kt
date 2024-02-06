package com.a21hao.myproject_monster_hunter

import retrofit2.http.GET

interface ItemApiService {
    @GET("items")
    suspend fun getItems(): List<Item>
}
