package com.example.beechang.foodyfoodyfoody.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


val migration1to2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE ${RECIPES_TABLE} RENAME TO temp_${RECIPES_TABLE}")
        database.execSQL("ALTER TABLE ${FAVORITE_RECIPES_TABLE} RENAME TO temp_${FAVORITE_RECIPES_TABLE}")

        database.execSQL("""
            CREATE TABLE ${RECIPES_TABLE} (
                id INTEGER PRIMARY KEY NOT NULL,
                foodRecipe TEXT NOT NULL
            )
        """)

        database.execSQL("""
            CREATE TABLE ${FAVORITE_RECIPES_TABLE} (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                foodyResult TEXT NOT NULL
            )
        """)

        database.execSQL("""
            INSERT INTO ${RECIPES_TABLE} (id, foodRecipe)
            SELECT id, foodRecipe FROM temp_${RECIPES_TABLE}
        """)

        database.execSQL("""
            INSERT INTO ${FAVORITE_RECIPES_TABLE} (id, foodyResult)
            SELECT id, foodyResult FROM temp_${FAVORITE_RECIPES_TABLE} -- Fix this line
        """)

        database.execSQL("DROP TABLE temp_${RECIPES_TABLE}")
        database.execSQL("DROP TABLE temp_${FAVORITE_RECIPES_TABLE}")
    }
}