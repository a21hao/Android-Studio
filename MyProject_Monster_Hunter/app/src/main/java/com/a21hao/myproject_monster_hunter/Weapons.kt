package com.a21hao.myproject_monster_hunter
data class Weapon(
    val id: Int,
    val type: String,
    val rarity: Int,
    val attack: Attack,
    val elderseal: String?,
    val attributes: Map<String, Any>,
    val damageType: String,
    val name: String,
    val durability: List<Map<String, Int>>,
    val slots: List<Any>,
    val elements: List<Any>,
    val crafting: Crafting,
    val assets: Assets
)

data class Attack(
    val display: Int,
    val raw: Int
)

data class Crafting(
    val craftable: Boolean,
    val previous: Int?,
    val branches: List<Int>,
    val craftingMaterials: List<CraftingMaterial>,
    val upgradeMaterials: List<UpgradeMaterial>
)

data class CraftingMaterial(
    val quantity: Int,
    val item: WeaponItem
)

data class UpgradeMaterial(
    val quantity: Int,
    val item: WeaponItem
)

data class WeaponItem(
    val id: Int,
    val rarity: Int,
    val carryLimit: Int,
    val value: Int,
    val name: String,
    val description: String
)

data class Assets(
    val icon: String,
    val image: String
)
