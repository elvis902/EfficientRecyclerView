package com.example.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

/*
 OnItemClickListener is an manual interface created by me in ExampleAdapter class
 to handle onClicks from the activity instead from the adapter. So I implemented
 OnItemClickListener  in MainActivity so that I can define onItemClick here itself
 in the activity.
 */


class MainActivity : AppCompatActivity(), ExampleAdapter.OnItemClickListener {

    val exampleList = generateDummyList(500)
    val adapter = ExampleAdapter(exampleList, this)  // MainActivity implements OnItemClickListener and it is passed to listener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)  // We can also use GridLayoutManager
        rvMain.setHasFixedSize(true)  //If we are sure that our recycler view will have fixed number of items, it is better to setHasFixed as true for
                                       //better optimization
    }

    //A new item is inserted at a random postion (0 to 8) in the recycler View when inserted button is pressed
    fun insertItem(view: View){
        val idx = Random.nextInt(until = 8)
        val newItem = ExampleItem(
                R.drawable.ic_baseline_add_location_24, "New List Item at Line $idx", "Line 2"
        )
        exampleList.add(idx, newItem);
        adapter.notifyItemInserted(idx)
    }

    //An item is deleted at a random postion (0 to 8) in the recycler View when deleted button is pressed
    fun deleteItem(view: View){
        val idx = Random.nextInt(until = 8)

        exampleList.removeAt(idx)
        adapter.notifyItemRemoved(idx)
    }

    //OnItemClickListener our manual interface has a fun which need to be overrided and define as you want, as MainActivity implements OnItemClickListener
    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = exampleList[position]
        clickedItem.text1 = "Clicked"
        adapter.notifyItemChanged(position)
    }

    //A fun to generate any number of dummy data in an ArrayList to showcase in our recycler View
    private fun generateDummyList(size : Int) : ArrayList<ExampleItem>{
        val list = ArrayList<ExampleItem>()
        for(i in 0 until size){
            val drawable = when(i%3) {
                0 -> R.drawable.ic_baseline_camera_enhance_24
                1-> R.drawable.ic_baseline_build_24
                else-> R.drawable.ic_baseline_add_location_24
            }
            val item = ExampleItem(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }
}