package com.onjuno.assignment.crypto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onjuno.assignment.crypto.databinding.FragmentStateBinding

class StateFragment : Fragment() {
    companion object {
        fun newInstance() = StateFragment()
    }

    private lateinit var mBinding: FragmentStateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentStateBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {
        with(mBinding) {
        }
    }
}