package com.dmgdavid2109.xapochallenge.kotlinrepos.ui.list

import androidx.fragment.app.Fragment
import com.dmgdavid2109.xapochallenge.di.FragmentKey
import com.dmgdavid2109.xapochallenge.kotlinrepos.ui.details.KotlinRepoDetailFragment
import com.dmgdavid2109.xapochallenge.kotlinrepos.ui.list.KotlinRepoListFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class KotlinRepoListFragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(KotlinRepoListFragment::class)
    abstract fun bindListFragment(mainFragment: KotlinRepoListFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(KotlinRepoDetailFragment::class)
    abstract fun bindDetailsFragment(mainFragment: KotlinRepoDetailFragment): Fragment

}
