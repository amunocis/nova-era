package com.amunocis.novaera.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.amunocis.novaera.R
import com.amunocis.novaera.databinding.FragmentSecondBinding
import com.amunocis.novaera.viewModel.PhoneViewModel
import com.bumptech.glide.Glide

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: PhoneViewModel by activityViewModels()
    private var idPhone: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idPhone = it.getInt("id", -1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPhoneById(idPhone).observe(viewLifecycleOwner, Observer {
            it?.let {
                val creditCheck: String
                if (it.credit) {
                    creditCheck = "Acepta Crédito"
                } else {
                    creditCheck = "Solo Efectivo"
                }

                binding.nameTextView.text = it.name
                binding.lastPriceTextView.text = it.lastPrice.toString()
                binding.priceTextView.text = it.price.toString()
                binding.creditTextView.text = creditCheck
                binding.descriptionTextView.text = it.description
                Glide.with(this)
                    .load(it.image)
                    .into(binding.phoneImageDetail)
            }
        })
    }
}