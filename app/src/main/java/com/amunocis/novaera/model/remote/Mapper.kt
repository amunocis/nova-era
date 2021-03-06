package com.amunocis.novaera.model.remote

import com.amunocis.novaera.model.local.entity.DetailEntity
import com.amunocis.novaera.model.local.entity.PhoneEntity
import com.amunocis.novaera.model.remote.pojo.DetailWrapper
import com.amunocis.novaera.model.remote.pojo.PhoneWrapper

fun fromInternetToPhoneEntity(list: List<PhoneWrapper>): List<PhoneEntity> {
    val phoneList = mutableListOf<PhoneEntity>()
    list.map {
        phoneList.add(
            PhoneEntity(
                it.id,
                it.name,
                it.price,
                it.image
            )
        )
    }
    return phoneList
}

fun fromInternetToDetailEntity(phone: DetailWrapper, id: Int): DetailEntity {
    return DetailEntity(
        phone.id,
        phone.name,
        phone.price,
        phone.image,
        phone.description,
        phone.lastPrice,
        phone.credit
    )
}