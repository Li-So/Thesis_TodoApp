package com.example.thesis_todoapp

import android.app.Application
import com.example.thesis_todoapp.data.AppContainer
import com.example.thesis_todoapp.data.AppDataContainer

class TodoApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}