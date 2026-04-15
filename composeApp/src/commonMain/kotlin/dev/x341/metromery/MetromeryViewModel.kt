package dev.x341.metromery

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MetromeryViewModel : ViewModel() {
    var showMessage by mutableStateOf(false)
        private set

    fun modifyShowMessage() {
        showMessage = !showMessage
    }
}