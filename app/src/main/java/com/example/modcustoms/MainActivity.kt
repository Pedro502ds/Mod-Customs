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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
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

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.ui.draw.clip
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.modcustoms.data.Producto
import com.example.modcustoms.ui.pantalla.PantallaProductos
import com.example.modcustoms.ui.pantalla.PantallaDetalleProducto
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.layout.ContentScale

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.derivedStateOf
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.modcustoms.viewmodel.CarritoViewModel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.IconButton

import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.unit.sp
import com.example.modcustoms.viewmodel.CarritoViewModelFactory

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.modcustoms.data.Usuario
import com.example.modcustoms.viewmodel.UsuarioViewModel
import com.example.modcustoms.viewmodel.UsuarioViewModelFactory
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            ModCustomsTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) {
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
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
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
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
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
    val carritoViewModel: CarritoViewModel = viewModel(factory = CarritoViewModelFactory())

    Scaffold(
        bottomBar = { BarraInferior(internalNavController, carritoViewModel) }
    ) { innerPadding ->
        NavHost(
            navController = internalNavController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                PantallaHome(internalNavController)
            }
            composable(Screen.Perfil.route) { PantallaPerfil() }
            composable(Screen.TuAuto.route) { PantallaTuAuto() }
            composable(Screen.Comprados.route) { PantallaComprados(carritoViewModel) }
            composable(Screen.Entrega.route) { PantallaEntrega() }

            composable(
                route = "productos/{categoria}",
                arguments = listOf(
                    navArgument("categoria") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val categoria = backStackEntry.arguments?.getString("categoria") ?: "Categoría"

                PantallaProductos (
                    categoriaNombre = categoria,
                    productos = productosPorCategoria(categoria),
                    onProductoClick = { producto ->
                        internalNavController.navigate("detalle/${producto.id}")
                    },
                    onBack = { internalNavController.popBackStack() }
                )
            }
            composable(
                route = "detalle/{productoId}",
                arguments = listOf(navArgument("productoId") { type = NavType.IntType })
            ) { backStackEntry ->
                val productoId = backStackEntry.arguments?.getInt("productoId") ?: 1
                val producto = todosLosProductos().find { it.id == productoId }
                    ?: Producto(1, "Producto no encontrado", "$0", R.drawable.motor)

                PantallaDetalleProducto(
                    producto = producto,
                    onBack = { internalNavController.popBackStack() },
                    onAddToCart = { carritoViewModel.addProducto(producto) },
                    viewModel = carritoViewModel
                )
            }
        }
    }
}

@Composable
fun PantallaHome(navController: NavHostController) {
    var textoBusqueda by remember { mutableStateOf("") }

    val categorias = listOf(
        "Motor" to R.drawable.motor,
        "Rines" to R.drawable.rines,
        "Interiores" to R.drawable.interiores,
        "Equipamiento" to R.drawable.equipamiento,
        "Racing" to R.drawable.racing,
        "Viaje" to R.drawable.viaje
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("MOD CUSTOMS", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = textoBusqueda,
            onValueChange = { textoBusqueda = it },
            label = { Text("Buscar") },
            placeholder = { Text("Motor y más") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
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
                CategoriaItem(
                    nombre = nombre,
                    imagenId = imagenId,
                    onClick = { navController.navigate("productos/$nombre") }
                )
            }
        }
    }
}

@Composable
fun BarraInferior(
    navController: NavHostController,
    carritoViewModel: CarritoViewModel
) {
    val items = listOf(
        Screen.Home,
        Screen.Perfil,
        Screen.TuAuto,
        Screen.Comprados,
        Screen.Entrega
    )

    val cantidad by remember { derivedStateOf { carritoViewModel.items.size } }
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    if (screen == Screen.Comprados && cantidad > 0) {
                        BadgedBox(
                            badge = {
                                Badge(containerColor = Color.Red) {
                                    Text(cantidad.toString())
                                }
                            }
                        ) {
                            Icon(screen.icon, contentDescription = screen.label)
                        }
                    } else {
                        Icon(screen.icon, contentDescription = screen.label)
                    }
                },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun CategoriaItem(nombre: String, imagenId: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imagenId),
            contentDescription = nombre,
            modifier = Modifier
                .width(140.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = nombre, fontWeight = FontWeight.Medium)
    }
}

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "Inicio", Icons.Default.Home)
    object Perfil : Screen("perfil", "Perfil", Icons.Default.Person)
    object TuAuto : Screen("tuAuto", "Tu Auto", Icons.Default.DirectionsCar)
    object Comprados : Screen("comprados", "Cesta", Icons.Default.ShoppingCart)
    object Entrega : Screen("entrega", "Entrega", Icons.Default.LocalShipping)
}

fun productosPorCategoria(categoria: String): List<Producto> {
    return when (categoria) {
        "Motor" -> listOf(
            Producto(1, "Kit Turbo Garrett GT35", "$2,850.000", R.drawable.turbo),
            Producto(2, "Escape Deportivo", "$3,200.000", R.drawable.escape),
            Producto(3, "ECU Haltech Elite 2500", "$4,500.000", R.drawable.haltech),
            Producto(4, "Inyectores 1000cc", "$1,200.000", R.drawable.inyector),
            Producto(5, "Intercooler Mishimoto", "$980,000", R.drawable.mishi)
        )
        "Rines" -> listOf(
            Producto(6, "Rines BBS FI-R 19\"", "$8,500.000", R.drawable.rines19),
            Producto(7, "Rines OZ Racing 18\"", "$6,800.000", R.drawable.rines18),
            Producto(8, "Rines Forgiato 20\"", "$12,000.000", R.drawable.rines20),
            Producto(9, "Rines Vossen HF-5 19\"", "$9,200.000", R.drawable.rines19v)
        )
        "Interiores" -> listOf(
            Producto(10, "Asientos Recaro Sportster", "$5,800.000", R.drawable.recaro),
            Producto(11, "Volante Momo Prototipo", "$1,100.000", R.drawable.volante),
            Producto(12, "Tapicería Alcantara Negra", "$4,200.000", R.drawable.tapiceria)
        )
        "Racing" -> listOf(
            Producto(13, "Roll Cage 6 puntos", "$3,800.000", R.drawable.rol),
            Producto(14, "Suspensión Coilover KW V3", "$7,500.000", R.drawable.suspencion),
            Producto(15, "Frenos Brembo GT 6 pistones", "$14,000.000", R.drawable.brembo)
        )
        "Equipamiento" -> listOf(
            Producto(16, "Luces LED Full", "$1,800.000", R.drawable.led),
            Producto(17, "Spoiler Carbono", "$2,300.000", R.drawable.spoiler),
            Producto(18, "Difusor Trasero", "$1,950.000", R.drawable.difusor)
        )
        "Viaje" -> listOf(
            Producto(19, "Portaequipajes Thule", "$1,200.000", R.drawable.porta),
            Producto(20, "Nevera 12V 40L", "$1,780.000", R.drawable.nevera),
            Producto(21, "Kit Camping Premium", "$2,100.000", R.drawable.kit)
        )
        else -> emptyList()
    }
}

fun todosLosProductos(): List<Producto> {
    return productosPorCategoria("Motor") +
            productosPorCategoria("Rines") +
            productosPorCategoria("Interiores") +
            productosPorCategoria("Racing") +
            productosPorCategoria("Equipamiento") +
            productosPorCategoria("Viaje")
}

@Composable
fun PantallaPerfil() {
    val usuarioViewModel: UsuarioViewModel = viewModel(factory = UsuarioViewModelFactory())
    val usuario by usuarioViewModel.usuario.collectAsStateWithLifecycle()

    var mostrarDialogoEditar by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.perfil),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = usuario.nombre,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Información Personal",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    InfoItem(
                        icon = Icons.Default.Person,
                        titulo = "Nombre",
                        valor = usuario.nombre
                    )
                    InfoItem(
                        icon = Icons.Default.Email,
                        titulo = "Email",
                        valor = usuario.email
                    )
                    InfoItem(
                        icon = Icons.Default.Phone,
                        titulo = "Teléfono",
                        valor = usuario.telefono
                    )
                    InfoItem(
                        icon = Icons.Default.LocationOn,
                        titulo = "Dirección",
                        valor = usuario.direccion
                    )

                    Button(
                        onClick = { mostrarDialogoEditar = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        Text("Editar Información")
                    }
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Contacto Chevrolet",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    InfoItem(
                        icon = Icons.Default.Phone,
                        titulo = "Servicio al Cliente",
                        valor = "01 8000 517 200"
                    )
                    InfoItem(
                        icon = Icons.Default.Phone,
                        titulo = "Asistencia Técnica",
                        valor = "01 8000 517 300"
                    )
                    InfoItem(
                        icon = Icons.Default.Email,
                        titulo = "Email",
                        valor = "servicio@chevrolet.com.co"
                    )
                    InfoItem(
                        icon = Icons.Default.Language,
                        titulo = "Sitio Web",
                        valor = "www.chevrolet.com.co"
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Llamar")
                        }
                        Button(
                            onClick = { },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Email")
                        }
                    }
                }
            }
        }
    }

    if (mostrarDialogoEditar) {
        DialogoEditarPerfil(
            usuario = usuario,
            onGuardar = { usuarioActualizado ->
                usuarioViewModel.actualizarUsuario(usuarioActualizado)
                mostrarDialogoEditar = false
            },
            onCancelar = { mostrarDialogoEditar = false }
        )
    }
}

@Composable
fun FotoPerfil(fotoUri: String?, onFotoSelected: (String) -> Unit) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        if (fotoUri != null) {
            Image(
                painter = painterResource(id = R.drawable.camaross),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .clickable { },
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Agregar foto",
                    modifier = Modifier.size(48.dp),
                    tint = Color.White
                )
            }
        }

        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .align(Alignment.BottomEnd)
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.CameraAlt,
                contentDescription = "Cambiar foto",
                modifier = Modifier.size(20.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
fun InfoItem(icon: ImageVector, titulo: String, valor: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = titulo,
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = titulo,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Text(
                text = valor,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun DialogoEditarPerfil(usuario: Usuario, onGuardar: (Usuario) -> Unit, onCancelar: () -> Unit) {
    var nombre by remember { mutableStateOf(usuario.nombre) }
    var email by remember { mutableStateOf(usuario.email) }
    var telefono by remember { mutableStateOf(usuario.telefono) }
    var direccion by remember { mutableStateOf(usuario.direccion) }

    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text("Editar Perfil") },
        text = {
            Column {
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = { Text("Dirección") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onGuardar(
                    usuario.copy(
                        nombre = nombre,
                        email = email,
                        telefono = telefono,
                        direccion = direccion
                    )
                )
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun PantallaTuAuto() {
    val usuarioViewModel: UsuarioViewModel = viewModel(factory = UsuarioViewModelFactory())
    val usuario by usuarioViewModel.usuario.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Mi Chevrolet Camaro SS 2025",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.camaross),
                        contentDescription = "Chevrolet Camaro SS",
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(200.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Especificaciones técnicas
                    Text(
                        text = "Especificaciones Técnicas",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    )

                    InfoItem(
                        icon = Icons.Default.DirectionsCar,
                        titulo = "Modelo",
                        valor = "${usuario.auto.marca} ${usuario.auto.modelo}"
                    )
                    InfoItem(
                        icon = Icons.Default.CalendarToday,
                        titulo = "Año",
                        valor = usuario.auto.año
                    )
                    InfoItem(
                        icon = Icons.Default.ColorLens,
                        titulo = "Color",
                        valor = usuario.auto.color
                    )
                    InfoItem(
                        icon = Icons.Default.ConfirmationNumber,
                        titulo = "Placa",
                        valor = usuario.auto.placa
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Características adicionales
                    Text(
                        text = "Características",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    )

                    CaracteristicaItem("Motor", "6.2L V8 LT1")
                    CaracteristicaItem("Potencia", "455 HP")
                    CaracteristicaItem("Transmisión", "Automática 10 velocidades")
                    CaracteristicaItem("Tracción", "Trasera")
                    CaracteristicaItem("0-100 km/h", "4.0 segundos")

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { /* Acción para más detalles */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Ver Historial de Mantenimiento")
                    }
                }
            }
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Próximo Mantenimiento",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    InfoItem(
                        icon = Icons.Default.CalendarToday,
                        titulo = "Próxima Revisión",
                        valor = "15 de Diciembre, 2024"
                    )
                    InfoItem(
                        icon = Icons.Default.DirectionsCar,
                        titulo = "Kilometraje Actual",
                        valor = "8,450 km"
                    )
                    InfoItem(
                        icon = Icons.Default.ConfirmationNumber,
                        titulo = "Próximo Cambio de Aceite",
                        valor = "En 1,550 km"
                    )
                }
            }
        }
    }
}

@Composable
fun CaracteristicaItem(nombre: String, valor: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = nombre,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = valor,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaComprados(viewModel: CarritoViewModel = viewModel()) {
    val items = viewModel.items
    val total = viewModel.total.value

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Carrito (${items.size} productos)", fontWeight = FontWeight.Bold) }
            )
        }
    ) { padding ->
        if (items.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    tint = Color.Gray
                )
                Spacer(Modifier.height(16.dp))
                Text("Tu carrito está vacío", fontSize = 20.sp, color = Color.Gray)
                Text("¡Explora y añade productos!", color = Color.Gray)
            }
        } else {
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(count = items.size) { index ->
                    val producto = items[index]
                    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                        Row(modifier = Modifier.padding(12.dp)) {
                            Image(
                                painter = painterResource(producto.imagenId),
                                contentDescription = null,
                                modifier = Modifier.size(80.dp).clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Column(modifier = Modifier.padding(start = 12.dp).weight(1f)) {
                                Text(producto.nombre, fontWeight = FontWeight.SemiBold)
                                Text(producto.precio, color = Color(0xFF00A000), fontWeight = FontWeight.Bold)
                            }
                            IconButton(onClick = { viewModel.removeProducto(producto) }) {
                                Icon(Icons.Default.Close, contentDescription = "Eliminar")
                            }
                        }
                    }
                }
                item {
                    Card(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Total:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                Text(
                                    text = formatearPrecio(total),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF00695C)
                                )
                            }
                            Spacer(Modifier.height(16.dp))
                            Button(
                                onClick = {  },
                                modifier = Modifier.fillMaxWidth().height(50.dp)
                            ) {
                                Text("PROCEDER AL PAGO", fontSize = 18.sp)
                            }
                            Spacer(Modifier.height(8.dp))
                            OutlinedButton(
                                onClick = { viewModel.clearCarrito() },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Vaciar carrito")
                            }
                        }
                    }
                }
            }

        }
    }
}

fun formatearPrecio(total: Int): String {
    if (total == 0) return "$0"

    return if (total < 1000) {
        "$$total"
    } else {
        val millones = total / 1000
        val miles = total % 1000
        val millonesFormateados = String.format("%,d", millones)
        val milesFormateados = String.format("%03d", miles)
        "$$millonesFormateados.$milesFormateados"
    }
}

@Composable fun PantallaEntrega() { Text("En camino para entrega") }


@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun PantallaPrincipalPreview() {
    val navController = rememberNavController()
    ModCustomsTheme {
        Surface(color = Color.Transparent) {
            PantallaPrincipal()
        }
    }
}


