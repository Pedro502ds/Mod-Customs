package com.example.modcustoms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.modcustoms.ui.theme.ModCustomsTheme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.Scaffold
import androidx.compose.ui.graphics.vector.ImageVector

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            ModCustomsTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            PantallaLogin(onLoginSuccess = { usuario ->
                                println("Login exitoso para: $usuario")
                                navController.navigate("principal")
                            })
                        }
                        composable("principal") {
                            PantallaPrincipal()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PantallaLogin(onLoginSuccess: (String) -> Unit) {
    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var mostrarFormulario by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(500)
        mostrarFormulario = true
    }

    AnimatedVisibility(
        visible = mostrarFormulario,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Ingresa tu Usuario") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFE0E0E0),
                    unfocusedContainerColor = Color(0xFFF5F5F5)
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Ingresa tu Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFE0E0E0),
                    unfocusedContainerColor = Color(0xFFF5F5F5)
                )
            )
            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = {
                    if (usuario == "pedro" && password == "1234") {
                        mensaje = ""
                        onLoginSuccess(usuario)
                    } else {
                        mensaje = "Usuario o Contraseña Incorrecta"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Inicia Sesión")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(mensaje, color = Color.Red)
        }
    }
}

@Composable
fun PantallaPrincipal() {
    val internalNavController = rememberNavController()

    Scaffold(
        bottomBar = { BarraInferior(internalNavController) }
    ) { innerPadding ->
        NavHost(
            navController = internalNavController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { PantallaHome() }
            composable(Screen.Perfil.route) { PantallaPerfil() }
            composable(Screen.TuAuto.route) { PantallaTuAuto() }
            composable(Screen.Comprados.route) { PantallaComprados() }
            composable(Screen.Entrega.route) { PantallaEntrega() }
        }
    }
}

@Composable
fun PantallaHome() {
    var textoBusqueda by remember { mutableStateOf("") }
    val categorias = listOf(
        "Motor" to R.drawable.motor,
        "Rines" to R.drawable.rines,
        "Interiores" to R.drawable.interiores,
        "Equipamiento" to R.drawable.equipamiento,
        "Racing" to R.drawable.racing,
        "Viaje" to R.drawable.viaje
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("MOD CUSTOMS", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = textoBusqueda,
            onValueChange = { textoBusqueda = it },
            label = { Text("Buscar") },
            placeholder = { Text("Motor y más") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFE0E0E0),
                unfocusedContainerColor = Color(0xFFF5F5F5)
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text("Categorías", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(12.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categorias) { (nombre, imagenId) ->
                CategoriaItem(nombre, imagenId)
            }
        }
    }
}

@Composable
fun BarraInferior(navController: NavHostController) {
    val items = listOf(
        Screen.Home,
        Screen.Perfil,
        Screen.TuAuto,
        Screen.Comprados,
        Screen.Entrega
    )

    NavigationBar {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun CategoriaItem(nombre: String, imagenId: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Image(painter = painterResource(id = imagenId), contentDescription = nombre)
        Text(nombre)
    }
}


sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "Inicio", Icons.Default.Home)
    object Perfil : Screen("perfil", "Perfil", Icons.Default.Person)
    object TuAuto : Screen("tuAuto", "Tu Auto", Icons.Default.DirectionsCar)
    object Comprados : Screen("comprados", "Compras", Icons.Default.ShoppingCart)
    object Entrega : Screen("entrega", "Entrega", Icons.Default.LocalShipping)
}


@Composable fun PantallaPerfil() { Text("Tu perfil") }
@Composable fun PantallaTuAuto() { Text("Detalles de tu auto") }
@Composable fun PantallaComprados() { Text("Artículos comprados") }
@Composable fun PantallaEntrega() { Text("En camino para entrega") }

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun PantallaPrincipalPreview() {
    val navController = rememberNavController()
    ModCustomsTheme {
        Surface(color = Color.White) {
            PantallaPrincipal()
        }
    }
}


