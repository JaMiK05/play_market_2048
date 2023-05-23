package uz.gita.play_market_2048.ui.activitys

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import uz.gita.play_market_2048.databinding.ActivityGameBinding
import uz.gita.play_market_2048.domain.local.SharedPrev
import uz.gita.play_market_2048.enums.SideEnum
import uz.gita.play_market_2048.touch.MyTouch
import uz.gita.play_market_2048.ui.fragments.*
import uz.gita.play_market_2048.util.BackgrounUtil

class GameActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityGameBinding


    private lateinit var myTouch: MyTouch

    private val game: Game by lazy { Game(this) }

    private val items = ArrayList<TextView>()

    private val color: BackgrounUtil by lazy { BackgrounUtil() }


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        loadViews()
        wireUpListeners()
        describeMatrixToViews()

        myTouch = MyTouch(this)
        myTouch.setCall {
            when (it) {
                SideEnum.LEFT -> {
                    game.moveToLeft()
                }
                SideEnum.RIGHT -> {
                    game.moveToRight()
                }
                SideEnum.UP -> {
                    game.moveToUp()
                }
                SideEnum.DOWN -> {
                    game.moveToDown()
                }
            }
        }
        _binding.pare.setOnTouchListener(myTouch)

    }

    private fun wireUpListeners() = with(_binding) {
        trash.setOnClickListener {
            trash.isEnabled = false
            game.trash()
        }
        val bool = SharedPrev.getInstance(this@GameActivity)
        trash.isEnabled = bool.shared.getBoolean("trash", true)

        home.setOnClickListener {
            finish()
        }

        reload.setOnClickListener {
            game.newGame()
        }

    }

    private fun loadViews() {
        _binding.apply {
            items.add(_binding.b1)
            items.add(_binding.b2)
            items.add(_binding.b3)
            items.add(_binding.b4)
            items.add(_binding.b5)
            items.add(_binding.b6)
            items.add(_binding.b7)
            items.add(_binding.b8)
            items.add(_binding.b9)
            items.add(_binding.b10)
            items.add(_binding.b11)
            items.add(_binding.b12)
            items.add(_binding.b13)
            items.add(_binding.b14)
            items.add(_binding.b15)
            items.add(_binding.b16)
        }
    }

    fun tr() {
        _binding.trash.isEnabled = true
    }

    override fun onPause() {
        SharedPrev.getInstance(this).edit.apply {
            putBoolean("game", true)
            putBoolean("trash", _binding.trash.isEnabled)
            for (i in 0 until 16) putInt("numb$i", game.matrix[i / 4][i % 4])
            putInt("score", game.score)
            putInt("best", game.best)
        }.apply()
        super.onPause()
    }

    fun game_over() {
        _binding.trash.isEnabled = false
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                items[i * 4 + j].apply {
                    text = ""
                    setBackgroundResource(color.colorByAmount(10))
                }
            }
        }
        _binding.gameover.visibility = View.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            _binding.gameover.visibility = View.GONE
            game.newGame()
        }, 5000)

    }

    fun describeMatrixToViews() {
        _binding.apply {
            score.text = "${game.score}"
            best.text = "${game.best}"
            record.text = "${game.record}"
        }

        val _matrix = game.matrix
        for (i in _matrix.indices) {
            for (j in _matrix[i].indices) {
                items[i * 4 + j].apply {
                    text = if (_matrix[i][j] == 0) ""
                    else _matrix[i][j].toString()
                    setBackgroundResource(color.colorByAmount(_matrix[i][j]))
                }
            }
        }
    }


}