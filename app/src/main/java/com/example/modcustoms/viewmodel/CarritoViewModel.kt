package com.example.modcustoms.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.modcustoms.data.Producto

class CarritoViewModel : ViewModel() {
    private val _items = mutableStateListOf<Producto>()
    val items: List<Producto> = _items

    private val _total = mutableStateOf(0)
    val total: State<Int> = _total

    fun addProducto(producto: Producto) {
        _items.add(producto)
        recalcularTotal()
    }

    fun removeProducto(producto: Producto) {
        _items.remove(producto)
        recalcularTotal()
    }

    fun clearCarrito() {
        _items.clear()
        recalcularTotal()
    }

    private fun recalcularTotal() {
        _total.value = _items.sumOf { precioToInt(it.precio) }
    }

    private fun precioToInt(precio: String): Int {
        return precio
            .replace("$", "")
            .replace(",", "")
            .replace(".", "")
            .trim()
            .toIntOrNull() ?: 0
    }
}