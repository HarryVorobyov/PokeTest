package com.example.pokemon.utils

import android.graphics.PorterDuff
import android.widget.ProgressBar
import com.example.pokemon.App

object UIUtils {
    @JvmStatic
    fun setProgressBarColor(progressBar: ProgressBar?, color: Int) {
        if (progressBar != null) {
            val drawable =
                progressBar.indeterminateDrawable
            drawable?.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
    }

}