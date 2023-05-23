package uz.gita.play_market_2048.util

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import uz.gita.play_market_2048.R

class BackgrounUtil {

    private val backgroundMap = HashMap<Int, Int>()

    init {
        loadMap()
    }

    private fun loadMap() {
        backgroundMap[0] = R.drawable.empty_corner
        backgroundMap[2] = R.drawable.bg_corner2
        backgroundMap[4] = R.drawable.bg_corner4
        backgroundMap[8] = R.drawable.bg_corner8
        backgroundMap[16] = R.drawable.bg_corner16
        backgroundMap[32] = R.drawable.bg_corner32
        backgroundMap[64] = R.drawable.bg_corner64
        backgroundMap[128] = R.drawable.bg_corner128
        backgroundMap[256] = R.drawable.bg_corner256
        backgroundMap[512] = R.drawable.bg_corner512
        backgroundMap[1_024] = R.drawable.bg_corner1024
        backgroundMap[2_048] = R.drawable.bg_corner2048
        backgroundMap[4_096] = R.drawable.bg_corner4096
        backgroundMap[8_192] = R.drawable.bg_corner8192
        backgroundMap[16_384] = R.drawable.bg_corner16384
        backgroundMap[10] = R.drawable.over
    }

    @SuppressLint("ObsoleteSdkInt")
    @RequiresApi(Build.VERSION_CODES.N)
    fun colorByAmount(amount: Int): Int {
        return backgroundMap.getOrDefault(amount, R.drawable.over)
    }

}