package com.example.pokemon.utils

import android.content.Context
import android.widget.Toast
import com.example.pokemon.App

object GeneralUtils {
    @JvmStatic
    fun isNullOrEmpty(s: String?): Boolean {
        return s == null || s == ""
    }

    fun showToast(context: Context?, message: Int) {
        if (context != null && message > 0) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}