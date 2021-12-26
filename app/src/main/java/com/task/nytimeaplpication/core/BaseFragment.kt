package com.task.nytimeaplpication.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Created by Amer Elsayed.
 *12/25/2021
 *dev.amer.elsayed@gmail.com
 */

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null

    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    protected abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    fun toast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}