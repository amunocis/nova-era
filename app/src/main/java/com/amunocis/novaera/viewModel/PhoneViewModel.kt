package com.amunocis.novaera.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.amunocis.novaera.model.PhoneDatabase
import com.amunocis.novaera.model.local.entity.PhoneEntity
import kotlinx.coroutines.launch
import com.amunocis.novaera.model.PhoneRepository
import com.amunocis.novaera.model.local.entity.DetailEntity

class PhoneViewModel(application: Application): AndroidViewModel(application) {
    private val repository: PhoneRepository
    private var phoneSelected = 0

    init {
        val db = PhoneDatabase.getDatabase(application)
        val phoneDao = db.phoneDao()
        repository = PhoneRepository(phoneDao)

        viewModelScope.launch {
            repository.fetchPhoneData()
        }
    }

    fun getPhoneList(): LiveData<List<PhoneEntity>> = repository.phoneListLiveData

    fun getPhoneById(id: Int): LiveData<DetailEntity> = repository.getPhoneById(id)

    fun getDetailByIdFromInternet(id: Int) = viewModelScope.launch {
        phoneSelected = id
        repository.fetchDetailData(id)
    }
}