package com.mahmoudbashir.onyxdelivery.ui.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.mahmoudbashir.onyxdelivery.R
import com.mahmoudbashir.onyxdelivery.databinding.FragmentLoginScreenBinding
import com.mahmoudbashir.onyxdelivery.local.SharedPreference
import com.mahmoudbashir.onyxdelivery.pojo.DeliveryModel
import com.mahmoudbashir.onyxdelivery.pojo.Value
import com.mahmoudbashir.onyxdelivery.ui.activities.MainActivity
import com.mahmoudbashir.onyxdelivery.utils.Resource
import com.mahmoudbashir.onyxdelivery.viewModel.LoginViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class LoginScreenFragment : Fragment() {
    lateinit var loginBinding : FragmentLoginScreenBinding
    val login_VM by inject<LoginViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
       //login_VM = (activity as MainActivity).loginVM
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (SharedPreference.getInastance(context).isLoggedIn){
            findNavController().navigate(LoginScreenFragmentDirections.actionLoginScreenFragmentToHomeDeliveryOrdersFragment())
        }
        loginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login_screen, container, false)


        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       doTransitionWithShowMoreLessBtn()
       navigateToHomeScreen()
    }

    // todo here we navigate to home after enter required inputs and validate on them,
    private fun navigateToHomeScreen() {
        loginBinding.apply {
            loginBtn.setOnClickListener {
                if (validateFormsInput()){
                    loginBinding.isLogging = true
                    doLogin()
                }else loginBinding.isLogging = false
            }
        }
    }

    private fun doLogin() {
        val  model = DeliveryModel(
            Value(
                loginBinding.edtUserId.text.toString(), "1", loginBinding.edtPassword.text.toString(),""
            )
        )

        login_VM.doLoginDelivery(model).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    when(response.Result.ErrNo){
                        0 -> {
                            loginBinding.isLogging = false
                            Log.d("loginStatus : ","Logged in ")
                            findNavController().navigate(LoginScreenFragmentDirections.actionLoginScreenFragmentToHomeDeliveryOrdersFragment())
                            SharedPreference.getInastance(context).saveDeliveryInfo(response.Data.DeliveryName,loginBinding.edtUserId.text.toString())
                        }
                        else -> {
                            loginBinding.isLogging = false
                            loginBinding.loginBtn.isEnabled = true
                            showErrorMessage(response.Result.ErrMsg)
                            showErrorMessage("please check your validate data ,or internet connection")
                        }
                    }
                },
                { throwable ->
                    loginBinding.isLogging = false
                    Log.e("errorMessage: ", throwable.message ?: "onError")
                }
            )

    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    private fun validateFormsInput():Boolean{
        val userId = loginBinding.edtUserId.text.toString()
        val userPassword = loginBinding.edtPassword.text.toString()
        if (TextUtils.isEmpty(userId)){
            loginBinding.edtUserId.error = "Please Enter a valid input"
            loginBinding.edtUserId.requestFocus()
            return false
        }else if (TextUtils.isEmpty(userPassword)){
            loginBinding.edtPassword.error = "Please Enter a valid input"
            loginBinding.edtPassword.requestFocus()
            return false
        }

        return true
    }

    // here we apply transition on inputs form that required in ui/ux design
    private fun doTransitionWithShowMoreLessBtn() {
        loginBinding.apply {
            showMoreLessBtn.setOnClickListener {
                if (hiddenView.visibility == View.VISIBLE){

                    TransitionManager.beginDelayedTransition(bigLin,
                        AutoTransition()
                    )
                    hiddenView.visibility = View.GONE
                    showMoreLessBtn.text = context?.resources?.getString(R.string.show_more_st)
                }else{
                    TransitionManager.beginDelayedTransition(bigLin,
                        AutoTransition()
                    )
                    hiddenView.visibility = View.VISIBLE
                    showMoreLessBtn.text = context?.resources?.getString(R.string.show_less_st)
                }
            }
        }
    }

}