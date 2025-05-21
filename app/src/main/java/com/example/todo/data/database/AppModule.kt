package com.example.todo.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todo.data.dao.TodoDAO
import com.example.todo.data.repository.TodoRepository
import com.example.todo.data.repository.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // migration functionality
    private val MIGRATION_1_2 = object: Migration(1,2){
        override fun migrate(db: SupportSQLiteDatabase) {
//            super.migrate(db)
            db.execSQL(
                "ALTER TABLE todos ADD COLUMN firebase_id " +
                        "INTEGER NOT NULL DEFAULT 0"
            )
        }
    }
    @Provides
    @Singleton
    fun provideTodoRepository(dao: TodoDAO) : TodoRepository{
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
        ).addMigrations(MIGRATION_1_2).build()
    }

    @Provides
    fun provideTodoDAO(database: AppDatabase): TodoDAO {
        return database.todoDao()
    }
}













