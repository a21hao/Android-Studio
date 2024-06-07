package com.a21hao.myproject_monster_hunter

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Monster(
    val id: Int,
    val type: String,
    val species: String,
    val name: String,
    val description: String,
    val locations: List<Location>,
    val weaknesses: List<Weakness>
) : Serializable

data class Location(
    val id: Int,
    val zoneCount: Int,
    val name: String
) : Serializable

data class Weakness(
    val element: String,
    val stars: Int
) : Serializable
