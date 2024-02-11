package com.a21hao.myproject_monster_hunter


import retrofit2.Call
import retrofit2.http.GET

interface WeaponsApiService {
    @GET("https://mhw-db.com/weapons")
    fun getWeapons(): Call<List<Weapon>>
}
