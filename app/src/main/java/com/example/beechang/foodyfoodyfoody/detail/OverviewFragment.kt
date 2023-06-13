package com.example.beechang.foodyfoodyfoody.detail


import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.beechang.foodyfoodyfoody.R
import com.example.beechang.foodyfoodyfoody.base.BaseFragment
import com.example.beechang.foodyfoodyfoody.databinding.FragmentOverviewBinding
import com.example.beechang.foodyfoodyfoody.model.Result
import org.jsoup.Jsoup

class OverviewFragment : BaseFragment<FragmentOverviewBinding>(R.layout.fragment_overview) {

    override fun initView() {
        val args = arguments
        val myBundle: Result? = args?.getParcelable("recipeBundle")

        Glide.with(requireActivity())
            .load(myBundle?.image)
            .into(binding.mainImageView)

        binding.titleTextView.text = myBundle?.title
        binding.likesTextView.text = myBundle?.aggregateLikes.toString()
        binding.timeTextView.text = myBundle?.readyInMinutes.toString()
        myBundle?.summary.let {
            binding.summaryTextView.text = Jsoup.parse(it).text()
        }

        if(myBundle?.vegetarian == true){
            binding.vegetarianImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            binding.vegetarianTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }

        if(myBundle?.vegan == true){
            binding.veganImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            binding.veganTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }

        if(myBundle?.glutenFree == true){
            binding.glutenFreeImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            binding.glutenFreeTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }

        if(myBundle?.dairyFree == true){
            binding.dairyFreeImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            binding.dairyFreeTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }

        if(myBundle?.veryHealthy == true){
            binding.healthyImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            binding.healthyTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }

        if(myBundle?.cheap == true){
            binding.cheapImageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            binding.cheapTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }

    }

}