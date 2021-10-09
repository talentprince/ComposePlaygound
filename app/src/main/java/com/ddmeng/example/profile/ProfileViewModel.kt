package com.ddmeng.example.profile

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.ddmeng.example.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class ProfileState(val id: Int = -1, val name: String = "")

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : StateViewModel<ProfileState>() {

    init {
        val userId = savedStateHandle.get<Int>("userId")
        val userName = savedStateHandle.get<String>("userName")
        Log.i("savedStateHandle", "userId $userId userName $userName")
    }

    override fun configureInitialState() = ProfileState()
}
