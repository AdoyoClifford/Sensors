package dev.ricknout.composesensors.demo.ui.magneticfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ricknout.composesensors.demo.R
import dev.ricknout.composesensors.demo.model.Demo
import dev.ricknout.composesensors.demo.model.getSensorDetails
import dev.ricknout.composesensors.demo.ui.Sensor
import dev.ricknout.composesensors.demo.ui.NotAvailableDemo
import dev.ricknout.composesensors.magneticfield.isMagneticFieldSensorAvailable
import dev.ricknout.composesensors.magneticfield.rememberMagneticFieldSensorValueAsState

@Composable
fun MagneticFieldDemo() {
    if (isMagneticFieldSensorAvailable()) {
        val sensorValue by rememberMagneticFieldSensorValueAsState()
        val (x, y, z) = sensorValue.value
        Sensor(
            demo = Demo.MAGNETIC_FIELD,
            value = "X: $x μT\nY: $y μT\nZ: $z μT",
        ) {
            // TODO: Add demo

            val getSensor = getSensorDetails(sensorType = android.hardware.Sensor.TYPE_MAGNETIC_FIELD)

            Column(
                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().background(Color(0xFF277ED0)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_magnetic_field_24dp), contentDescription = null,
                        modifier = Modifier.size(150.dp), tint = Color.White,
                    )
                    Text(text = "Karthik Chitrapu", fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(420.dp))

                Column (
                    modifier = Modifier.padding(16.dp)
                ){
                    Text(
                        text = "Sensor Details",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color.Blue
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Sensor Name: ") }

                            append(getSensor.name)
                        },
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Vendor: ") }

                            append(getSensor.vendor)
                        },
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Power: ") }

                            append(getSensor.power.toString())
                        },
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Range: ") }

                            append(getSensor.range.toString())
                        },
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Resolution") }

                            append(getSensor.resolution.toString())
                        },
                    )
                }
            }
        }
    } else {
        NotAvailableDemo(demo = Demo.MAGNETIC_FIELD)
    }
}
