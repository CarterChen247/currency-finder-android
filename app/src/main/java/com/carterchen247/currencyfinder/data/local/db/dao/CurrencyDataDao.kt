package com.carterchen247.currencyfinder.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carterchen247.currencyfinder.data.local.db.entity.CurrencyDataEntity

@Dao
interface CurrencyDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<CurrencyDataEntity>)

    @Query("SELECT * FROM CurrencyDataEntity")
    suspend fun selectAll(): List<CurrencyDataEntity>

    @Query("DELETE FROM CurrencyDataEntity")
    suspend fun deleteAll()
}