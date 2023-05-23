package uz.gita.play_market_2048.domain.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SharedPrev private constructor(val context: Context) {


    val shared: SharedPreferences by lazy {
        context.getSharedPreferences("local",
            Context.MODE_PRIVATE)
    }

    val edit: SharedPreferences.Editor = shared.edit()

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var shared: SharedPrev

        fun getInstance(context: Context): SharedPrev {
            if (!(::shared.isInitialized)) {
                shared = SharedPrev(context)
            }
            return shared
        }

    }


}