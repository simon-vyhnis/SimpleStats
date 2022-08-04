package com.simon.simplestats.ui

import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simon.simplestats.R
import com.simon.simplestats.data.Register

class RegistersViewAdapter (private val registerList: List<Register>, /*private val viewModel : CreateViewModel*/) : RecyclerView.Adapter<RegistersViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if(viewType == 0) {
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_register, parent, false))
        }else{
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_register_with_values, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = registerList[position].name
        holder.buttonAdd.setOnClickListener {
            if(registerList[position].values){

            }else{

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

    open inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val buttonAdd: Button = itemView.findViewById(R.id.button_add)
    }

    inner class ViewHolderWithValue(itemView: View) : ViewHolder(itemView) {
        val editText: EditText = itemView.findViewById(R.id.input_value)
    }


}