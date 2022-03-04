package com.mahmoudbashir.onyxdelivery.repository

import com.mahmoudbashir.onyxdelivery.pojo.LoginModel
import com.mahmoudbashir.onyxdelivery.pojo.LoginResponseModel
import com.mahmoudbashir.onyxdelivery.retrofit.RetrofitInstance
import retrofit2.Response

class DeliveryRepository(val retrofit:RetrofitInstance): apiRepositoryInterface{
    override suspend fun doLoginDelivery(loginModel: LoginModel): Response<LoginResponseModel>
     = retrofit.api.LoginUser(loginModel)


}