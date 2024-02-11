package com.a21hao.myproject_monster_hunter

data class Item(
    val id: Int,
    val rarity: Int,
    val carryLimit: Int,
    val value: Int,
    val name: String,
    val description: String
)
