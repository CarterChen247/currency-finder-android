package com.carterchen247.currencyfinder.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carterchen247.currencyfinder.data.local.db.dao.CurrencyDataDao
import com.carterchen247.currencyfinder.data.local.db.entity.CurrencyDataEntity

@Database(
    entities = [
        CurrencyDataEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDataDao(): CurrencyDataDao

    companion object {
        const val DB_NAME = "currencyfinder.db"
    }
}