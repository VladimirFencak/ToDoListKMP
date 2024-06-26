package org.example.todolistkmp.di

import org.example.todolistkmp.data.local.LocalData
import org.example.todolistkmp.data.local.sql.LocalDataSqlImpl
import org.example.todolistkmp.data.remote.TaskApi
import org.example.todolistkmp.data.remote.TaskApiImpl
import org.example.todolistkmp.data.repository.TaskRepositoryImpl
import org.example.todolistkmp.database.TasksDatabase
import org.example.todolistkmp.domain.repository.TaskRepository
import org.koin.dsl.module

val appModule = module {
    single<TaskApi> {
        TaskApiImpl(get())
    }

    single {
        TasksDatabase(get())
    }

    single<LocalData> {
        LocalDataSqlImpl(get())
    }

    single<TaskRepository> {
        TaskRepositoryImpl(get(), get())
    }
}