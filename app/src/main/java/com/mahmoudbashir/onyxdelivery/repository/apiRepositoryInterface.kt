package com.mahmoudbashir.onyxdelivery.repository

import com.mahmoudbashir.onyxdelivery.pojo.LoginModel
import com.mahmoudbashir.onyxdelivery.pojo.LoginResponseModel
import com.mahmoudbashir.onyxdelivery.pojo.billsModel.BillItemsModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response

interface apiRepositoryInterface {
    suspend fun doLoginDelivery(loginModel: LoginModel):Response<LoginResponseModel>

    fun gettingBillsItem(loginModel: LoginModel):Observable<BillItemsModel>
}