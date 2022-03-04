package com.mahmoudbashir.onyxdelivery.adapters

import com.mahmoudbashir.onyxdelivery.pojo.billsModel.DeliveryBill

interface ItemClickedInterface {
    fun onClick(position:Int,item:DeliveryBill)
}