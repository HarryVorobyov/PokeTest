package com.example.pokemon.ui.pokeDetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pokemon.R
import com.example.pokemon.base.BaseActivity
import com.example.pokemon.databinding.ActivityPokeDetailsBinding

class PokeDetailsActivity : BaseActivity() {
    private val KEY_POKE_ID = "poke_id"
    lateinit var pokeViewModel: PokeDetailsViewModel
    private lateinit var activityDetailsBinding: ActivityPokeDetailsBinding
    var showLoader = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_poke_details)
        pokeViewModel = ViewModelProviders.of(this).get(PokeDetailsViewModel::class.java)
        pokeViewModel.setId(intent.extras?.getInt(KEY_POKE_ID, -1) ?: -1)
        activityDetailsBinding.loader = showLoader


        pokeViewModel.getPokeData().observe(this,
            Observer { t ->
                activityDetailsBinding.pokeItem = t
                showLoader = false
                activityDetailsBinding.loader = showLoader
            })
    }

    companion object {
        val KEY_POKE_ID = "poke_id"

        fun getInstans(context: Context, pokeId: Int): Intent {
            val intent = Intent(context, PokeDetailsActivity::class.java)
            intent.putExtra(KEY_POKE_ID, pokeId)
            return intent
        }
    }

}