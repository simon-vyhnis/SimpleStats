package com.simon.simplestats.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.simon.simplestats.R
import com.simon.simplestats.data.Event
import com.simon.simplestats.data.Register

class RegistersViewAdapter (private val registerList: List<Register>, private val viewModel : CreateViewModel, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == 0) {
            ViewHolderWithoutValue(LayoutInflater.from(parent.context).inflate(R.layout.item_register, parent, false))
        }else{
            ViewHolderWithValue(LayoutInflater.from(parent.context).inflate(R.layout.item_register_with_values, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if(registerList[position].values){
           val viewHolder = holder as ViewHolderWithValue
           viewHolder.name.text = registerList[position].name
           viewHolder.buttonAdd.setOnClickListener{
               val value = viewHolder.editText.text.toString().toDoubleOrNull()
               value?.let {
                   viewModel.addEvent(Event((System.currentTimeMillis()/1000), it, registerList[position].id))
                   viewHolder.editText.text.clear()
                   Toast.makeText(context, "Value added", Toast.LENGTH_SHORT).show()
               }

           }
           viewHolder.itemView.setOnClickListener {
                //TODO go to display register activity
           }
       }else{
           val viewHolder = holder as ViewHolderWithoutValue
           viewHolder.name.text = registerList[position].name
           viewHolder.buttonAdd.setOnClickListener {
               viewModel.addEvent(Event((System.currentTimeMillis()/1000), null, registerList[position].id))
               Toast.makeText(context, "Record added", Toast.LENGTH_SHORT).show()
           }
           viewHolder.itemView.setOnClickListener {
                //TODO go to display register activity
           }
       }
    }

    override fun getItemViewType(position: Int): Int {
        if(registerList[position].values){
            return 1
        }
        return 0
    }

    override fun getItemCount(): Int {
        return registerList.size
    }

    open inner class ViewHolderWithoutValue(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val buttonAdd: Button = itemView.findViewById(R.id.button_add)
    }

    inner class ViewHolderWithValue(itemView: View) : ViewHolderWithoutValue(itemView) {
        val editText: EditText = itemView.findViewById(R.id.input_value)
    }


}