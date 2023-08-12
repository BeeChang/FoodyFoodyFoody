package com.example.beechang.foodyfoodyfoody.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beechang.foodyfoodyfoody.R
import com.example.beechang.foodyfoodyfoody.base.BaseFragment
import com.example.beechang.foodyfoodyfoody.databinding.FragmentFavoriteRecipesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteRecipesFragment : BaseFragment<FragmentFavoriteRecipesBinding>(R.layout.fragment_favorite_recipes) {

    private val favoriteViewModel : FavoriteViewModel by viewModels()
    private val mAdapter: FavoriteRecipesAdapter by lazy { FavoriteRecipesAdapter(requireActivity(), favoriteViewModel) }

    override fun initView() {
        binding.lifecycleOwner = this
        binding.favoriteRecipesRecyclerView.adapter = mAdapter
        binding.favoriteRecipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        favoriteUiState()
    }

    override fun onResume() {
        super.onResume()
        getFavorites()
    }

    private fun getFavorites(){
        lifecycleScope.launch {
            favoriteViewModel.readFavoriteRecipes()
        }
    }

    private fun favoriteUiState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                favoriteViewModel.favoriteState.collect { uiState ->
                    when(uiState){
                        is FavoriteViewModel.FavoriteUiState.FavoriteEmpty -> {
                            binding.favoriteRecipesRecyclerView.visibility = View.INVISIBLE
                            binding.noDataImageView.visibility = View.VISIBLE
                            binding.noDataTextView.visibility = View.VISIBLE
                        }
                        is FavoriteViewModel.FavoriteUiState.GetFavorite -> {
                            binding.favoriteRecipesRecyclerView.visibility = View.VISIBLE
                            binding.noDataImageView.visibility = View.INVISIBLE
                            binding.noDataTextView.visibility = View.INVISIBLE
                            mAdapter.setData(uiState.favorites)
                        }

                        is FavoriteViewModel.FavoriteUiState.ShowError -> {
                            Toast.makeText(context, uiState.errorMassage, Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mAdapter.clearContextualActionMode()
    }

}