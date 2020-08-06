package com.dmgdavid2109.xapochallenge.kotlinrepos.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dmgdavid2109.xapochallenge.common.ui.viewBinding
import com.dmgdavid2109.xapochallenge.R
import com.dmgdavid2109.xapochallenge.common.ui.setViewModelInputs
import com.dmgdavid2109.xapochallenge.common.ui.setViewState
import com.dmgdavid2109.xapochallenge.databinding.KotlinRepoListFragmentBinding
import com.dmgdavid2109.xapochallenge.di.ViewModelFactory
import javax.inject.Inject

class KotlinRepoListFragment @Inject constructor(
    private val viewModelFactory: ViewModelFactory<KotlinRepoListViewModel>
) : Fragment((R.layout.kotlin_repo_list_fragment)) {

    private val binding by viewBinding(KotlinRepoListFragmentBinding::bind)

    private val repositoriesListViewModel: KotlinRepoListViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
    }

    private fun bindView() {
        val listAdapter =
            KotlinRepoListAdapter()

        binding.list.adapter = listAdapter
        binding.loadingView.setViewModelInputs(repositoriesListViewModel)

        repositoriesListViewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            listAdapter.submitList(viewState.repositoryList)
            binding.loadingView.setViewState(viewState)
        })
    }
}
