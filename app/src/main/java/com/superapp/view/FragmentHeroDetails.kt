package com.superapp.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.superapp.R
import com.superapp.api.Api
import com.superapp.api.model.HeroModel
import com.superapp.api.network.HeroService
import com.superapp.databinding.FragmentHeroDetailsBinding
import com.superapp.vm.RepoHero
import com.superapp.vm.ViewModelHero
import com.superapp.vm.VmFactoryHero

class FragmentHeroDetails : Fragment() {

    lateinit var binding: FragmentHeroDetailsBinding

    lateinit var viewModelHero: ViewModelHero

    var heroId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null){
           heroId = requireArguments().getString("hero_id","")
        }

        val serviceHero = Api.createService(HeroService::class.java)
        val heroRepoHero = RepoHero(serviceHero)

        viewModelHero = ViewModelProvider(this,
            VmFactoryHero(heroRepoHero)
        ).get(ViewModelHero::class.java)

        viewModelHero.heroModel.observe(this,{
            bind(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHeroDetailsBinding.inflate(inflater,container,false)

        viewModelHero.getById(heroId)

        return binding.root
    }

    fun bind(model: HeroModel){

        Glide.with(requireContext())
            .load(model.image?.url)
            .placeholder(R.drawable.ic_baseline_hourglass_top_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(binding.imgHero)

        if(model.powerStats != null){
            binding.txtDurability.text = "${model.powerStats?.durability?:""}"
            binding.txtIntelligence.text = "${model.powerStats?.intelligence?:""}"
            binding.txtCombat.text = "${model.powerStats?.combat?:""}"
            binding.txtSpeed.text = "${model.powerStats?.speed?:""}"
            binding.txtPower.text = "${model.powerStats?.power?:""}"
            binding.txtStrenght.text = "${model.powerStats?.strength?:""}"
        }

        binding.txtGender.text = "Gender: ${model.appearance?.gender}"
        binding.txtGender.text = "Race: ${model.appearance?.race}"
        binding.txtGender.text = "Height: ${model.appearance?.height?.joinToString(", ")}"
        binding.txtGender.text = "Weight: ${model.appearance?.weight?.joinToString(", ")}"
        binding.txtGender.text = "Eye color: ${model.appearance?.eyeColor}"
        binding.txtGender.text = "Hair color: ${model.appearance?.hairColor}"

        binding.txtFullName.text = "Full name: ${model.biography?.fullName}"
        binding.txtAlterEgo.text = "Alter Egos: ${model.biography?.alterEgos}"
        binding.txtAliases.text = "Aliases: ${model.biography?.aliases?.joinToString(", ")}"
        binding.txtPlaceBirth.text = "Place of Birth: ${model.biography?.placeOfBirth}"
        binding.txtFirstAppearance.text = "First Appearance: ${model.biography?.firstAppearance}"
        binding.txtFirstPublisher.text = "Publisher: ${model.biography?.publisher}"
        binding.txtFirstAlignment.text = "Alignment: ${model.biography?.alignment}"

        binding.txtOccupation.text = "Occupation: ${model.work?.occupation}"
        binding.txtWorkBase.text = "Base of operation: ${model.work?.base}"

        binding.txtGroupAffiliation.text = "Group Affiliation: ${model.connections?.groupAffiliation}"
        binding.txtRelatives.text = "Relatives: ${model.connections?.relatives}"

    }

}