package com.mahmoudbashir.onyxdelivery.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.mahmoudbashir.onyxdelivery.R
import com.mahmoudbashir.onyxdelivery.databinding.FragmentHomeDeliveryOrdersBinding
import com.mahmoudbashir.onyxdelivery.local.SharedPreference
import com.mahmoudbashir.onyxdelivery.pojo.LoginModel
import com.mahmoudbashir.onyxdelivery.pojo.Value
import com.mahmoudbashir.onyxdelivery.ui.activities.MainActivity
import com.mahmoudbashir.onyxdelivery.viewModel.OrdersViewModel


class HomeDeliveryOrdersFragment : Fragment() {

    lateinit var deliveryBinding:FragmentHomeDeliveryOrdersBinding
    lateinit var ordersVM : OrdersViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ordersVM = (activity as MainActivity).ordersVM
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        deliveryBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home_delivery_orders, container, false)

        return deliveryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDataToViews()
        val model = LoginModel(
            Value(SharedPreference.getInastance(context).userId,
                "1","")
        )
        ordersVM.getBillsItem(model)
        ordersVM.billsItem.observe(viewLifecycleOwner,{
            response ->
            if (response.Result.ErrNo == 0) {
                Toast.makeText(context,"Data Received!! ", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupDataToViews() {
        val name = SharedPreference.getInastance(context).deliveryName
        deliveryBinding.firstNameTxt.text = name
    }

}