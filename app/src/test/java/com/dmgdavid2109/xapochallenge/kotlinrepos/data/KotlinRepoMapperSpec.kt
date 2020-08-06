package com.dmgdavid2109.xapochallenge.kotlinrepos.data

import com.dmgdavid2109.xapochallenge.kotlinrepos.data.mapper.KotlinRepoMapper
import com.dmgdavid2109.xapochallenge.kotlinrepos.data.model.KotlinRepoDTO
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model.KotlinRepo
import junit.framework.TestCase.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object KotlinRepoMapperSpec : Spek({
    val mapper = KotlinRepoMapper()
    val kotlinRepoDTO = KotlinRepoDTO(
    "First repository",
    "Contains the description of first repository",
    "https://avatar1.png",
    "dovdtel87",
    "123",
    "45"
    )

    describe("map") {
        it("maps from KotlinRepoDTO to KotlinRepo") {
            val result = mapper.map(kotlinRepoDTO)
            val expected = KotlinRepo(
                "First repository",
                "Contains the description of first repository",
                "https://avatar1.png",
                "dovdtel87",
                "123",
                "45"
            )
            assertEquals(expected, result)
        }
    }
})
