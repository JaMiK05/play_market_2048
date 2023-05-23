package uz.gita.play_market_2048.ui.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import uz.gita.play_market_2048.databinding.DialogExitBinding

class Diaog_Exit(context: Context) : AlertDialog(context) {

    private var _binding: DialogExitBinding? = null
    private val binding get() = _binding!!

    private var call: (() -> Unit)? = null

    fun setCall(block: () -> Unit) {
        call = block
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DialogExitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.apply {
            no.setOnClickListener {
                dismiss()
            }

            yes.setOnClickListener {
                call?.invoke()
            }

        }

    }

}