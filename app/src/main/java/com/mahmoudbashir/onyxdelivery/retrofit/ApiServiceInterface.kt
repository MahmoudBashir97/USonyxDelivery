package com.mahmoudbashir.onyxdelivery.retrofit

import com.mahmoudbashir.onyxdelivery.pojo.DeliveryModel
import com.mahmoudbashir.onyxdelivery.pojo.LoginResponseModel
import com.mahmoudbashir.onyxdelivery.pojo.billsDetailsModel.BillsDetailsResponseModel
import com.mahmoudbashir.onyxdelivery.pojo.billsModel.BillItemsModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServiceInterface {

    @POST("OnyxDeliveryService/Service.svc/CheckDeliveryLogin")
    fun LoginUser(@Body deliveryModel: DeliveryModel):Single<LoginResponseModel>

    @POST("OnyxDeliveryService/Service.svc/GetDeliveryBillsItems")
    fun GetBillsList(@Body deliveryModel: DeliveryModel):Observable<BillItemsModel>


    @POST("OnyxDeliveryService/Service.svc/GetDeliveryBillsItems")
    fun GetOrderDetails(@Body deliveryModel: DeliveryModel):Observable<BillsDetailsResponseModel>

}