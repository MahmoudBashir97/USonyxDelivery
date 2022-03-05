package com.mahmoudbashir.onyxdelivery.repository

import com.mahmoudbashir.onyxdelivery.pojo.DeliveryModel
import com.mahmoudbashir.onyxdelivery.pojo.LoginResponseModel
import com.mahmoudbashir.onyxdelivery.pojo.billsDetailsModel.BillsDetailsResponseModel
import com.mahmoudbashir.onyxdelivery.pojo.billsModel.BillItemsModel
import com.mahmoudbashir.onyxdelivery.retrofit.ApiServiceInterface
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class DeliveryRepository(private val apiService:ApiServiceInterface): apiRepositoryInterface{

    override fun doLoginDelivery(deliveryModel: DeliveryModel): Single<LoginResponseModel>
     = apiService.LoginUser(deliveryModel)

    override fun gettingBillsItem(deliveryModel: DeliveryModel): Observable<BillItemsModel>  =
        apiService.GetBillsList(deliveryModel)

    override fun gettingBillsDetailsItem(deliveryModel: DeliveryModel): Observable<BillsDetailsResponseModel> =
        apiService.GetOrderDetails(deliveryModel)

}