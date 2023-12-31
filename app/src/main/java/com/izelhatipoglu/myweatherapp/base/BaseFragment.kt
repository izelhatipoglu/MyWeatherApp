package com.izelhatipoglu.myweatherapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<DB: ViewDataBinding> : Fragment() {

    lateinit var  dataBinding: DB

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun initUI()
    abstract fun handleClick()
    abstract fun observeLiveData()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        handleClick()
        observeLiveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,getLayoutRes(),container,false)
        return dataBinding.root
    }
}