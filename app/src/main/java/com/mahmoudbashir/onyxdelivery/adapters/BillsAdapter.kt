package com.mahmoudbashir.onyxdelivery.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudbashir.onyxdelivery.R
import com.mahmoudbashir.onyxdelivery.pojo.billsModel.DeliveryBill

class BillsAdapter : RecyclerView.Adapter<BillsAdapter.ViewHolder>(){

    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

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
        return ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.single_orders_item_layout,
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.apply {

        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}