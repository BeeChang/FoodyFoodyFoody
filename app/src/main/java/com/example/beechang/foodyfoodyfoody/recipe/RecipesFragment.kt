package com.example.beechang.foodyfoodyfoody.recipe

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beechang.foodyfoodyfoody.R
import com.example.beechang.foodyfoodyfoody.base.BaseFragment
import com.example.beechang.foodyfoodyfoody.databinding.FragmentRecipesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : BaseFragment<FragmentRecipesBinding>(R.layout.fragment_recipes) ,
    SearchView.OnQueryTextListener {

    private val recipesViewModel: RecipesViewModel by viewModels()
    private val mAdapter by lazy { RecipesAdapter() }

    override fun initView() {
        setupRecyclerView()
        binding.recipesViewModel = recipesViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeUiState()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recipes_menu, menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null) {
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }

    private fun recipeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                recipesViewModel.recipesState.collect { uiState ->
                    when (uiState) {
                        is RecipesViewModel.RecipeUiState.Loading -> {
                             showShimmerEffect()
                        }
                        is RecipesViewModel.RecipeUiState.RecipesListUpdate -> {
                            hideShimmerEffect()
                            mAdapter.setData(uiState.recipes)
                        }
                        is RecipesViewModel.RecipeUiState.ShowError -> {
                            hideShimmerEffect()
                            Toast.makeText(context, uiState.errorMassage, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }


    private fun setupRecyclerView() {
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        binding.recyclerview.showShimmer()
    }

    private fun hideShimmerEffect() {
        binding.recyclerview.hideShimmer()
    }

}