package com.mahmoudbashir.onyxdelivery.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mahmoudbashir.onyxdelivery.R
import com.mahmoudbashir.onyxdelivery.databinding.FragmentOrderDetailsBinding
import com.mahmoudbashir.onyxdelivery.pojo.billsModel.DeliveryBill
import com.mahmoudbashir.onyxdelivery.ui.common.TransitionClass


class OrderDetailsFragment : Fragment() {

    lateinit var detailsBinding: FragmentOrderDetailsBinding

    val args:OrderDetailsFragmentArgs by navArgs()
    var item: DeliveryBill? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        item = args.billItem

    }

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
        setUpDataToViews()
    }

    private fun setUpDataToViews() {
        detailsBinding.apply {

            customerNameTxt.text = item?.CSTMR_NM
            orderNoTxt.text = item?.BILL_NO
            orderDate.text = item?.BILL_DATE
            payOptionTxt.text = "Cash on Delivery"

            locationAddressTxt.text = "${item?.CSTMR_BUILD_NO} , ${item?.RGN_NM} , ${item?.CSTMR_ADDRSS}"
            locationPhoneTxt.text = item?.MOBILE_NO

        }
    }

    private fun workOnTransitionForViews() {
        detailsBinding.apply {
            relCutomerDetails.setOnClickListener {
                TransitionClass.doTransition(customerDetailsLin,upDownBtn,bigLin)
            }
            relOrderDetails.setOnClickListener {
                TransitionClass.doTransition(orderDetailsLin,orderDetailsUpDownBtn,bigLin)
            }
            relOrderLocation.setOnClickListener {
                TransitionClass.doTransition(orderLocationLin,orderLocationUpDownBtn,bigLin)
            }
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