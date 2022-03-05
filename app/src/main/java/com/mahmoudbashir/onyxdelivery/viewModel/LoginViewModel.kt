package com.mahmoudbashir.onyxdelivery.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mahmoudbashir.onyxdelivery.pojo.DeliveryModel
import com.mahmoudbashir.onyxdelivery.pojo.LoginResponseModel
import com.mahmoudbashir.onyxdelivery.repository.DeliveryRepository
import com.mahmoudbashir.onyxdelivery.utils.Resource
import kotlinx.coroutines.launch

class LoginViewModel(val app :Application,val deliveryRepo: DeliveryRepository): AndroidViewModel(app){

    val loginStatusResponse:MutableLiveData<Resource<LoginResponseModel>> = MutableLiveData()

    fun doLoginDelivery(model: DeliveryModel) =
        deliveryRepo.doLoginDelivery(model)
}