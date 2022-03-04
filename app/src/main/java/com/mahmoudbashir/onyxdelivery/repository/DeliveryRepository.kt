package com.mahmoudbashir.onyxdelivery.repository

import com.mahmoudbashir.onyxdelivery.pojo.LoginModel
import com.mahmoudbashir.onyxdelivery.pojo.LoginResponseModel
import com.mahmoudbashir.onyxdelivery.pojo.billsDetailsModel.BillsDetailsResponseModel
import com.mahmoudbashir.onyxdelivery.pojo.billsModel.BillItemsModel
import com.mahmoudbashir.onyxdelivery.retrofit.ApiServiceInterface
import com.mahmoudbashir.onyxdelivery.retrofit.RetrofitInstance
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response

class DeliveryRepository(private val apiService:ApiServiceInterface): apiRepositoryInterface{
    override suspend fun doLoginDelivery(loginModel: LoginModel): Response<LoginResponseModel>
     = apiService.LoginUser(loginModel)

    override fun gettingBillsItem(loginModel: LoginModel): Observable<BillItemsModel>  =
        apiService.GetBillsList(loginModel)

    override fun gettingBillsDetailsItem(loginModel: LoginModel): Observable<BillsDetailsResponseModel> =
        apiService.GetOrderDetails(loginModel)

}