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

        FirebaseApp.initializeApp(this)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "formulario") {
                composable("formulario") { PantallaFormulario(navController) }
                composable("examen") { PantallaExamen(navController) }
                composable("resultado") { PantallaResultado(navController) }
            }
        }
    }
}
