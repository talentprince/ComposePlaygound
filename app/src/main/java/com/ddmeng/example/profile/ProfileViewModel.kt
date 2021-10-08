package com.ddmeng.example.profile

import com.ddmeng.example.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class ProfileState(val id: Int = -1, val name: String = "")

@HiltViewModel
class ProfileViewModel @Inject constructor() : StateViewModel<ProfileState>() {
    override fun configureInitialState() = ProfileState()
}
