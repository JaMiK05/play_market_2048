package uz.gita.play_market_2048.ui.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import uz.gita.play_market_2048.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSplashBinding.inflate(layoutInflater)

        binding.apply {
            val animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 100)
            animation.duration = 3000
            animation.interpolator = DecelerateInterpolator()
            animation.start()
        }

        return binding.root
    }

}