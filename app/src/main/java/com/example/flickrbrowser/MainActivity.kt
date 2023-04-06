package com.example.flickrbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val vm = ViewModelProvider(this)[SearchViewModel::class.java]
//
//        val rv = findViewById<RecyclerView>(R.id.rv)
//        rv.layoutManager = GridLayoutManager(this,2)
//
//        val adapter = PropertyListAdapter()
//        vm.photoList.observe(this){
//            if(it!=null){
//                adapter.setData(it!!)
//            }
//        }
//        rv.adapter = adapter
    }
}