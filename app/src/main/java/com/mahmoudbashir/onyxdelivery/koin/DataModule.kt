package com.mahmoudbashir.onyxdelivery.koin

import com.mahmoudbashir.onyxdelivery.repository.DeliveryRepository
import com.mahmoudbashir.onyxdelivery.repository.apiRepositoryInterface
import com.mahmoudbashir.onyxdelivery.retrofit.ApiServiceInterface
import com.mahmoudbashir.onyxdelivery.retrofit.RetrofitInstance
import com.mahmoudbashir.onyxdelivery.utils.Constants
import com.mahmoudbashir.onyxdelivery.viewModel.LoginViewModel
import com.mahmoudbashir.onyxdelivery.viewModel.OrdersViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single {
        getRetrofitInstance()
    }

    single {
        getApiInterface(get())
    }

    single { RetrofitInstance() }
    single{
        DeliveryRepository(get())
    }

}

val mainViewModel = module {
    viewModel {
        LoginViewModel(androidApplication(),get())
    }
}
val orderViewModel = module {
    viewModel {
        OrdersViewModel(androidApplication(),get())
    }
}

fun getRetrofitInstance():Retrofit{
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client)
        .build()
}
fun getApiInterface(retrofit: Retrofit):ApiServiceInterface = retrofit.create(ApiServiceInterface::class.java)