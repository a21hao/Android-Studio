package com.a21hao.myproject_monster_hunter

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Items_Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: View
    private var items: MutableList<Item> = mutableListOf()
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        recyclerView = findViewById(R.id.item_recycle)
        progressBar = findViewById(R.id.progress_bar_item)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ItemAdapter(items)
        recyclerView.adapter = adapter

        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE

        val retrofit = Retrofit.Builder()
                .baseUrl("https://mhw-db.com/item/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val apiService = retrofit.create(ItemApiService::class.java)

        val call = apiService.getItems()
        call.enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if (response.isSuccessful) {
                    items.addAll(response.body()!!)
                    adapter.notifyDataSetChanged()


                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                progressBar.visibility = View.GONE
            }
        })
    }
}
