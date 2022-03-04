package com.mahmoudbashir.onyxdelivery.repository

import com.mahmoudbashir.onyxdelivery.pojo.LoginModel
import com.mahmoudbashir.onyxdelivery.pojo.LoginResponseModel
import com.mahmoudbashir.onyxdelivery.retrofit.ApiServiceInterface
import retrofit2.Response

class DeliveryRepository(val apiService:ApiServiceInterface): apiRepositoryInterface{
    override suspend fun doLoginDelivery(loginModel: LoginModel): Response<LoginResponseModel>
     = apiService.LoginUser(loginModel)
}