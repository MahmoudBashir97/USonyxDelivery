package com.mahmoudbashir.onyxdelivery.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.mahmoudbashir.onyxdelivery.R
import com.mahmoudbashir.onyxdelivery.adapters.BillsAdapter
import com.mahmoudbashir.onyxdelivery.adapters.ItemClickedInterface
import com.mahmoudbashir.onyxdelivery.databinding.FragmentHomeDeliveryOrdersBinding
import com.mahmoudbashir.onyxdelivery.local.SharedPreference
import com.mahmoudbashir.onyxdelivery.pojo.DeliveryModel
import com.mahmoudbashir.onyxdelivery.pojo.Value
import com.mahmoudbashir.onyxdelivery.pojo.billsModel.DeliveryBill
import com.mahmoudbashir.onyxdelivery.ui.activities.MainActivity
import com.mahmoudbashir.onyxdelivery.viewModel.OrdersViewModel


class HomeDeliveryOrdersFragment : Fragment() ,ItemClickedInterface{

    lateinit var deliveryBinding:FragmentHomeDeliveryOrdersBinding
    lateinit var ordersVM : OrdersViewModel
    lateinit var billsAdapter: BillsAdapter

    lateinit var newBillsList:ArrayList<DeliveryBill>
    lateinit var othersBillsList:ArrayList<DeliveryBill>



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


        setUpRecyclerview()
        getBillsItemList()
        setupDataToViews()
    }

    private fun getBillsItemList() {
        newBillsList = ArrayList()
        othersBillsList = ArrayList()
        deliveryBinding.isLoaded = false
        val model = DeliveryModel(
            Value(SharedPreference.getInastance(context).userId,
                "1","","")
        )

        ordersVM.getBillsItem(model)

        ordersVM.billsItem.observe(viewLifecycleOwner,{
                response ->
            when(response.Result.ErrNo){

                0 ->{
                    deliveryBinding.isLoaded = true
                    newBillsList.clear()
                    othersBillsList.clear()

                    response.Data.DeliveryBills.forEach {
                        if (it.DLVRY_STATUS_FLG == "1"){
                            newBillsList.add(it)
                        }else {
                            othersBillsList.add(it)
                        }
                    }



                }
                1 -> {
                    deliveryBinding.isLoaded = false
                    showErrorMessage("There is no data to display")
                }
                else ->{
                    deliveryBinding.isLoaded = false
                    showErrorMessage("some error occurred!!")
                }

            }

        })
    }

    private fun showErrorMessage(ErrMsg: String) {
        Toast.makeText(context,ErrMsg,Toast.LENGTH_LONG).show()
    }

    private fun setUpRecyclerview(){
        deliveryBinding.isLoaded = false
        billsAdapter  = BillsAdapter(requireContext().applicationContext,this)
        deliveryBinding.recOrders.apply {
            setHasFixedSize(true)
            adapter = billsAdapter
        }
    }

    private fun setupDataToViews() {
        val name = SharedPreference.getInastance(context).deliveryName
        deliveryBinding.firstNameTxt.text = name

        deliveryBinding.newRb.isChecked = true
        if (deliveryBinding.newRb.isChecked) billsAdapter.differ.submitList(newBillsList)
        if (deliveryBinding.othersRb.isChecked) billsAdapter.differ.submitList(othersBillsList)

        deliveryBinding.rgBtns.setOnCheckedChangeListener { _, checkedId ->

            if (checkedId == R.id.others_rb && othersBillsList.isNotEmpty()){
                billsAdapter.differ.submitList(othersBillsList)
            }else {
                billsAdapter.differ.submitList(newBillsList)
                deliveryBinding.recOrders.scrollToPosition(1)
            }
        }
    }

    override fun onClick(position: Int, item: DeliveryBill) {
        deliveryBinding.newRb.isChecked = true
        findNavController().navigate(HomeDeliveryOrdersFragmentDirections.actionHomeDeliveryOrdersFragmentToOrderDetailsFragment(item))
    }

}