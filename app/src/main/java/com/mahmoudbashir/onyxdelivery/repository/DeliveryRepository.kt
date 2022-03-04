package com.mahmoudbashir.onyxdelivery.repository

import com.mahmoudbashir.onyxdelivery.pojo.LoginModel
import com.mahmoudbashir.onyxdelivery.pojo.LoginResponseModel
import com.mahmoudbashir.onyxdelivery.pojo.ModelL
import com.mahmoudbashir.onyxdelivery.retrofit.ApiServiceInterface
import com.mahmoudbashir.onyxdelivery.retrofit.RetrofitInstance
import retrofit2.Response

class DeliveryRepository(val retrofit:RetrofitInstance): apiRepositoryInterface{
    override suspend fun doLoginDelivery(loginModel: ModelL): Response<LoginResponseModel>
     = retrofit.api.LoginUser(loginModel)
}