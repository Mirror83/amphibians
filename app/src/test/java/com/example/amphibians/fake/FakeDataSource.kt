package com.example.amphibians.fake

import com.example.amphibians.data.Amphibian

object FakeDataSource {
    val amphibians: List<Amphibian> = List(10) {
        Amphibian(
            "Name $it",
            "Type $it",
            "Description $it",
            "Image source $it"
        )
    }
}

