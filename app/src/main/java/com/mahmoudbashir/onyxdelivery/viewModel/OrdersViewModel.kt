package com.mahmoudbashir.onyxdelivery.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mahmoudbashir.onyxdelivery.pojo.LoginModel
import com.mahmoudbashir.onyxdelivery.pojo.billsModel.BillItemsModel
import com.mahmoudbashir.onyxdelivery.repository.DeliveryRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class OrdersViewModel(val app:Application,val repo :DeliveryRepository): AndroidViewModel(app) {

      val billsItem : MutableLiveData<BillItemsModel> = MutableLiveData()

    fun getBillsItem(model:LoginModel){

        repo.gettingBillsItem(model)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response != null) billsItem.postValue(response)
                },
                { throwable ->

                    Log.e("errorMessage: ", throwable.message ?: "onError")
                }
            )
    }
}