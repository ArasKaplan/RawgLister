package com.example.trendyol_intern_project_v2.data.local.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.trendyol_intern_project_v2.common.DBModelDetails

@Dao
interface ModelDAO {
    @Insert
    suspend fun insertIntoWishlist(dbModelDetails: DBModelDetails)

    @Query("SELECT * FROM dbmodel")
    suspend fun getAllFromWishlist():List<DBModelDetails>

    @Query("SELECT * FROM dbmodel WHERE id = :uuid")
    suspend fun checkIfWishlist(uuid:Int):DBModelDetails?

    @Query("DELETE FROM dbmodel WHERE id = :uuid")
    suspend fun deleteFromWishlist(uuid: Int)

}