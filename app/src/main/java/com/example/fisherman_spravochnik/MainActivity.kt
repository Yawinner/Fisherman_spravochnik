package com.example.fisherman_spravochnik

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener(this)

        var list = ArrayList<ListItem>()

        list.addAll(fillArrays(resources.getStringArray(R.array.fish), resources.getStringArray(R.array.fish_content), getImageId(R.array.fish_image_array)))
        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(list, this)
        rcView.adapter = adapter

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.id_fish ->{
                Toast.makeText(this, "Рыбы",Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(fillArrays(resources.getStringArray(R.array.fish), resources.getStringArray(R.array.fish_content), getImageId(R.array.fish_image_array)))
            }
            R.id.id_na ->
            {
                Toast.makeText(this, "Наживки",Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(fillArrays(resources.getStringArray(R.array.na), resources.getStringArray(R.array.na_content), getImageId(R.array.na_image_array)))
            }
            R.id.id_sna ->
            {
                Toast.makeText(this, "Снасти",Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(fillArrays(resources.getStringArray(R.array.sna), resources.getStringArray(R.array.sna_content), getImageId(R.array.sna_image_array)))
            }
            R.id.id_history ->
            {
                Toast.makeText(this, "Истории",Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(fillArrays(resources.getStringArray(R.array.his), resources.getStringArray(R.array.his_content), getImageId(R.array.his_image_array)))
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
    fun fillArrays(titleArray:Array<String>, contentArray: Array<String>,imageArray:IntArray):List<ListItem>{
        var listItemArray = ArrayList<ListItem>()
        for(n in 0.. titleArray.size - 1){
            var listItem = ListItem(imageArray[n], titleArray[n], contentArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray
    }
    fun getImageId(imageArrayId: Int):IntArray{
        var tArray: TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = tArray.length()
        val ids = IntArray(count)
        for(i in ids.indices){
            ids[i] = tArray.getResourceId(i, 0)

        }
        tArray.recycle()
        return ids
    }


}