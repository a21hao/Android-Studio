package com.a21hao.myproject_monster_hunter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.a21hao.myproject_monster_hunter.R
import com.bumptech.glide.Glide

class WeaponsAdapter(private val context: Context, private val weapons: List<Weapon>) :
    RecyclerView.Adapter<WeaponsAdapter.WeaponViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weapons, parent, false)
        return WeaponViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        val weapon = weapons[position]
        holder.bind(weapon)
    }

    override fun getItemCount(): Int {
        return weapons.size
    }

    inner class WeaponViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val attackTextView: TextView = itemView.findViewById(R.id.attackTextView)
        private val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        private val rarityTextView: TextView = itemView.findViewById(R.id.rarityTextView)
        private val eldersealTextView: TextView = itemView.findViewById(R.id.eldersealTextView)
        private val weaponImageView: ImageView = itemView.findViewById(R.id.weaponImageView)

        fun bind(weapon: Weapon) {
            nameTextView.text = weapon.name
            attackTextView.text = "Attack: ${weapon.attack.display}"
            typeTextView.text = "Type: ${weapon.type}"
            rarityTextView.text = "Rarity: ${weapon.rarity}"
            eldersealTextView.text = "Elderseal: ${weapon.elderseal ?: "N/A"}"


            Glide.with(context)
                .load(weapon.assets.image)
                .placeholder(R.drawable.cuadrado)
                .into(weaponImageView)


        }
    }
}