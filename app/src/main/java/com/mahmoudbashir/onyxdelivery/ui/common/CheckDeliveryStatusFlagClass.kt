package com.mahmoudbashir.onyxdelivery.ui.common

import android.content.Context
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.mahmoudbashir.onyxdelivery.R
import com.mahmoudbashir.onyxdelivery.pojo.billsModel.DeliveryBill

class CheckDeliveryStatusFlagClass {
    companion object{
        fun setUpSomeOperationOnViews(context:Context,item: DeliveryBill, billStatusTxt: TextView, toDetailsBtn: RelativeLayout) {
            when(item.DLVRY_STATUS_FLG){
                "1"-> {
                    billStatusTxt.text = context.resources.getString(R.string.new_st)
                    billStatusTxt.setTextColor(ContextCompat.getColor(context, R.color.greenItemColor))
                    toDetailsBtn.setBackgroundResource(R.drawable.green_order_details)
                }
                "2" -> {
                    billStatusTxt.text = context.resources.getString(R.string.delivered_st)
                    billStatusTxt.setTextColor(ContextCompat.getColor(context, R.color.redItemColor))
                    toDetailsBtn.setBackgroundResource(R.drawable.red_order_details)
                }
                else -> {
                    billStatusTxt.text = context.resources.getString(R.string.returned_st)
                    billStatusTxt.setTextColor(ContextCompat.getColor(context, R.color.welcomeColor))
                    toDetailsBtn.setBackgroundResource(R.drawable.dark_order_details)
                }
            }
        }
    }
}