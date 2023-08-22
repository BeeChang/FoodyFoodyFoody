package com.example.beechang.foodyfoodyfoody.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beechang.foodyfoodyfoody.R
import com.example.beechang.foodyfoodyfoody.databinding.IngredientsRowLayoutBinding
import com.example.beechang.foodyfoodyfoody.model.ExtendedIngredient
import com.example.beechang.foodyfoodyfoody.model.ui.ExtendedIngredientUiModel
import com.example.beechang.foodyfoodyfoody.utils.Constants.Companion.BASE_IMAGE_URL
import com.example.beechang.foodyfoodyfoody.utils.RecipesDiffUtil

import java.util.Locale

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    private var ingredientsList = emptyList<ExtendedIngredientUiModel>()

    class MyViewHolder(val binding: IngredientsRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            IngredientsRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide
            .with(holder.itemView.context)
            .load(BASE_IMAGE_URL + ingredientsList[position].image)
            .error(R.drawable.ic_error_placeholder)
            .into(holder.binding.ingredientImageView)

        holder.binding.ingredientName.text = ingredientsList[position].name.capitalize(Locale.ROOT)
        holder.binding.ingredientAmount.text = ingredientsList[position].amount.toString()
        holder.binding.ingredientUnit.text = ingredientsList[position].unit
        holder.binding.ingredientConsistency.text = ingredientsList[position].consistency
        holder.binding.ingredientOriginal.text = ingredientsList[position].original
    }


    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    fun setData(newIngredients: List<ExtendedIngredientUiModel>) {
        val ingredientsDiffUtil =
            RecipesDiffUtil(ingredientsList, newIngredients)
        val diffUtilResult = DiffUtil.calculateDiff(ingredientsDiffUtil)
        ingredientsList = newIngredients
        diffUtilResult.dispatchUpdatesTo(this)
    }

}