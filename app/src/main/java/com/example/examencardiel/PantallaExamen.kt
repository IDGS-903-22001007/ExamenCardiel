package com.example.examencardiel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// Clase que representa una pregunta con su texto, opciones y la respuesta correcta (por índice)
data class Pregunta(val texto: String, val opciones: List<String>, val respuestaCorrecta: Int)

// Composable principal para mostrar el examen
@Composable
fun PantallaExamen(navController: NavController) {

    // Lista de preguntas con sus opciones y respuestas correctas
    val preguntas = listOf(
        Pregunta("¿Cuánto es 2 + 2?", listOf("8", "6", "4", "3"), 2),
        Pregunta("Capital de México", listOf("Monterrey", "CDMX", "Puebla", "Cancún"), 1),
        Pregunta("Días de una semana", listOf("10", "6", "5", "7"), 3),
        Pregunta("Color de rojo + azul", listOf("Verde", "Morado", "Naranja", "Café"), 1),
        Pregunta("Planeta más cercano al Sol", listOf("Marte", "Venus", "Tierra", "Mercurio"), 3),
        Pregunta("¿Cuántas patas tiene una araña?", listOf("6", "8", "4", "10"), 1)
    )

    // Lista mutable que almacena las respuestas del usuario (-1 significa sin contestar)
    val respuestasUsuario = remember { mutableStateListOf(-1, -1, -1, -1, -1, -1) }

    // Estado para permitir hacer scroll en la columna
    val scrollState = rememberScrollState()

    // Contenedor principal con scroll vertical y padding
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        // Se muestra cada pregunta con sus opciones
        preguntas.forEachIndexed { i, pregunta ->
            // Título de la pregunta
            Text("${i + 1}. ${pregunta.texto}", style = MaterialTheme.typography.bodyLarge)

            // Opciones con RadioButton
            pregunta.opciones.forEachIndexed { j, opcion ->
                Row {
                    RadioButton(
                        selected = respuestasUsuario[i] == j, // Marca si está seleccionada
                        onClick = { respuestasUsuario[i] = j } // Cambia la selección
                    )
                    Text(opcion)
                }
            }

            // Espacio entre preguntas
            Spacer(Modifier.height(8.dp))
        }

        // Botón para terminar el examen
        Button(onClick = {
            val puntosPorPregunta = 10.0 / preguntas.size // Ponderación de cada pregunta
            var calificacion = 0.0

            // Verifica cuántas respuestas son correctas
            respuestasUsuario.forEachIndexed { i, r ->
                if (r == preguntas[i].respuestaCorrecta) {
                    calificacion += puntosPorPregunta
                }
            }
            // Asegura que la calificación esté entre 0 y 10
            calificacion = calificacion.coerceIn(0.0, 10.0)
            // Guarda la calificación en una variable global
            DatosUsuario.calificacion = calificacion.toInt()
            navController.navigate("resultado")
        }) {
            Text("Terminar")
        }
    }
}