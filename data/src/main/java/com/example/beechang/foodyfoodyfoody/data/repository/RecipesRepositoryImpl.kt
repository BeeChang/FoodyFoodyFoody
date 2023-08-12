package com.example.beechang.foodyfoodyfoody.data.repository

import com.example.beechang.foodyfoodyfoody.data.provideErrorType
import com.example.beechang.foodyfoodyfoody.model.ApiException
import com.example.beechang.foodyfoodyfoody.model.Result
import com.example.beechang.foodyfoodyfoody.network.Dispatcher
import com.example.beechang.foodyfoodyfoody.network.FoodRecipesRetrofitClient
import com.example.beechang.foodyfoodyfoody.network.FoodyDispatchers
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class RecipesRepositoryImpl @Inject constructor(
    private val recipesClient: FoodRecipesRetrofitClient,
    @Dispatcher(FoodyDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : RecipesRepository {
    override fun getRecipes(
        searchWord: String,
    ): Flow<List<Result>> = flow {

        recipesClient.fetchFoodRecipes(searchWord).suspendOnSuccess {
            emit(data.results)
        }.suspendOnError {
            throw provideErrorType(response.code() , response.errorBody()?.string() , response.message() )
        }.suspendOnException {
            throw ApiException.OthersError(this.message.toString())
        }

    }.flowOn(ioDispatcher)
}

