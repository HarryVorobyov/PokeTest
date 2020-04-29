package com.example.pokemon.ui.pokemonList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.base.BaseActivity
import com.example.pokemon.databinding.ActivityPokeListBinding
import com.example.pokemon.model.PokeModel
import com.example.pokemon.ui.pokeDetails.PokeDetailsActivity
import com.example.pokemon.ui.pokemonList.dialogDetails.DialogDetails

class PokemonListActivity : BaseActivity(), PokeAdapter.OnPokeClicListener {
    private var pokeCollection: PagedList<PokeModel>? = null
    lateinit var pokeListViewModel: PokeListViewModel
    private lateinit var activityMainBinding: ActivityPokeListBinding


    private var recyclerView: RecyclerView? = null
    private var pokeAdapter: PokeAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokeListViewModel = ViewModelProviders.of(this).get(PokeListViewModel::class.java)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_poke_list)

        pokeListViewModel.getPokePagedList().observe(this,
            Observer { t ->
                pokeCollection = t
                showOnRecyclerView()
            })
    }

    private fun showOnRecyclerView() {
        recyclerView = activityMainBinding.pokeRv
        pokeAdapter = PokeAdapter()
        pokeAdapter!!.setListener(this)
        pokeAdapter!!.submitList(pokeCollection)

        recyclerView!!.layoutManager = GridLayoutManager(this, 4)

        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = pokeAdapter
        pokeAdapter!!.notifyDataSetChanged()
    }

    override fun onPokeClickListener(pokeId: Int) {
        startActivity(PokeDetailsActivity.getInstans(this, pokeId))
    }

    override fun onDetailsClickListener(pokeId: Int) {
        openDetails(pokeId)
    }

    fun openDetails(pokeId: Int){
        DialogDetails.newInstance(pokeId).show(supportFragmentManager, "DialogDetails");
    }
}