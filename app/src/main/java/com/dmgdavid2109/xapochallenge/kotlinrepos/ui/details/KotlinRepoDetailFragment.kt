package com.dmgdavid2109.xapochallenge.kotlinrepos.ui.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dmgdavid2109.xapochallenge.R
import com.dmgdavid2109.xapochallenge.common.ui.setToolbar
import com.dmgdavid2109.xapochallenge.common.ui.viewBinding
import com.dmgdavid2109.xapochallenge.databinding.KotlinRepoDetailFragmentBinding
import kotlinx.android.synthetic.main.kotlin_repo_detail_fragment.*
import javax.inject.Inject

class KotlinRepoDetailFragment @Inject constructor(
    private val viewModelFactory: KotlinRepoDetailViewModel.Factory
) : Fragment(R.layout.kotlin_repo_detail_fragment) {

    private val fragmentArguments: KotlinRepoDetailFragmentArgs by navArgs()
    private val binding by viewBinding(KotlinRepoDetailFragmentBinding::bind)

    private val viewModel: KotlinRepoDetailViewModel by viewModels {
        viewModelFactory.create(fragmentArguments.repo)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        bindView()
    }

    private fun setUpToolbar() {
        val title = fragmentArguments.repo.name
        (activity as AppCompatActivity).setToolbar(binding.toolbar, title, true)
    }

    private fun bindView() {

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            with(viewState.kotlinRepo) {
                binding.repoName.text = name
                binding.repoDescription.text = description
                binding.authorValue.text = author
                binding.startsValue.text = starts
                binding.forksValue.text = forks

                Glide.with(this@KotlinRepoDetailFragment)
                    .load(avatar)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(binding.repoLogo)

            }
        })
    }
}
