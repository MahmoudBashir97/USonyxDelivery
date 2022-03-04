package com.mahmoudbashir.onyxdelivery.ui.common

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.mahmoudbashir.onyxdelivery.R

class TransitionClass {
    companion object{
        fun doTransition(hiddenView: LinearLayout, upDownBtn: ImageView, bigLin: LinearLayout) {
            if (hiddenView.visibility == View.VISIBLE){

                TransitionManager.beginDelayedTransition(bigLin,
                    AutoTransition()
                )
                hiddenView.visibility = View.GONE
                upDownBtn.setImageResource(R.drawable.ic_listdown)
            }else{
                TransitionManager.beginDelayedTransition(bigLin,
                    AutoTransition()
                )
                hiddenView.visibility = View.VISIBLE
                upDownBtn.setImageResource(R.drawable.ic_listup)
            }
        }
    }
}