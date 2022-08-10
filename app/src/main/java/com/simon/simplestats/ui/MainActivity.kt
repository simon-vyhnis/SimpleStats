package com.simon.simplestats.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.simon.simplestats.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonAdd = findViewById<FloatingActionButton>(R.id.button_add)
        buttonAdd.setOnClickListener {
            intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

        loadData()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData(){
        val viewModel = ViewModelProvider(this)[CreateViewModel::class.java]
        val emptyStateText = findViewById<TextView>(R.id.emtpy_state_text)
        val recyclerView = findViewById<RecyclerView>(R.id.register_list)
        viewModel.getRegisterList().observe(this) {
            if(it.isNotEmpty()){
                emptyStateText.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = RegistersViewAdapter(it, viewModel, this)
            }else{
                emptyStateText.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
        }
    }
}