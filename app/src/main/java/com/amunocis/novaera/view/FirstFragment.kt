package com.amunocis.novaera.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.amunocis.novaera.R
import com.amunocis.novaera.databinding.FragmentFirstBinding
import com.amunocis.novaera.view.adapter.PhoneAdapter
import com.amunocis.novaera.viewModel.PhoneViewModel

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private val viewModel: PhoneViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PhoneAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        viewModel.getPhoneList().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.update(it)
            }
        })

        adapter.selectedPhone().observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "Hola Mundo", Toast.LENGTH_LONG)

            it?.let {
                viewModel.getDetailByIdFromInternet(it.id)
                val bundle = Bundle()
                bundle.putInt("id", it.id)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        })
    }
}