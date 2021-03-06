package com.amunocis.novaera.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amunocis.novaera.model.local.entity.DetailEntity
import com.amunocis.novaera.model.local.entity.PhoneEntity

@Dao
interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoneList(phoneList: List<PhoneEntity>)

    @Query("SELECT * FROM phone_table ORDER BY id ASC")
    fun getPhoneList(): LiveData<List<PhoneEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoneDetail(phoneDetail: DetailEntity)

    @Query("SELECT * FROM detail_table WHERE id = :id")
    fun getDetailByPhoneId(id: Int): LiveData<DetailEntity>
}