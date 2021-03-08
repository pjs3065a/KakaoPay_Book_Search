package kr.pjs.booksearch.app

import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import kr.pjs.booksearch.DEBUG
import timber.log.Timber

object Library {
    fun init(context: Context) {
        initLogger()
    }

    /**
     * 로그 설정
     */
    private fun initLogger() {
        if (DEBUG) Timber.plant(Timber.DebugTree())
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean = DEBUG
        })
    }
}