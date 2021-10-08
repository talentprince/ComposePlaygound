package com.ddmeng.example

import javax.inject.Inject

data class Friend(
    val id: Int, val name: String
)

class MainRepository @Inject constructor() {

    val friends = listOf<Friend>(
        Friend(1, "Friend X"),
        Friend(2, "Friend Y"),
        Friend(3, "Friend Z"),
        Friend(4, "Friend A"),
        Friend(5, "Friend B"),
        Friend(6, "Friend C"),
    )
}
