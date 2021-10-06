package com.superapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.superapp.R
import com.superapp.api.Api
import com.superapp.api.network.HeroService
import com.superapp.ui.AdapterHero
import com.superapp.vm.RepoHero
import com.superapp.vm.ViewModelHero
import com.superapp.vm.VmFactoryHero

class FragmentHeroList : Fragment() {

    var currentPage = 1
    var perpage = 15

    var isLoading = false

    lateinit var viewModelHero: ViewModelHero

    private val adapter = AdapterHero()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val serviceHero = Api.createService(HeroService::class.java)
        val heroRepoHero = RepoHero(serviceHero)

        adapter.setSelectedListener { hero ->
            val bundle = Bundle()
            bundle.putString("hero_id",hero.id)
            findNavController().navigate(
                R.id.action_fragmentHeroList_to_fragmentHeroDetails,
                bundle
            )
        }
        viewModelHero = ViewModelProvider(this,
            VmFactoryHero(heroRepoHero)).get(ViewModelHero::class.java)

        viewModelHero.heroList.observe(this, {
            adapter.setHeroModelList(it)
        })

        viewModelHero.isLoading.observe(this, {
            isLoading = it
        })

        loadHeroBundle()

    }

    fun loadHeroBundle(){
        var from = (currentPage - 1 ) * perpage
        if (from == 0) from = 1
        val to = (currentPage ) * perpage
        viewModelHero.getNextBundle(from,to)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hero_list, container, false)
        // Inflate the layout for this fragment
        val recyclerView: RecyclerView = view.findViewById(R.id.rvHeroList)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val visibleItems = layoutManager.childCount
                val latestVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount

                if(!isLoading){
                    if((visibleItems + latestVisibleItem) >= total){
                        currentPage++
                        loadHeroBundle()
                    }
                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })

        return view
    }
}