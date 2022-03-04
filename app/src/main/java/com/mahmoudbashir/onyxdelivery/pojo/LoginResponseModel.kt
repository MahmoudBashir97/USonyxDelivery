package com.mahmoudbashir.onyxdelivery.pojo

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("Data")
    val Data: Data,
    @SerializedName("Result")
    val Result: Result
)