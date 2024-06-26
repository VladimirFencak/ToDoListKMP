package org.example.todolistkmp.di

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.todolistkmp.database.TasksDatabase
import org.example.todolistkmp.presentation.add_task.AddTaskViewModel
import org.example.todolistkmp.presentation.agenda.AgendaViewModel
import org.example.todolistkmp.presentation.detail.DetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual val expectAppModule: Module = module {
    single {
        HttpClient(Android) {
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

    //    single<TaskDatabase> {
    //        val dbFile = androidContext().getDatabasePath("task-database")
    //        Room.databaseBuilder<TaskDatabase>(
    //            context = androidContext().applicationContext,
    //            name = dbFile.absolutePath
    //        )
    //            .setDriver(BundledSQLiteDriver())
    //            .build()
    //    }

    //    single<TaskDao> {
    //        get<TaskDatabase>()
    //    }

    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = TasksDatabase.Schema,
            context = androidContext(),
            name = "task.db"
        )
    }

    viewModel<AgendaViewModel> {
        AgendaViewModel(get())
    }

    viewModel<AddTaskViewModel> {
        AddTaskViewModel(get())
    }

    viewModel<DetailViewModel> {
        DetailViewModel(get(), get())
    }
}