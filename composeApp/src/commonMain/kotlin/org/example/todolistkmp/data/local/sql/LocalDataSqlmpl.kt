package org.example.todolistkmp.data.local.sql

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.todolistkmp.data.local.LocalData
import org.example.todolistkmp.database.TasksDatabase
import org.example.todolistkmp.domain.model.Task

class LocalDataSqlImpl(
    private val database: TasksDatabase
) : LocalData {
    override fun getTaskById(taskId: Int): Flow<Task?> {
        return database.tasksQueries.getTaskById(taskId.toLong())
            .asFlow()
            .mapToOne()
            .map { it.toTask() }
    }

    override suspend fun insertTask(task: Task) {
        database.tasksQueries.insert(
            title = task.title,
            description = task.description,
            isCompleted = if (task.isCompleted) 1L else 0L,
            createdAt = task.createdAt
        )
    }

    override suspend fun updateTask(task: Task) {
        database.tasksQueries.update(
            title = task.title,
            description = task.description,
            isCompleted = if (task.isCompleted) 1L else 0L,
            createdAt = task.createdAt,
            id = task.id.toLong()
        )
    }

    override suspend fun deleteTask(task: Task) {
        database.tasksQueries.delete(task.id.toLong())
    }

    override fun getTasksOrderedByDateCreated(): Flow<List<Task>> {
        return database.tasksQueries.getTasksOrderedByDateCreated()
            .asFlow()
            .mapToList()
            .map { taskEntities ->
                taskEntities.map { it.toTask() }
            }
    }

}