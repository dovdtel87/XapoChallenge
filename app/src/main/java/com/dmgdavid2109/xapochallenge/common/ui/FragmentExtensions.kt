package com.dmgdavid2109.xapochallenge.common.ui

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

fun <T : ViewBinding> Fragment.viewBinding(bindView: (View) -> T) =
    FragmentViewBindingDelegate(this, bindView)
