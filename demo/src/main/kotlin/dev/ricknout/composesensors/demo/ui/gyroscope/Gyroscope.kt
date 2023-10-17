package dev.ricknout.composesensors.demo.ui.gyroscope

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
import dev.ricknout.composesensors.gyroscope.isGyroscopeSensorAvailable
import dev.ricknout.composesensors.gyroscope.rememberGyroscopeSensorValueAsState

@Composable
fun GyroscopeDemo() {
    if (isGyroscopeSensorAvailable()) {
        val sensorValue by rememberGyroscopeSensorValueAsState()
        val (x, y, z) = sensorValue.value
        Sensor(
            demo = Demo.GYROSCOPE,
            value = "X: $x rad/s\nY: $y rad/s\nZ: $z rad/s",
        ) {
            // TODO: Add demo

            val getSensor = getSensorDetails(sensorType = android.hardware.Sensor.TYPE_GYROSCOPE)

            Column(
                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().background(Color(0xFF277ED0)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_gyroscope_24dp), contentDescription = null,
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
        NotAvailableDemo(demo = Demo.GYROSCOPE)
    }
}
