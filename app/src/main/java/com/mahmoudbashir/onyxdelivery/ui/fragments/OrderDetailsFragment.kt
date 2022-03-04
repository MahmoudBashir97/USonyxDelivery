package com.mahmoudbashir.onyxdelivery.ui.fragments

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
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
        workOnTransitionForViews()
    }

    private fun workOnTransitionForViews() {
        detailsBinding.apply {
            relCutomerDetails.setOnClickListener {
                doCommonTransition(customerDetailsLin,upDownBtn,bigLin)
            }
            relOrderDetails.setOnClickListener {
                doCommonTransition(orderDetailsLin,orderDetailsUpDownBtn,bigLin)
            }
            relOrderLocation.setOnClickListener {
                doCommonTransition(orderLocationLin,orderLocationUpDownBtn,bigLin)
            }
        }
    }

    private fun doCommonTransition(hiddenView: LinearLayout, upDownBtn: ImageView, bigLin: LinearLayout) {
        if (hiddenView.visibility == View.VISIBLE){

            TransitionManager.beginDelayedTransition(bigLin,
                AutoTransition()
            )
            hiddenView.visibility = View.GONE
            upDownBtn.setImageResource(R.drawable.ic_listdown)
        }else{
            TransitionManager.beginDelayedTransition(bigLin,
                AutoTransition()
            )
            hiddenView.visibility = View.VISIBLE
            upDownBtn.setImageResource(R.drawable.ic_listup)
        }
    }

    private fun backToPrevScreen() {
        detailsBinding.apply {
            backBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

}