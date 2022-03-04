package com.mahmoudbashir.onyxdelivery.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mahmoudbashir.onyxdelivery.pojo.LoginModel
import com.mahmoudbashir.onyxdelivery.pojo.LoginResponseModel
import com.mahmoudbashir.onyxdelivery.pojo.ModelL
import com.mahmoudbashir.onyxdelivery.repository.DeliveryRepository
import kotlinx.coroutines.launch

class LoginViewModel(val app :Application,val deliveryRepo: DeliveryRepository): AndroidViewModel(app){

    val loginStatusResponse:MutableLiveData<LoginResponseModel> = MutableLiveData()

    fun doLoginDelivery(model:ModelL) = viewModelScope.launch {
        deliveryRepo.doLoginDelivery(model).body()?.also{ response ->
            if (response != null && response.Result.ErrNo == 0){
             loginStatusResponse.postValue(response)
            }
        }
    }
}