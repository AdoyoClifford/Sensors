package dev.ricknout.composesensors.demo.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.ricknout.composesensors.JlResDimens
import dev.ricknout.composesensors.demo.R
import dev.ricknout.composesensors.demo.model.Demo
import dev.ricknout.composesensors.demo.ui.accelerometer.AccelerometerDemo
import dev.ricknout.composesensors.demo.ui.ambienttemperature.AmbientTemperatureDemo
import dev.ricknout.composesensors.demo.ui.gravity.GravityDemo
import dev.ricknout.composesensors.demo.ui.gyroscope.GyroscopeDemo
import dev.ricknout.composesensors.demo.ui.light.LightDemo
import dev.ricknout.composesensors.demo.ui.linearacceleration.LinearAccelerationDemo
import dev.ricknout.composesensors.demo.ui.magneticfield.MagneticFieldDemo
import dev.ricknout.composesensors.demo.ui.pressure.PressureDemo
import dev.ricknout.composesensors.demo.ui.proximity.ProximityDemo
import dev.ricknout.composesensors.demo.ui.relativehumidity.RelativeHumidityDemo

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
fun SensorApp(onItemClick: (demo: Demo) -> Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection).background(Color.LightGray),
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = Color(0xFF277ED0),
                    )
                },
                title = { Text(text = stringResource(id = R.string.app_name)) },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent),
            )
        },
    ) { paddingValues ->
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),

            ) {
            val demos = Demo.values().toList().filter { demo -> demo != Demo.NONE }
            items(demos) { demo ->
                CustomListItem(
                    headlineContent = { Text(text = demo.title) },
                    leadingContent = {
                        Icon(
                            painter = painterResource(id = demo.iconResourceId),
                            contentDescription = null,
                            modifier = Modifier.size(56.dp),
                            tint = Color(0xFF277ED0)
                        )
                    },
                    modifier = Modifier
                        .clickable { onItemClick(demo) },
                    // .padding(paddingValues = paddingValues),
                )
            }
        }
    }
}

@Composable
fun DemoNavigation(
    demo: Demo,
    onItemClick: (demo: Demo) -> Unit,
) {
    Crossfade(targetState = demo, label = "") {
        when (it) {
            Demo.NONE -> SensorApp(onItemClick = onItemClick)
            Demo.ACCELEROMETER -> AccelerometerDemo()
            Demo.AMBIENT_TEMPERATURE -> AmbientTemperatureDemo()
            Demo.GRAVITY -> GravityDemo()
            Demo.GYROSCOPE -> GyroscopeDemo()
            Demo.LIGHT -> LightDemo()
            Demo.LINEAR_ACCELERATION -> LinearAccelerationDemo()
            Demo.MAGNETIC_FIELD -> MagneticFieldDemo()
            Demo.PRESSURE -> PressureDemo()
            Demo.PROXIMITY -> ProximityDemo()
            Demo.RELATIVE_HUMIDITY -> RelativeHumidityDemo()
        }
    }
}

@Composable
fun CustomListItem(
    headlineContent: @Composable () -> Unit,
    leadingContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .size(165.dp)
//            .border(
//                brush = Brush.verticalGradient(
//                    listOf(
//                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
//                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
//                    ),
//                ),
//                width = JlResDimens.dp1,
//                shape = RoundedCornerShape(
//                    topStart = JlResDimens.dp16, topEnd = JlResDimens.dp16,
//                    bottomStart = JlResDimens.dp16, bottomEnd = JlResDimens.dp16,
//                ),
//        ).background(
//            brush = Brush.linearGradient(
//                listOf(
//                    JLThemeBase.colorPrimary.copy(alpha = 0.2f),
////                    JLThemeBase.colorPrimary30,
//                    JLThemeBase.colorPrimary.copy(alpha = 0.0f),
////                    JLThemeBase.colorPrimary20,
//                ),
//                start = Offset(0f, 0f),
//                end = Offset(0f, Float.POSITIVE_INFINITY)
//            )
//        )
        //    ),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            leadingContent()
            Spacer(modifier = Modifier.height(8.dp))
            headlineContent()
        }

    }
}





