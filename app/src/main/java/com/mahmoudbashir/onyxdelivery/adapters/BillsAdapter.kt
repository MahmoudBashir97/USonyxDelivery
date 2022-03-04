package com.mahmoudbashir.onyxdelivery.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudbashir.onyxdelivery.R
import com.mahmoudbashir.onyxdelivery.databinding.SingleOrdersItemLayoutBinding
import com.mahmoudbashir.onyxdelivery.pojo.billsModel.DeliveryBill

class BillsAdapter : RecyclerView.Adapter<BillsAdapter.ViewHolder>(){


    inner class ViewHolder(private val binding:SingleOrdersItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: DeliveryBill) {
            with(binding) {
                if (item.DLVRY_STATUS_FLG == "1") billStatusTxt.text ="New" else "Delivered"
                billAmountTxt.text = item.BILL_AMT.trim('.')
                billDateTxt.text = item.BILL_DATE
                billSerTxt.text =item.BILL_SRL
            }
        }
    }


    private val differCallback = object : DiffUtil.ItemCallback<DeliveryBill>(){
        override fun areItemsTheSame(oldItem: DeliveryBill, newItem: DeliveryBill): Boolean {
            return oldItem.BILL_NO == newItem.BILL_NO
        }

        override fun areContentsTheSame(oldItem: DeliveryBill, newItem: DeliveryBill): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = SingleOrdersItemLayoutBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    override fun getItemCount(): Int = differ.currentList.size
}