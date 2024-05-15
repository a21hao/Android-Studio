package com.a21hao.myproject_monster_hunter

// Importa las clases necesarias de Room
import androidx.room.*
import androidx.room.Entity
import androidx.room.RoomDatabase

// Define la entidad Monster
@Entity(tableName = "monsters")
data class MonsterEntity(
    @PrimaryKey val id: Int,
    val type: String,
    val species: String,
    val name: String,
    val description: String
)

data class Weakness(
    val element: String,
    val stars: Int
)

data class Location(
    val id: Int,
    val zoneCount: Int,
    val name: String
)

// Define el Data Access Object (DAO)
@Dao
interface MonsterDao {
    @Query("SELECT * FROM monsters")
    fun getAll(): List<MonsterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(monsters: List<MonsterEntity>)
}

// Define la base de datos
@Database(entities = [MonsterEntity::class], version = 1)
abstract class MonsterDatabase : RoomDatabase() {
    abstract fun monsterDao(): MonsterDao
}
