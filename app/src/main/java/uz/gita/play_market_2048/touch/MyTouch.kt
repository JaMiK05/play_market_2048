package uz.gita.play_market_2048.touch

import android.annotation.SuppressLint
import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import uz.gita.play_market_2048.enums.SideEnum
import kotlin.math.abs

class MyTouch(val context: Context) : View.OnTouchListener {


    private val dec = GestureDetector(context, gester())

    inner class gester : GestureDetector.SimpleOnGestureListener() {

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val swipe = 100
            val x = abs(e1.x - e2.x)
            val y = abs(e1.y - e2.y)

            if (x > swipe || y > swipe) {
                // surildi
                if (x > y) {
                    //horizontal

                    if (e1.x < e2.x) {
                        //right
                        call?.invoke(SideEnum.RIGHT)
                    } else {
                        //left
                        call?.invoke(SideEnum.LEFT)
                    }

                } else {
                    //vertical
                    if (e1.y < e2.y) {
                        //Down
                        call?.invoke(SideEnum.DOWN)
                    } else {
                        //UP
                        call?.invoke(SideEnum.UP)

                    }
                }

            }

            return super.onFling(e1, e2, velocityX, velocityY)
        }

    }


    private var call: ((SideEnum) -> Unit?)? = null

    fun setCall(block: (SideEnum) -> Unit) {
        call = block
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        dec.onTouchEvent(event!!)
        return true
    }



}