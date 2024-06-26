package org.example.todolistkmp.di

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.todolistkmp.database.TasksDatabase
import org.example.todolistkmp.presentation.add_task.AddTaskViewModel
import org.example.todolistkmp.presentation.agenda.AgendaViewModel
import org.example.todolistkmp.presentation.detail.DetailViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual val expectAppModule: Module = module {
    single {
        HttpClient(Darwin) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
            }
        }
    }

    //    single {
    //        val dbFile = NSHomeDirectory() + "/task-database.db"
    //        Room.databaseBuilder(
    //            name = dbFile,
    //            factory = { TaskDatabase::class.instantiateImpl() }
    //        )
    //            .setDriver(BundledSQLiteDriver())
    //            .build()
    //    }
    //

    single<SqlDriver> {
        NativeSqliteDriver(
            TasksDatabase.Schema,
            "task.db"
        )
    }

    single<AgendaViewModel> {
        AgendaViewModel(get())
    }

    single<AddTaskViewModel> {
        AddTaskViewModel(get())
    }

    single<DetailViewModel> {
        DetailViewModel(get(), get())
    }
}