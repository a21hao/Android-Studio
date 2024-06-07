package com.a21hao.myproject_monster_hunter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MonsterAdapter(private val context: Context, private val monsters: List<Monster>) : RecyclerView.Adapter<MonsterAdapter.MonsterViewHolder>() {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
    private val gson = Gson()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonsterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_monster, parent, false)
        return MonsterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MonsterViewHolder, position: Int) {
        val monster = monsters[position]
        holder.nameTextView.text = "Name: ${monster.name}"
        holder.speciesTextView.text = "Species: ${monster.species}"
        holder.descriptionTextView.text = "Description: ${monster.description}"

        val locationBuilder = StringBuilder("Locations: ")
        for (location in monster.locations) {
            locationBuilder.append("${location.name}, ")
        }
        holder.locationsTextView.text = locationBuilder.toString().trimEnd(',', ' ')

        val weaknessBuilder = StringBuilder("Weaknesses: ")
        for (weakness in monster.weaknesses) {
            weaknessBuilder.append("${weakness.element} (${weakness.stars} stars), ")
        }
        holder.weaknessesTextView.text = weaknessBuilder.toString().trimEnd(',', ' ')

        val favoriteMonsters: MutableList<Monster> = getFavoriteMonsters()
        holder.favoriteCheckBox.isChecked = favoriteMonsters.contains(monster)

        holder.favoriteCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                addMonsterToFavorites(monster)
            } else {
                removeMonsterFromFavorites(monster)
            }
        }
    }

    override fun getItemCount(): Int {
        return monsters.size
    }

    inner class MonsterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val speciesTextView: TextView = itemView.findViewById(R.id.speciesTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val locationsTextView: TextView = itemView.findViewById(R.id.locationsTextView)
        val weaknessesTextView: TextView = itemView.findViewById(R.id.weaknessesTextView)
        val favoriteCheckBox: CheckBox = itemView.findViewById(R.id.favoriteCheckBox)
    }

    private fun getFavoriteMonsters(): MutableList<Monster> {
        val json = sharedPreferences.getString("favorite_monsters", "")
        val type = object : TypeToken<MutableList<Monster>>() {}.type
        return if (json.isNullOrEmpty()) mutableListOf() else gson.fromJson(json, type)
    }

    private fun saveFavoriteMonsters(monsters: MutableList<Monster>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(monsters)
        editor.putString("favorite_monsters", json)
        editor.apply()
    }

    private fun addMonsterToFavorites(monster: Monster) {
        val favoriteMonsters = getFavoriteMonsters()
        favoriteMonsters.add(monster)
        saveFavoriteMonsters(favoriteMonsters)
    }

    private fun removeMonsterFromFavorites(monster: Monster) {
        val favoriteMonsters = getFavoriteMonsters()
        favoriteMonsters.remove(monster)
        saveFavoriteMonsters(favoriteMonsters)
    }
}
