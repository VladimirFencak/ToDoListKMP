package org.example.todolistkmp.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
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
    //    single {
    //        Room.databaseBuilder(
    //            get(),
    //            TaskDatabase::class.java,
    //            "task-database"
    //        ).setDriver(BundledSQLiteDriver()).build()
    //    }
}