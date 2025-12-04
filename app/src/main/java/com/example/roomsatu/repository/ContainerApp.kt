package com.example.roomsatu.repository



import android.content.Context
import com.example.roomsatu.repositori.OfflineRepositoriSiswa
import com.example.roomsatu.repositori.RepositoriSiswa
import com.example.roomsatu.room.DatabaseSiswa


interface ContainerApp {
    val repositoriSiswa: RepositoriSiswa
}

class ContainerDataApp(private val context: Context) : ContainerApp {
    override val repositoriSiswa: RepositoriSiswa by lazy {
        OfflineRepositoriSiswa( // Pastikan class ini sudah Anda buat
            siswaDao = DatabaseSiswa.getDatabase(context).siswaDao()
        )
    }
}