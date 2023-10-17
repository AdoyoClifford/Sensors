package dev.ricknout.composesensors.demo.model

import android.hardware.Sensor
import androidx.compose.runtime.Composable
import dev.ricknout.composesensors.getSensor

@Composable
fun getSensorDetails(sensorType: Int): SensorDetails {
    val sensor = getSensor(sensorType)

    return SensorDetails(
        name = sensor.name,
        vendor = sensor.type.toString(),
        range = sensor.maximumRange,
        resolution = sensor.resolution,
        power = sensor.power,
    )
}

data class SensorDetails(
    val name: String,
    val vendor: String,
    val range: Float,
    val resolution: Float,
    val power: Float,
)