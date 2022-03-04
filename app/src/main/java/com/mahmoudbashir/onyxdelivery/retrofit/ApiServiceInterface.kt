package com.mahmoudbashir.onyxdelivery.retrofit

import com.mahmoudbashir.onyxdelivery.pojo.LoginModel
import com.mahmoudbashir.onyxdelivery.pojo.LoginResponseModel
import com.mahmoudbashir.onyxdelivery.pojo.billsModel.BillItemsModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServiceInterface {

    @POST("OnyxDeliveryService/Service.svc/CheckDeliveryLogin")
    suspend fun LoginUser(@Body loginModel: LoginModel):Response<LoginResponseModel>

    @POST("OnyxDeliveryService/Service.svc/GetDeliveryBillsItems")
    fun GetBillsList(@Body loginModel: LoginModel):Observable<BillItemsModel>

}