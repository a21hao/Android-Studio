package com.a21hao.myproject_monster_hunter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item -> {
                    startActivity(Intent(this, ItemActivity::class.java))
                    true
                }
                R.id.menu_monster -> {
                    startActivity(Intent(this, MonsterActivity::class.java))
                    true
                }
                R.id.menu_weapon -> {
                    startActivity(Intent(this, WeaponActivity::class.java))
                    true
                }
                R.id.menu_skills -> {
                    startActivity(Intent(this, SkillsActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
