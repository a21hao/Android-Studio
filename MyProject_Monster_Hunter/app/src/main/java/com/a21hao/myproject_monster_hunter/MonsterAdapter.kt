package com.a21hao.myproject_monster_hunter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MonsterAdapter(private val monsters: List<Monster>) : RecyclerView.Adapter<MonsterAdapter.MonsterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonsterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_monster, parent, false)
        return MonsterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MonsterViewHolder, position: Int) {
        val monster = monsters[position]
        holder.nameTextView.text = "Name: ${monster.name}"
        holder.speciesTextView.text = "Species: ${monster.species}"
        holder.descriptionTextView.text = "Description: ${monster.description}"

        // Ubicaciones
        val locationBuilder = StringBuilder("Locations: ")
        for (location in monster.locations) {
            locationBuilder.append("${location.name}, ")
        }
        holder.locationsTextView.text = locationBuilder.toString().trimEnd(',', ' ')

        // Debilidades
        val weaknessBuilder = StringBuilder("Weaknesses: ")
        for (weakness in monster.weaknesses) {
            weaknessBuilder.append("${weakness.element} (${weakness.stars} stars), ")
        }
        holder.weaknessesTextView.text = weaknessBuilder.toString().trimEnd(',', ' ')
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
    }
}
