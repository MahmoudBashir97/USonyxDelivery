package com.mahmoudbashir.onyxdelivery.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mahmoudbashir.onyxdelivery.R
import com.mahmoudbashir.onyxdelivery.databinding.FragmentHomeDeliveryOrdersBinding
import com.mahmoudbashir.onyxdelivery.local.SharedPreference


class HomeDeliveryOrdersFragment : Fragment() {



    lateinit var deliveryBinding:FragmentHomeDeliveryOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        deliveryBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home_delivery_orders, container, false)

        return deliveryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDataToViews()
    }

    private fun setupDataToViews() {
        val name = SharedPreference.getInastance(context).deliveryName
        deliveryBinding.firstNameTxt.text = name
    }

}