package com.anton111111.popupwindow

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anton111111.popupwindow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _viewBinding: ActivityMainBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initRV()
    }

    private fun initRV() {
        viewBinding.list.adapter = RVAdapter() { clickedItem ->
            Toast.makeText(this, "Clicked item: $clickedItem", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}