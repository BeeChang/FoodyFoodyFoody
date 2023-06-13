package com.example.beechang.foodyfoodyfoody.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.beechang.foodyfoodyfoody.test.MainDispatcherRule
import com.example.beechang.foodyfoodyfoody.test.MockData
import com.example.foodyfoody.database.TypeResponseConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RecipesDatabaseTest {

    lateinit var db: RecipesDatabase
    lateinit var recipesDao: RecipesDao

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, RecipesDatabase::class.java)
            .addTypeConverter(TypeResponseConverter(Moshi.Builder().build()))
            .build()

        recipesDao = db.recipesDao()
    }

    @After
    fun close() {
        db.close()
    }

    @Test
    fun `WHEN insert a favorite recipe at recipe database EXPECT create a row in field`() = runBlocking{
        val mockData = MockData.mockFavorites().asEntity()
        recipesDao.insertFavoriteRecipe(mockData)

        val savedMockData = recipesDao.readFavoriteRecipes()
        MatcherAssert.assertThat(savedMockData.toString(), `is`(savedMockData.toString()))
    }

    @Test
    fun `WHEN delete a favorite recipe from the recipe database EXPECT the row to be removed`() = runBlocking {
        val mockData = MockData.mockFavorites().asEntity()
        recipesDao.insertFavoriteRecipe(mockData)
        recipesDao.deleteFavoriteRecipe(mockData)

        val savedMockData = recipesDao.readFavoriteRecipes()
        assertTrue(!savedMockData.contains(mockData))
    }


}