package org.weyoung.example

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class MainState(val friends: List<Friend> = emptyList())

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : StateViewModel<MainState>() {

    init {
        getFriends()
    }

    private fun getFriends() {
        state = state.copy(friends = repository.friends)
    }

    override fun configureInitialState() = MainState()
}
