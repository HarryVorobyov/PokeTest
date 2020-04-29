package com.example.pokemon.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import java.lang.Exception
import java.util.regex.Pattern

class PokeModel(
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String,
    var imgUrl: String?,
    var id: Int?
) {

    fun getImageUrl(): String {
        if (imgUrl == null) {
            imgUrl = calculateImageUrl()
        }

        return imgUrl!!
    }

    fun getId(): Int {
        if (id == null) {
            id = calculateId();
        }

        return id!!
    }

    fun calculateId(): Int {
        try {
            val p = Pattern.compile("(\\d+)(?!.*\\d)")
            val m = p.matcher(url)
            if (m.find()) {
                id = m.group().toInt()
            } else {
                id = -1
            }
        } catch (e: Exception) {
            id = -1
        }

        return id!!
    }

    fun calculateImageUrl(): String {
        return "https://pokeres.bastionbot.org/images/pokemon/${getId()}.png"
    }


    companion object {
        val CALLBACK: DiffUtil.ItemCallback<PokeModel> =
            object : DiffUtil.ItemCallback<PokeModel>() {
                override fun areItemsTheSame(oldItem: PokeModel, newItem: PokeModel): Boolean {
                    return oldItem.name === newItem.name
                }

                override fun areContentsTheSame(oldItem: PokeModel, newItem: PokeModel): Boolean {
                    return true
                }
            }
    }

}