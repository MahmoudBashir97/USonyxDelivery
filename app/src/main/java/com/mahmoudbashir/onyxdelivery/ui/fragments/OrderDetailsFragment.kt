package com.mahmoudbashir.onyxdelivery.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mahmoudbashir.onyxdelivery.R
import com.mahmoudbashir.onyxdelivery.adapters.OrderDetailsAdapter
import com.mahmoudbashir.onyxdelivery.databinding.FragmentOrderDetailsBinding
import com.mahmoudbashir.onyxdelivery.local.SharedPreference
import com.mahmoudbashir.onyxdelivery.pojo.LoginModel
import com.mahmoudbashir.onyxdelivery.pojo.Value
import com.mahmoudbashir.onyxdelivery.pojo.billsModel.DeliveryBill
import com.mahmoudbashir.onyxdelivery.ui.activities.MainActivity
import com.mahmoudbashir.onyxdelivery.ui.common.TransitionClass
import com.mahmoudbashir.onyxdelivery.viewModel.OrderDetailsViewModel
import org.koin.android.ext.android.inject


class OrderDetailsFragment : Fragment() {

    lateinit var detailsBinding: FragmentOrderDetailsBinding
    val orderVM by inject<OrderDetailsViewModel>()
    lateinit var orderDetailsAdapter: OrderDetailsAdapter

    val args:OrderDetailsFragmentArgs by navArgs()
    var item: DeliveryBill? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        orderDetailsAdapter = OrderDetailsAdapter()
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
        observingOnOrderDetails()
    }

    private fun setUpRecyclerView() {
        detailsBinding.recOrderDetails.apply {
            setHasFixedSize(true)
            adapter = orderDetailsAdapter
        }
    }

    private fun observingOnOrderDetails() {
        val model = LoginModel(
            Value(
                SharedPreference.getInastance(context).userId,
                "1",
                "",
                item?.BILL_SRL.toString()
            )
        )

        orderVM.getBillsItemDetails(model)
        orderVM.billsDetailsItem.observe(viewLifecycleOwner,{
            responseModel ->
            when(responseModel.Result.ErrNo){

                0 ->{
                    Log.d("mess : ","List : ${responseModel.Data.DeliveryBillItems}")
                    setUpRecyclerView()
                    orderDetailsAdapter.differ.submitList(responseModel.Data.DeliveryBillItems)
                }
                1 -> {

                    showErrorMessage("There is no data to display")
                }
                else ->{

                    showErrorMessage("some error occurred!!")
                }
            }
        })
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

    private fun setUpDataToViews() {
        detailsBinding.apply {

            customerNameTxt.text = item?.CSTMR_NM
            orderNoTxt.text = item?.BILL_NO
            orderDate.text = item?.BILL_DATE
            payOptionTxt.text = "Cash on Delivery"
            deliveryTxt.text = item?.DLVRY_AMT
            taxesTxt.text = item?.TAX_AMT?.split(".")?.get(0) ?: ""

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