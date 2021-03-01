package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    private var listItems = queryToDoItems()

    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set up the recyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.toDoItemRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ToDoListRecyclerAdapter(listItems)

        setSupportActionBar(findViewById(R.id.toolbar))
        val addActivityButton = findViewById<FloatingActionButton>(R.id.addActivityButton)

        //takes you to the AddToDoActivity to add a new item to the list
        addActivityButton.setOnClickListener {
            val addToDoIntent = Intent(this, AddToDoActivity:: class.java)
            startActivity(addToDoIntent)
        }
    }

    private fun queryToDoItems(): List<ToDoItem> {
        //realm query to populate recycler view with all saved ToDoItems
        val realm = Realm.getDefaultInstance()

        val query = realm.where(ToDoItem::class.java)
        return query.findAll()
    }

    override fun onResume() {
        super.onResume()
        listItems = queryToDoItems()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}