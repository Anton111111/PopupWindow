package com.anton111111.popupwindow

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.ScrollView
import com.anton111111.popupwindow.databinding.RowViewBinding

@SuppressLint("ViewConstructor")
class RowView(
    context: Context,
    private val popupItemClickCallback: (item: String) -> Unit
) : FrameLayout(context) {
    private var popupItems: List<String>? = null
    private var viewBinding: RowViewBinding =
        RowViewBinding.inflate(LayoutInflater.from(context), this, true)

    val popupMaxHeight = 400.toPx

    init {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        viewBinding.time.setOnClickListener { showPopup() }
    }

    fun bind(name: String, popupItems: List<String>) {
        this.popupItems = popupItems
        viewBinding.name.text = name
    }

    private fun showPopup() {
        popupItems?.let { items ->
            PopupWindow().apply {
                val listView = createList(this, items).apply {
                    measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
                }
                //To close on touch outside
                isOutsideTouchable = true
                contentView = listView
                width = listView.measuredWidth
                height = if (listView.measuredHeight > popupMaxHeight)
                    popupMaxHeight else listView.measuredHeight
            }.showAsDropDown(viewBinding.time)
        }
    }

    private fun createList(popup: PopupWindow, popupItems: List<String>) =
        ScrollView(context).apply {
            layoutParams = LayoutParams(WRAP_CONTENT, MATCH_PARENT)
            addView(LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                popupItems.forEach { item ->
                    addView(PopupItemView(context, item).apply {
                        setOnClickListener {
                            popup.dismiss()
                            popupItemClickCallback(item)
                        }
                    })
                }
            })
        }

}