package com.example.roomsatu.view.route

import com.example.roomsatu.R

object DestinasiDetailSiswa : DestinasiNavigasi {
    override val route = "Detail_siswa"
    override val titleRes = R.string.detail_siswa
    const val itemIdArg = "idSiswa"
    val routeWithArgs = "$route/{itemIdArg}"
}
