package com.a21hao.myproject_monster_hunter

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("https://mhw-db.com/monsters")
    fun getMonsters(): Call<List<Monster>>
}
