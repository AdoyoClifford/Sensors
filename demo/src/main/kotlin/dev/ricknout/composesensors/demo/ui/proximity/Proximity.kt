package dev.ricknout.composesensors.demo.ui.proximity

import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ricknout.composesensors.SensorValue
import dev.ricknout.composesensors.demo.R
import dev.ricknout.composesensors.demo.model.Demo
import dev.ricknout.composesensors.demo.model.getSensorDetails
import dev.ricknout.composesensors.demo.ui.Sensor
import dev.ricknout.composesensors.demo.ui.NotAvailableDemo
import dev.ricknout.composesensors.proximity.getProximitySensor
import dev.ricknout.composesensors.proximity.isProximitySensorAvailable
import dev.ricknout.composesensors.proximity.rememberProximitySensorValueAsState

@Composable
fun ProximityDemo() {
    if (isProximitySensorAvailable()) {
        val sensor = getProximitySensor()
        val sensorValue by rememberProximitySensorValueAsState(initialValue = SensorValue(value = sensor.maximumRange))
        val value = sensorValue.value
        Sensor(
            demo = Demo.PROXIMITY,
            value = "",
        ) {

            val getSensor = getSensorDetails(sensorType = android.hardware.Sensor.TYPE_PROXIMITY)

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
                        painter = painterResource(id = R.drawable.ic_proximity_24dp), contentDescription = null,
                        modifier = Modifier.size(150.dp), tint = Color.White,
                    )
                    Text(text = "Karthik Chitrapu", fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(40.dp))

                Text(text = "Proximity: $value", color = Color(0xFF277ED0), fontSize = 30.sp)

                Spacer(modifier = Modifier.height(260.dp))

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

            val near = value == 0f
            val radiusDp by animateDpAsState(targetValue = if (near) 100.dp else 10.dp)
            val radius = with(LocalDensity.current) { radiusDp.toPx() }
            val contentColor = LocalContentColor.current
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = Color(0xFF277ED0),
                    radius = radius + 20,
                    center = center,
                )
            }
        }
    } else {
        NotAvailableDemo(demo = Demo.PROXIMITY)
    }
}
