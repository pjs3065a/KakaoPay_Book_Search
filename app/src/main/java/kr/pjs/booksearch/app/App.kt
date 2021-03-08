package kr.pjs.booksearch.app

import android.app.Application

class App: Application() {
    init{
        app = this
    }

    override fun onCreate() {
        super.onCreate()
        Library.init(this)
    }

    companion object{
        private lateinit var app: App
        fun getInstance(): App = app
    }
}