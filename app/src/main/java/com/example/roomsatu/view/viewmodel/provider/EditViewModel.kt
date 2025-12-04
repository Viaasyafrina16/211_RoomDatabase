package com.example.roomsatu.view.viewmodel.provider

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsatu.repositori.RepositoriSiswa
import com.example.roomsatu.view.route.DestinasiDetailSiswa
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch



class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UiStateSiswa())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[DestinasiDetailSiswa.itemIdArg])

    init {
        viewModelScope.launch {
            uiStateSiswa = repositoriSiswa.getSiswaStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateSiswa(true)
        }
    }

    fun updateUiState(detailSiswa: EntryViewModel.DetailSiswa) {
        uiStateSiswa =
            uiStateSiswa.copy(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    private fun validasiInput(uiState: EntryViewModel.DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    suspend fun updateSiswa() {
        if (validasiInput(uiStateSiswa.detailSiswa)) {
            repositoriSiswa.updateSiswa(uiStateSiswa.detailSiswa.toSiswa())
        }
    }
}