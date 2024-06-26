package org.example.todolistkmp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TaskEntity::class],
    version = 1
)

abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

// FIXME: Added a hack to resolve below issue:
// Class 'AppDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
    fun clearAllTables() {}
}