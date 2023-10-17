package dev.ricknout.composesensors.demo.ui

import android.hardware.Sensor
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ricknout.composesensors.demo.R
import dev.ricknout.composesensors.demo.model.Demo
import dev.ricknout.composesensors.demo.model.getSensorDetails

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Sensor(
    demo: Demo,
    value: String,
    content: @Composable BoxWithConstraintsScope.() -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = demo.title, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(
                        0xFF277ED0,
                    ),
                )
            )
        },
    ) { paddingValues ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
        ) {


            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterStart),
                text = value,
                fontSize = 24.sp,
                color = Color(0xFF277ED0),
            )
            content()
        }
    }
}


@Composable
fun NotAvailableDemo(demo: Demo) {
    Sensor(
        demo = demo,
        value = "Not available",
        content = {},
    )
}
