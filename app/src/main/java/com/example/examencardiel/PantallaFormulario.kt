package com.example.examencardiel


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
// Composable que representa el formulario de captura de datos personales
@Composable
fun PantallaFormulario(navController: NavController) {

    // Estados para almacenar lo que el usuario escribe en el formulario
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var dia by remember { mutableStateOf("") }
    var mes by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("Masculino") }

    // Estructura principal con espacio alrededor
    Column(modifier = Modifier.padding(16.dp)) {

        // Título del formulario
        Text("Formulario", style = MaterialTheme.typography.headlineMedium)

        // Campos de texto para capturar datos del usuario
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        OutlinedTextField(value = apellidos, onValueChange = { apellidos = it }, label = { Text("Apellidos") })
        OutlinedTextField(value = dia, onValueChange = { dia = it }, label = { Text("Día de nacimiento") })
        OutlinedTextField(value = mes, onValueChange = { mes = it }, label = { Text("Mes") })
        OutlinedTextField(value = anio, onValueChange = { anio = it }, label = { Text("Año") })

        // Selección de sexo usando botones de radio
        Row {
            RadioButton(selected = sexo == "Masculino", onClick = { sexo = "Masculino" })
            Text("Masculino")
            RadioButton(selected = sexo == "Femenino", onClick = { sexo = "Femenino" })
            Text("Femenino")
        }

        // Botones para limpiar o continuar el formulario
        Row(Modifier.padding(top = 16.dp)) {

            // Botón para limpiar todos los campos
            Button(onClick = {
                nombre = ""
                apellidos = ""
                dia = ""
                mes = ""
                anio = ""
            }) { Text("Limpiar") }

            Spacer(Modifier.width(16.dp))

            // Botón para guardar los datos y pasar a la pantalla de examen
            Button(onClick = {
                // Guarda los datos en una clase compartida
                DatosUsuario.nombre = nombre
                DatosUsuario.apellidos = apellidos
                DatosUsuario.dia = dia.toIntOrNull() ?: 1
                DatosUsuario.mes = mes.toIntOrNull() ?: 1
                DatosUsuario.anio = anio.toIntOrNull() ?: 2000
                DatosUsuario.sexo = sexo

                // Navega a la pantalla del examen
                navController.navigate("examen")
            }) { Text("Siguiente") }
        }
    }
}
