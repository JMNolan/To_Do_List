package com.example.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm

class CompleteToDoActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_complete_to_do_activity)

        title = "Complete A Task"

        //variables to reference UI items
        val taskText = findViewById<TextView>(R.id.listItemText)
        val completeButton = findViewById<Button>(R.id.completeButton)
        val toDoItemId = intent.getStringExtra("ItemID")

        //query for the selected item using the id property
        val realm = Realm.getDefaultInstance()
        val selectedItem = realm.where(ToDoItem::class.java).equalTo("id", toDoItemId).findFirst()
        selectedItem?.let { taskText.text = it.toDoText }

        completeButton.setOnClickListener {
            //delete realm object from database when user taps complete button
            realm.beginTransaction()
            selectedItem?.let { it.deleteFromRealm() }
            realm.commitTransaction()

            finish()
        }


    }

}