package com.example.todo.data.database

import android.content.Context
import androidx.room.Room
import com.example.todo.data.dao.TodoDAO
import com.example.todo.data.repository.TodoRepository
import com.example.todo.data.repository.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    @Singleton
    fun provideTodoRepository(dao: TodoDAO) :  TodoRepository{
        return TodoRepositoryImpl(dao)
    }
    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context):
            AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    fun provideTodoDAO(database: AppDatabase): TodoDAO {
        return database.todoDao()
    }
}













