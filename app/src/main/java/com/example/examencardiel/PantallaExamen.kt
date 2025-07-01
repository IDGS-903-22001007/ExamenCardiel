package com.example.examencardiel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class Pregunta(val texto: String, val opciones: List<String>, val respuestaCorrecta: Int)

@Composable
fun PantallaExamen(navController: NavController) {
    val preguntas = listOf(
        Pregunta("¿Cuánto es 2 + 2?", listOf("8", "6", "4", "3"), 2), // Respuesta: "4"
        Pregunta("Capital de México", listOf("Monterrey", "CDMX", "Puebla", "Cancún"), 1), // Respuesta: "CDMX"
        Pregunta("Días de una semana", listOf("10", "6", "5", "7"), 3), // Respuesta: "7"
        Pregunta("Color de rojo + azul", listOf("Verde", "Morado", "Naranja", "Café"), 1), // Respuesta: "Morado"
        Pregunta("Planeta más cercano al Sol", listOf("Marte", "Venus", "Tierra", "Mercurio"), 3), // Respuesta: "Mercurio"
        Pregunta("¿Cuántas patas tiene una araña?", listOf("6", "8", "4", "10"), 1) // Respuesta: "8"
    )

    val respuestasUsuario = remember { mutableStateListOf(-1, -1, -1, -1, -1, -1) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        preguntas.forEachIndexed { i, pregunta ->
            Text("${i + 1}. ${pregunta.texto}", style = MaterialTheme.typography.bodyLarge)

            pregunta.opciones.forEachIndexed { j, opcion ->
                Row {
                    RadioButton(
                        selected = respuestasUsuario[i] == j,
                        onClick = { respuestasUsuario[i] = j }
                    )
                    Text(opcion)
                }
            }
            Spacer(Modifier.height(8.dp))
        }

        Button(onClick = {
            val puntosPorPregunta = 10.0 / preguntas.size
            var calificacion = 0.0

            respuestasUsuario.forEachIndexed { i, r ->
                if (r == preguntas[i].respuestaCorrecta) {
                    calificacion += puntosPorPregunta
                }
            }

            calificacion = calificacion.coerceIn(0.0, 10.0)
            DatosUsuario.calificacion = calificacion.toInt()
            navController.navigate("resultado")
        }) {
            Text("Terminar")
        }
    }
}