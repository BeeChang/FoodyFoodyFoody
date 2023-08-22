package com.example.beechang.foodyfoodyfoody.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.beechang.foodyfoodyfoody.R
import com.example.beechang.foodyfoodyfoody.databinding.ActivityDetailsBinding
import com.example.beechang.foodyfoodyfoody.model.Favorites
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val args by navArgs<DetailsActivityArgs>()
    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailsBinding
    private var recipeSaved = false
    private var savedRecipeId = 0
    private lateinit var favoriteStarMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = args.result.title
        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")

        val resultBundle = Bundle()
        resultBundle.putParcelable("recipeBundle", args.result)

        val adapter = PagerAdapter(
            resultBundle,
            fragments,
            titles,
            supportFragmentManager
        )

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        uiEventObserve()
    }

    private fun uiEventObserve() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.favoriteState.collect { uiState ->
                    when (uiState) {
                        is DetailViewModel.FavoriteUiState.Loading -> {}
                        is DetailViewModel.FavoriteUiState.checkFavoriteRecipes -> {
                            val favorite =
                                uiState.favorites.firstOrNull { it.foodyResult.recipeId == args.result.recipeId }
                            if (favorite != null) {
                                savedRecipeId = favorite.id
                                recipeSaved = true
                                favoriteStarMenuItem.let {
                                    changeMenuItemColor(favoriteStarMenuItem, R.color.yellow)
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        val menuItem = menu?.findItem(R.id.save_to_favorites_menu)
         if (menuItem != null) {
            favoriteStarMenuItem = menuItem
            changeMenuItemColor(menuItem, R.color.white)
            lifecycleScope.launch {
                detailViewModel.checkFavoriteRecipe()
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.save_to_favorites_menu && !recipeSaved) {
            saveToFavorites(item)

        } else if (item.itemId == R.id.save_to_favorites_menu && recipeSaved) {
            removeFromFavorites(item)

        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveToFavorites(item: MenuItem) {
        lifecycleScope.launch {
            detailViewModel.insertFavoriteRecipe(Favorites(0, args.result))
            changeMenuItemColor(item, R.color.yellow)
            showSnackBar("Recipe saved.")
            recipeSaved = true
        }
    }

    private fun removeFromFavorites(item: MenuItem) {
        lifecycleScope.launch {
            detailViewModel.deleteFavoriteRecipe(Favorites(savedRecipeId, args.result))
            changeMenuItemColor(item, R.color.white)
            showSnackBar("Removed from Favorites.")
            recipeSaved = false
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.detailsLayout,
            message,
            Snackbar.LENGTH_SHORT
        ).setAction("Okay") {}
            .show()
    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon?.setTint(ContextCompat.getColor(this, color))
    }
}