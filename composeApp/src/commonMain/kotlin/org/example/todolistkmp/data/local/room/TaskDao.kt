package org.example.todolistkmp.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(taskEntity: TaskEntity): Long

    @Update
    suspend fun update(taskEntity: TaskEntity): Int

    @Delete
    suspend fun delete(taskEntity: TaskEntity): Int

    @Query("SELECT * FROM TaskEntity WHERE id = :taskId")
    fun getTaskById(taskId: Int): Flow<TaskEntity?>

    @Query("SELECT * FROM TaskEntity ORDER BY createdAt DESC")
    fun getTasksOrderedByDateCreated(): Flow<List<TaskEntity>>
}