package com.amunocis.novaera.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.amunocis.novaera.model.local.dao.PhoneDao
import com.amunocis.novaera.model.local.entity.DetailEntity
import com.amunocis.novaera.model.remote.RetrofitInstance
import com.amunocis.novaera.model.remote.fromInternetToDetailEntity
import com.amunocis.novaera.model.remote.fromInternetToPhoneEntity

class PhoneRepository(private val phoneDao: PhoneDao) {
    private val networkService = RetrofitInstance.retrofitInstance()
    val phoneListLiveData = phoneDao.getPhoneList()

    suspend fun fetchPhoneData() {
        val service = kotlin.runCatching { networkService.fetchPhoneList() }
        service.onSuccess {
            when(it.code()) {
                200 -> it.body()?.let {
                    phoneDao.insertPhoneList(fromInternetToPhoneEntity(it))
                }
                else -> Log.d("REPO", "${it.errorBody()}")
            }
        }
        service.onFailure {
            Log.e("REPO", "${it.message}")
        }
    }

    suspend fun fetchDetailData(id: Int) {
        val service = kotlin.runCatching { networkService.fetchPhoneDetail(id) }
        service.onSuccess {
            when(it.code()) {
                200 -> it.body()?.let {
                    phoneDao.insertPhoneDetail(fromInternetToDetailEntity(it, id))
                }
            }
        }
    }

    fun getPhoneById(id: Int): LiveData<DetailEntity> = phoneDao.getDetailByPhoneId(id)
}