package com.simon.simplestats.ui

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.simon.simplestats.R
import com.simon.simplestats.data.Register

class CreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        val viewModel = ViewModelProvider(this)[CreateViewModel::class.java]

        val button = findViewById<Button>(R.id.button_add)
        val nameEditText = findViewById<EditText>(R.id.input_name)
        val checkBox = findViewById<CheckBox>(R.id.values)
        button.setOnClickListener {
            if(!nameEditText.text.isNullOrBlank()){
                viewModel.addRegister(Register(nameEditText.text.toString(), checkBox.isChecked))
                finish()
            }
        }
    }
}