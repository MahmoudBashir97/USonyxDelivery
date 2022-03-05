package com.mahmoudbashir.onyxdelivery.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudbashir.onyxdelivery.R
import com.mahmoudbashir.onyxdelivery.databinding.SingleOrdersItemLayoutBinding
import com.mahmoudbashir.onyxdelivery.pojo.billsModel.DeliveryBill
import com.mahmoudbashir.onyxdelivery.ui.common.CheckDeliveryStatusFlagClass

class BillsAdapter(val context:Context, val itemClickedInterface: ItemClickedInterface): RecyclerView.Adapter<BillsAdapter.ViewHolder>(){


    //here we applying dataBinding on its SingleOrderItemLayout.xml and attaching passed data to its views
    inner class ViewHolder(private val binding:SingleOrdersItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: DeliveryBill) {
            with(binding) {

                CheckDeliveryStatusFlagClass.setUpSomeOperationOnViews(context,item,billStatusTxt,toDetailsBtn)

                billAmountTxt.text = item.BILL_AMT.split(".")[0]
                billDateTxt.text = item.BILL_DATE
                billSerTxt.text ="#${item.BILL_SRL}"
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
        val item = differ.currentList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            itemClickedInterface.onClick(position,item)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}