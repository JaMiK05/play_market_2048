package uz.gita.play_market_2048.ui.activitys

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import uz.gita.play_market_2048.R
import uz.gita.play_market_2048.databinding.ActivityMainBinding
import uz.gita.play_market_2048.ui.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            Handler(Looper.getMainLooper()).postDelayed({
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, HomeFragment())
                    .commit()
            }, 3000)

        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}