package org.example.todolistkmp

import android.app.Application
import org.example.todolistkmp.di.KoinInitializer

class ToDoListApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}