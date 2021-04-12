package com.example.layouts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.layouts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // var qui sera utilisée plus tard
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        // required to update UI with data
        binding.lifecycleOwner = this

        setContentView(binding.root)
        val dataChange = DataChange()
        binding.nbClick = dataChange
        binding.myButton.setOnClickListener {
            dataChange.newData()
        }
    }
}
