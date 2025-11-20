package com.example.modcustoms.data

data class Usuario(
    val nombre: String = "",
    val email: String = "",
    val telefono: String = "",
    val direccion: String = "",
    val auto: Auto = Auto(),
    val fotoPerfil: String? = null
)
