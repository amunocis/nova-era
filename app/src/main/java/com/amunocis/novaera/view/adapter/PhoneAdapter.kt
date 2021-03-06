package com.amunocis.novaera.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.amunocis.novaera.databinding.PhoneItemBinding
import com.amunocis.novaera.model.local.entity.PhoneEntity
import com.bumptech.glide.Glide

class PhoneAdapter: RecyclerView.Adapter<PhoneAdapter.PhoneVH>() {
    private var phoneList = listOf<PhoneEntity>()
    private val selectedPhone = MutableLiveData<PhoneEntity>()

    fun update(list: List<PhoneEntity>) {
        phoneList = list
        notifyDataSetChanged()
    }

    inner class PhoneVH(private val binding: PhoneItemBinding):
            RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(phone: PhoneEntity) {
            Glide.with(binding.root)
                .load("${phone.image}")
                .into(binding.phoneImage)
            binding.nameTextView.text = phone.name
            binding.priceTextView.text = phone.price.toString()
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedPhone.value = phoneList[adapterPosition]
        }
    }

    fun selectedPhone(): LiveData<PhoneEntity> = selectedPhone

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneVH {
        return PhoneVH(PhoneItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhoneVH, position: Int) {
        holder.bind(phoneList[position])
    }

    override fun getItemCount() = phoneList.size
}