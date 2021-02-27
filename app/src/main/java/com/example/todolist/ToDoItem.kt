package com.example.todolist

import io.realm.RealmObject

public open class ToDoItem : RealmObject() {
    public var toDoText = ""
    public var isImportant = false
}