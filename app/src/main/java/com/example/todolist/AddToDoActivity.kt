package com.example.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import java.util.*

class AddToDoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_do)
        title = "New To Do"

        val addToDoButton = findViewById<Button>(R.id.addToDoItemButton)
        val toDoText = findViewById<TextView>(R.id.toDoText)
        val isImportantCheckbox = findViewById<CheckBox>(R.id.isImportantCheckbox)
        val realm = Realm.getDefaultInstance()

        //add to do item using current data
        addToDoButton.setOnClickListener {
            val toDoItemText = toDoText.text.toString()
            val isImportant = isImportantCheckbox.isChecked

            realm.beginTransaction()
            val toDoItem = realm.createObject(ToDoItem::class.java, UUID.randomUUID().toString())
            toDoItem.toDoText = toDoItemText
            toDoItem.isImportant = isImportant
            realm.commitTransaction()

            val query = realm.where(ToDoItem::class.java)
            val results = query.findAll()

            for (item in results) {
                println("The to do item ${item.toDoText} is important?")
            }
            println("You have ${results.count()} things to do.")

            finish()
        }

            }

}
