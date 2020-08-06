package com.dmgdavid2109.xapochallenge.kotlinrepos.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dmgdavid2109.xapochallenge.R
import com.dmgdavid2109.xapochallenge.common.ui.setViewModelInputs
import com.dmgdavid2109.xapochallenge.common.ui.setViewState
import com.dmgdavid2109.xapochallenge.common.ui.viewBinding
import com.dmgdavid2109.xapochallenge.databinding.KotlinRepoDetailFragmentBinding
import com.dmgdavid2109.xapochallenge.di.ViewModelFactory
import com.dmgdavid2109.xapochallenge.kotlinrepos.ui.list.KotlinRepoListViewModel
import javax.inject.Inject

class KotlinRepoDetailFragment @Inject constructor(
    private val viewModelFactory: ViewModelFactory<KotlinRepoListViewModel>
) : Fragment(R.layout.kotlin_repo_detail_fragment) {

    private val binding by viewBinding(KotlinRepoDetailFragmentBinding::bind)

    private val viewModel: KotlinRepoDetailViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
    }

    private fun bindView() {

       binding.loadingView.setViewModelInputs(viewModel)

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            binding.loadingView.setViewState(viewState)
            binding.authorValue.text = viewState.author
        })
    }
}
