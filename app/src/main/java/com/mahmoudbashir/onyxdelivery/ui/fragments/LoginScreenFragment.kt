package com.mahmoudbashir.onyxdelivery.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.mahmoudbashir.onyxdelivery.R
import com.mahmoudbashir.onyxdelivery.databinding.FragmentLoginScreenBinding
import com.mahmoudbashir.onyxdelivery.ui.activities.MainActivity
import com.mahmoudbashir.onyxdelivery.viewModel.LoginViewModel
import org.koin.android.ext.android.inject
import kotlin.math.log


class LoginScreenFragment : Fragment() {
    lateinit var loginBinding : FragmentLoginScreenBinding
    val loginVM by inject<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        loginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login_screen, container, false)


        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       doTransitionWithShiwMoreLessBtn()
       navigateToHomeScreen()
    }

    private fun navigateToHomeScreen() {
        loginBinding.apply {
            loginBtn.setOnClickListener {
                if (validateFormsInput())
                findNavController().navigate(LoginScreenFragmentDirections.actionLoginScreenFragmentToHomeDeliveryOrdersFragment())
            }
        }
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

    private fun doTransitionWithShiwMoreLessBtn() {
        loginBinding.apply {
            showMoreLessBtn.setOnClickListener {
                if (hiddenView.visibility == View.VISIBLE){

                    TransitionManager.beginDelayedTransition(bigLin,
                        AutoTransition()
                    )
                    hiddenView.visibility = View.GONE
                    showMoreLessBtn.text = "Show More"
                }else{
                    TransitionManager.beginDelayedTransition(bigLin,
                        AutoTransition()
                    )
                    hiddenView.visibility = View.VISIBLE
                    showMoreLessBtn.text = "Show Less"
                }
            }
        }
    }


}