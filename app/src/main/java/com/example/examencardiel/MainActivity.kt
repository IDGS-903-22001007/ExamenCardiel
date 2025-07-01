package com.example.examencardiel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa Firebase con el contexto de la aplicación
        FirebaseApp.initializeApp(this)
        setContent {
            // Crea un controlador de navegación para manejar las pantallas
            val navController = rememberNavController()
            // Configura el sistema de navegación entre pantallas
            NavHost(navController = navController, startDestination = "formulario") {
                composable("formulario") {
                    PantallaFormulario(navController)
                }
                composable("examen") {
                    PantallaExamen(navController)
                }
                composable("resultado") {
                    PantallaResultado(navController)
                }
            }
        }
    }
}