package com.example.modcustoms.repository

import com.example.modcustoms.data.Auto
import com.example.modcustoms.data.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object UsuarioRepository {
    private val _usuarioActual = MutableStateFlow(Usuario())
    val usuarioActual: StateFlow<Usuario> = _usuarioActual

    fun actualizarUsuario(nuevoUsuario: Usuario) {
        _usuarioActual.value = nuevoUsuario
    }

    fun cargarDatosEjemplo() {
        _usuarioActual.value = Usuario(
            nombre = "Pedro Manuel Romero",
            email = "pedro.romero@unimilenium.edu.mx",
            telefono = "272 236 96 25",
            direccion = "FJJ8+M57 Tehuacán, Puebla",
            auto = Auto(
                marca = "Chevrolet",
                modelo = "Camaro SS",
                año = "2025",
                color = "Negro",
                placa = "ABC123"
            ),
            fotoPerfil = null
        )
    }
}