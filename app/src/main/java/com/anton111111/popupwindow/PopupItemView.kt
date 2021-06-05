package com.anton111111.popupwindow

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.anton111111.popupwindow.databinding.PopupItemViewBinding

@SuppressLint("ViewConstructor")
class PopupItemView(context: Context, name: String) : FrameLayout(context) {
    private var viewBinding: PopupItemViewBinding =
        PopupItemViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        viewBinding.itemName.text = name
    }
}