package com.onjuno.assignment.crypto.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

    override fun onBackPressed() {
        with(mBinding) {
            when (hostFcv.getFragment<Fragment>()) {
                is StateFragment -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.hostFcv, HomeFragment.newInstance())
                        .commit()
                }
                else -> {
                    super.onBackPressed()
                }
            }
        }
    }
}