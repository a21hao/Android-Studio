package com.a21hao.myproject_monster_hunter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.a21hao.myproject_monster_hunter.Weakness
import com.a21hao.myproject_monster_hunter.Location

class MonsterAdapter(private var monsters: List<MonsterEntity>) : RecyclerView.Adapter<MonsterAdapter.MonsterViewHolder>() {

    private var locationsMap: Map<Int, List<Location>> = emptyMap()
    private var weaknessesMap: Map<Int, List<Weakness>> = emptyMap()

    fun setData(monsters: List<MonsterEntity>, locationsMap: Map<Int, List<Location>>, weaknessesMap: Map<Int, List<Weakness>>) {
        this.monsters = monsters
        this.locationsMap = locationsMap
        this.weaknessesMap = weaknessesMap
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonsterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_monster, parent, false)
        return MonsterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MonsterViewHolder, position: Int) {
        val monster = monsters[position]
        holder.nameTextView.text = "Name: ${monster.name}"
        holder.speciesTextView.text = "Species: ${monster.species}"
        holder.descriptionTextView.text = "Description: ${monster.description}"

        val locations = locationsMap[monster.id] ?: emptyList()
        val locationBuilder = StringBuilder("Locations: ")
        for (location in locations) {
            locationBuilder.append("${location.name}, ")
        }
        holder.locationsTextView.text = locationBuilder.toString().trimEnd(',', ' ')

        val weaknesses = weaknessesMap[monster.id] ?: emptyList()
        val weaknessBuilder = StringBuilder("Weaknesses: ")
        for (weakness in weaknesses) {
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
