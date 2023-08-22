package com.example.beechang.foodyfoodyfoody.detail

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beechang.foodyfoodyfoody.R
import com.example.beechang.foodyfoodyfoody.base.BaseFragment
import com.example.beechang.foodyfoodyfoody.databinding.FragmentIngredientsBinding
import com.example.beechang.foodyfoodyfoody.model.FoodyResult
import com.example.beechang.foodyfoodyfoody.model.ui.FoodyUiModel
import com.example.beechang.foodyfoodyfoody.utils.Constants.Companion.RECIPE_RESULT_KEY

class IngredientsFragment : BaseFragment<FragmentIngredientsBinding>(R.layout.fragment_ingredients) {

    private val mAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    override fun initView() {
        val args = arguments
        val myBundle: FoodyUiModel? = args?.getParcelable(RECIPE_RESULT_KEY)
        binding.ingredientsRecyclerview.adapter = mAdapter
        binding.ingredientsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        myBundle?.extendedIngredients?.let { mAdapter.setData(it) }
    }
}