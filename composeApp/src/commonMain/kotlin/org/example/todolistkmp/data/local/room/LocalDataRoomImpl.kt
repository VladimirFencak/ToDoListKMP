package org.example.todolistkmp.data.local.room

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.todolistkmp.data.local.LocalData
import org.example.todolistkmp.domain.model.Task

class LocalDataRoomImpl(
    private val taskDao: TaskDao
) : LocalData {
    override fun getTaskById(taskId: Int): Flow<Task?> {
        return taskDao.getTaskById(taskId).map { taskEntity ->
            taskEntity?.toTask()
        }
    }

    override suspend fun insertTask(task: Task) {
        taskDao.insert(task.toTaskEntity())
    }

    override suspend fun updateTask(task: Task) {
        taskDao.update(task.toTaskEntity())
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.delete(task.toTaskEntity())
    }

    override fun getTasksOrderedByDateCreated(): Flow<List<Task>> {
        return taskDao.getTasksOrderedByDateCreated().map { taskEntities ->
            taskEntities.map { it.toTask() }
        }
    }

}