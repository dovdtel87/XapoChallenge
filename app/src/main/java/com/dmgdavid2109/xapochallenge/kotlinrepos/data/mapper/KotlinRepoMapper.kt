package com.dmgdavid2109.xapochallenge.kotlinrepos.data.mapper

import com.dmgdavid2109.xapochallenge.common.data.Mapper
import com.dmgdavid2109.xapochallenge.kotlinrepos.data.model.KotlinRepoDTO
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model.KotlinRepo
import javax.inject.Inject

class KotlinRepoMapper @Inject constructor() : Mapper<KotlinRepoDTO, KotlinRepo> {

    override fun map(input: KotlinRepoDTO): KotlinRepo {
        with(input) {
            return KotlinRepo(
                input.name,
                input.description,
                input.avatarUrl,
                input.author,
                input.stars,
                input.forks
            )
        }
    }
}
