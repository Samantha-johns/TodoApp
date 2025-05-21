package com.example.todo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.data.dao.TodoDAO
import com.example.todo.data.model.TodoItem

// Add annotation database to mark this class as the database migration
// layer
@Database(
    entities = [TodoItem::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){
    // define an abstract function for the database interface
    abstract fun todoDao() : TodoDAO
    // add seed data .....

}










