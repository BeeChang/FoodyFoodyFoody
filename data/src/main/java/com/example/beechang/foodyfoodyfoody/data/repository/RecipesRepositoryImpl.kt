package com.example.beechang.foodyfoodyfoody.data.repository

import com.example.beechang.foodyfoodyfoody.model.Result
import com.example.beechang.foodyfoodyfoody.network.Dispatcher
import com.example.beechang.foodyfoodyfoody.network.FoodRecipesRetrofitClient
import com.example.beechang.foodyfoodyfoody.network.FoodyDispatchers
import com.example.beechang.foodyfoodyfoody.data.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow

class RecipesRepositoryImpl @Inject constructor(
    private val recipesClient: FoodRecipesRetrofitClient,
    @Dispatcher(FoodyDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : RecipesRepository {


    override fun getRecipes(
        searchWord: String,
    ): Flow<List<Result>> = flow {
        val result = recipesClient.fetchFoodRecipes(searchWord).asResult()
        if (result.isSuccess) {
            result.getData?.results?.let { emit(it) }
        } else {
            result.getFail?.let {
                throw it.error
            }
        }
    }.flowOn(ioDispatcher)
}

