package dev.ricknout.composesensors.demo.ui.light

import android.hardware.SensorManager
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ricknout.composesensors.demo.R
import dev.ricknout.composesensors.demo.model.Demo
import dev.ricknout.composesensors.demo.model.getSensorDetails
import dev.ricknout.composesensors.demo.ui.Sensor
import dev.ricknout.composesensors.demo.ui.NotAvailableDemo
import dev.ricknout.composesensors.light.isLightSensorAvailable
import dev.ricknout.composesensors.light.rememberLightSensorValueAsState

@Composable
fun LightDemo() {
    if (isLightSensorAvailable()) {
        val sensorValue by rememberLightSensorValueAsState(samplingPeriodUs = SensorManager.SENSOR_DELAY_FASTEST)
        val value = sensorValue.value
        val ratio = animateFloatAsState(targetValue = (value / SensorManager.LIGHT_OVERCAST).coerceAtMost(1f))
        Sensor(
            demo = Demo.LIGHT,
            value = "",
        ) {


            val getSensor = getSensorDetails(sensorType = android.hardware.Sensor.TYPE_LIGHT)

            Column(
                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF277ED0)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_light_24dp), contentDescription = null,
                        modifier = Modifier.size(150.dp), tint = Color.White,
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "$value lux",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    textAlign = TextAlign.Start,
                )

                Spacer(modifier = Modifier.height(260.dp))

                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Text(
                        text = "Sensor Details",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color.Blue,
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

            val contentColor = LocalContentColor.current
            val radius = with(LocalDensity.current) { 10.dp.toPx() }
//            Canvas(modifier = Modifier.fillMaxSize()) {
//                drawCircle(
//                    color = contentColor,
//                    radius = radius,
//                    center = center,
//                )
//                val gradientRadius = radius + (size.minDimension - radius) * ratio.value
//                drawCircle(
//                    brush = Brush.radialGradient(
//                        colors = listOf(contentColor, Color.Transparent),
//                        radius = gradientRadius,
//                        center = center,
//                    ),
//                    radius = gradientRadius,
//                    center = center,
//                )
//            }
        }
    } else {
        NotAvailableDemo(demo = Demo.LIGHT)
    }
}
