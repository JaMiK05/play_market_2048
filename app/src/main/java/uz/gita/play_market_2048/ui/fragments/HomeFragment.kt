package uz.gita.play_market_2048.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.play_market_2048.R
import uz.gita.play_market_2048.databinding.FragmentHomeBinding
import uz.gita.play_market_2048.ui.activitys.GameActivity
import uz.gita.play_market_2048.ui.dialog.Diaog_Exit


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.includedLayout.apply {

            openMenu.setOnClickListener {
                binding.root.openDrawer(GravityCompat.START)
            }

            play.setOnClickListener {
                requireActivity().startActivity(Intent(requireContext(), GameActivity::class.java))
            }
            exit.setOnClickListener {
                val dialog = Diaog_Exit(requireContext())
                dialog.setCall {
                    requireActivity().finishAffinity()
                }
                dialog.show()
            }

            share.setOnClickListener {
                val appMsg = "Hey !, Chack out this app for Share Button :-" +
                        "https://play.google.com/store/apps/details?id=uz.gita.play_market_2048_jamik"
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, appMsg)
                intent.type = "test/plain"
                requireActivity().startActivity(intent)
            }

        }

        binding.apply {
            drawer.itemIconTintList = null
            drawer.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.telegram -> {
                        requireActivity().startActivity(Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://t.me/jamik_gamer")))
                    }
                    R.id.instagram -> {
                        requireActivity().startActivity(Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://instagram.com/_mr.jamik_?igshid=ZDdkNTZiNTM=")))
                    }
                    R.id.tik -> {
                        requireActivity().startActivity(Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.tiktok.com/@jamik_gamer_?_t=8bhYxtZbfpS&_r=1")))
                    }
                }
                true
            }
        }

    }

    private fun nav(): Boolean {
        return true
    }
}
