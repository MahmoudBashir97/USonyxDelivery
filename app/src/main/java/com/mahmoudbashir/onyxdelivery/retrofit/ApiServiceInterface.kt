package com.mahmoudbashir.onyxdelivery.retrofit

import com.mahmoudbashir.onyxdelivery.pojo.LoginModel
import com.mahmoudbashir.onyxdelivery.pojo.LoginResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServiceInterface {

    @POST("OnyxDeliveryService/Service.svc/CheckDeliveryLogin")
    suspend fun LoginUser(@Body loginModel: LoginModel):Response<LoginResponseModel>

}