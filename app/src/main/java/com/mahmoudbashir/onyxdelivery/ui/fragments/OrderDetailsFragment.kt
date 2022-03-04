package com.mahmoudbashir.onyxdelivery.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.mahmoudbashir.onyxdelivery.R
import com.mahmoudbashir.onyxdelivery.databinding.FragmentOrderDetailsBinding


class OrderDetailsFragment : Fragment() {

    lateinit var detailsBinding: FragmentOrderDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        detailsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_order_details, container, false)

        return detailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backToPrevScreen()
    }

    private fun backToPrevScreen() {
        detailsBinding.apply {
            backBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

}