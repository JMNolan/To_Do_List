package com.example.todolist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoListRecyclerAdapter(private val items: List<ToDoItem>): RecyclerView.Adapter<ToDoListRecyclerAdapter.ToDoListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.layout_to_do_list_item, parent, false)
        return ToDoListItemViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: ToDoListItemViewHolder, position: Int) {
        val toDoItem = items[position]
        holder.toDoListItemText.text = toDoItem.toDoText
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CompleteToDoActivity::class.java)
            val id = items[position].getID()
            intent.putExtra("ItemID", id)
            holder.itemView.context.startActivity(intent)
            items
        }
    }

    override fun getItemCount(): Int {
        val size = items.size
        println("You have $size things left to do.")
        return items.size
    }

    //custom ViewHolder for the ToDoItem recyclerView
    inner class ToDoListItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val toDoListItemText: TextView =itemView.findViewById(R.id.toDoItemRowText)
        fun bind(toDoListItem: ToDoItem, clickListener: (ToDoItem) -> Unit){
            toDoListItemText.text = toDoListItem.toDoText
        }
    }

//    class listItemListener() {
//        fun onClick(item: ToDoItem) = clickListener(item.)
//    }

}