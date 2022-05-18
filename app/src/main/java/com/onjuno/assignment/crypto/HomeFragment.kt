package com.onjuno.assignment.crypto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onjuno.assignment.crypto.databinding.FragmentHomeBinding
import com.onjuno.assignment.crypto.models.StateType

class HomeFragment : Fragment() {
    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var mBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentHomeBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {
        with(mBinding) {
            emptyStateBtn.setOnClickListener {
                openStateFragment(StateType.EMPTY_STATE)
            }
            valueStateBtn.setOnClickListener {
                openStateFragment(StateType.VALUE_STATE)
            }
        }
    }

    private fun openStateFragment(stateType: StateType) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.hostFcv, StateFragment.newInstance(stateType)).commit()
    }
}