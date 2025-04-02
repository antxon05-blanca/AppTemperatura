package com.empresatitofranco.appconversordetemperatura

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction

@Composable
fun AppConversorTemperatura() {
    var input by remember { mutableStateOf("") }
    var esCelsiusAFahrenheit by remember { mutableStateOf(true) }

    val resultado = convertirTemperatura(input, esCelsiusAFahrenheit)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Conversor de Temperatura", fontSize = 24.sp)

        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Temperatura") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = { esCelsiusAFahrenheit = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (esCelsiusAFahrenheit) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("C → F")
            }

            Button(
                onClick = { esCelsiusAFahrenheit = false },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!esCelsiusAFahrenheit) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("F → C")
            }
        }

        Text("Resultado: $resultado", fontSize = 20.sp)
    }
}

fun convertirTemperatura(input: String, celsiusAFahrenheit: Boolean): String{
    val valor = input.toFloatOrNull() ?: return "Valor inválido"
    return if (celsiusAFahrenheit) {
        val f = valor * 9 / 5 + 32
        "$f °F"
    } else {
        val c = (valor - 32) * 5 / 9
        "$c °C"
    }
}