package com.mahmoudbashir.onyxdelivery.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudbashir.onyxdelivery.databinding.SingleOrderPriceDetailsItemBinding
import com.mahmoudbashir.onyxdelivery.pojo.billsDetailsModel.DeliveryBillItem

class OrderDetailsAdapter : RecyclerView.Adapter<OrderDetailsAdapter.OrderViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<DeliveryBillItem>(){
        override fun areItemsTheSame(oldItem: DeliveryBillItem, newItem: DeliveryBillItem): Boolean {
            return oldItem.BILL_SRL == newItem.BILL_SRL
        }

        override fun areContentsTheSame(oldItem: DeliveryBillItem, newItem: DeliveryBillItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    //here we applying dataBinding on its SingleOrderItemLayout.xml and attaching passed data to its views
    inner class OrderViewHolder(private val binding: SingleOrderPriceDetailsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:DeliveryBillItem){
            with(binding){
                Log.d("mess : ","List : $item")

                itemTitleTxt.text = item.I_NM.substring(1,item.I_NM.length)
                itemQuantityTxt.text = "${item.I_QTY}x"
                itemPriceTxt.text = "${item.I_PRICE.split(".")[0]}LE"

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SingleOrderPriceDetailsItemBinding.inflate(inflater)

        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item  = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = differ.currentList.size
}