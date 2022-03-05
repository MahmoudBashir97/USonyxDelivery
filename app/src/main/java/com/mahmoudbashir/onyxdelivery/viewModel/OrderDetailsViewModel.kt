package com.mahmoudbashir.onyxdelivery.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mahmoudbashir.onyxdelivery.pojo.DeliveryModel
import com.mahmoudbashir.onyxdelivery.pojo.billsDetailsModel.BillsDetailsResponseModel
import com.mahmoudbashir.onyxdelivery.repository.DeliveryRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class OrderDetailsViewModel(val app: Application, val repo : DeliveryRepository): AndroidViewModel(app)  {
    val billsDetailsItem : MutableLiveData<BillsDetailsResponseModel> = MutableLiveData()

    fun getBillsItemDetails(model: DeliveryModel){
        repo.gettingBillsDetailsItem(model)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response != null) billsDetailsItem.postValue(response)
                },
                { throwable ->

                    Log.e("errorMessage: ", throwable.message ?: "onError")
                }
            )
    }
}