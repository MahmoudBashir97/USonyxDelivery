package com.mahmoudbashir.onyxdelivery.repository

import com.mahmoudbashir.onyxdelivery.pojo.DeliveryModel
import com.mahmoudbashir.onyxdelivery.pojo.LoginResponseModel
import com.mahmoudbashir.onyxdelivery.pojo.billsDetailsModel.BillsDetailsResponseModel
import com.mahmoudbashir.onyxdelivery.pojo.billsModel.BillItemsModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface apiRepositoryInterface {
     fun doLoginDelivery(deliveryModel: DeliveryModel):Single<LoginResponseModel>


    fun gettingBillsItem(deliveryModel: DeliveryModel):Observable<BillItemsModel>

    fun gettingBillsDetailsItem(deliveryModel: DeliveryModel):Observable<BillsDetailsResponseModel>
}