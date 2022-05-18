package com.onjuno.assignment.crypto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onjuno.assignment.crypto.databinding.ActivityMainBinding

class HostActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setClickListeners()
    }

    private fun setClickListeners() {
        with(mBinding) {
            emptyStateBtn.setOnClickListener {
            }
            valueStateBtn.setOnClickListener {
            }
        }
    }
}