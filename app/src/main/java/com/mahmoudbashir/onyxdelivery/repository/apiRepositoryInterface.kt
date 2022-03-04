package com.mahmoudbashir.onyxdelivery.repository

import com.mahmoudbashir.onyxdelivery.pojo.LoginModel
import com.mahmoudbashir.onyxdelivery.pojo.LoginResponseModel
import com.mahmoudbashir.onyxdelivery.pojo.ModelL
import retrofit2.Response

interface apiRepositoryInterface {
    suspend fun doLoginDelivery(loginModel: ModelL):Response<LoginResponseModel>
}