package com.mahmoudbashir.onyxdelivery.repository

import com.mahmoudbashir.onyxdelivery.pojo.LoginModel
import com.mahmoudbashir.onyxdelivery.pojo.LoginResponseModel
import com.mahmoudbashir.onyxdelivery.pojo.billsModel.BillItemsModel
import com.mahmoudbashir.onyxdelivery.retrofit.RetrofitInstance
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response

class DeliveryRepository(val retrofit:RetrofitInstance): apiRepositoryInterface{
    override suspend fun doLoginDelivery(loginModel: LoginModel): Response<LoginResponseModel>
     = retrofit.api.LoginUser(loginModel)

    override fun gettingBillsItem(loginModel: LoginModel): Observable<BillItemsModel>  =
        retrofit.api.GetBillsList(loginModel)


}