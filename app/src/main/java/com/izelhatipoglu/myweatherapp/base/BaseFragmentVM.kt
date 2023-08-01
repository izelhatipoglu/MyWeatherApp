package com.izelhatipoglu.myweatherapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.izelhatipoglu.myweatherapp.BR

abstract class BaseFragmentVM<B: ViewDataBinding, VM: BaseViewModel> : Fragment() {

    lateinit var binding: B
    abstract val viewModel: VM

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun initUI()
    abstract fun handleClick()
    abstract fun observeLiveData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,getLayoutRes(),container!!,false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel,viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        handleClick()
        observeLiveData()
    }
}