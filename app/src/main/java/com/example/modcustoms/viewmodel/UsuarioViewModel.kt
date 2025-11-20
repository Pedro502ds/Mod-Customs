package com.example.modcustoms.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modcustoms.data.Usuario
import com.example.modcustoms.repository.UsuarioRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsuarioViewModel : ViewModel() {
    val usuario: StateFlow<Usuario> = UsuarioRepository.usuarioActual

    init {
        viewModelScope.launch {
            UsuarioRepository.cargarDatosEjemplo()
        }
    }

    fun actualizarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            UsuarioRepository.actualizarUsuario(usuario)
        }
    }
}