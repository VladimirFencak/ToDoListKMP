package org.example.todolistkmp.di

import org.example.todolistkmp.data.remote.TaskApi
import org.example.todolistkmp.data.remote.TaskApiImpl
import org.koin.dsl.module

val appModule = module {
    single<TaskApi> { TaskApiImpl(get()) }
}