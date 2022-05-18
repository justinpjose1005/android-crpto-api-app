package com.onjuno.assignment.crypto.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onjuno.assignment.crypto.R
import com.onjuno.assignment.crypto.databinding.ActivityHostBinding

class HostActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.hostFcv, HomeFragment.newInstance())
            .commit()
    }
}