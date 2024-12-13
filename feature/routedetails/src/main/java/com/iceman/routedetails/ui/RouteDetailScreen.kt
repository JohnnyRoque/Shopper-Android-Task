import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iceman.data.model.Driver
import com.iceman.data.network.request.ConfirmTripRequest
import com.iceman.designsystem.components.ShopperCard
import com.iceman.domain.model.ScreenState
import com.iceman.routedetails.ui.RouteDetailViewModel
import com.iceman.ui.DriverOption
import com.iceman.ui.StaticMapImage
import org.koin.androidx.compose.koinViewModel
import com.iceman.routedetails.R

@Composable
fun RouteDetailScreen(
    modifier: Modifier = Modifier,
    screenState: ScreenState,
    viewModel: RouteDetailViewModel = koinViewModel(),
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val confirmState by viewModel.confirmRideState.collectAsState()

    LaunchedEffect(confirmState.success) {
        if (confirmState.success) {
            Toast.makeText(context, "Corrida Confirmada com sucesso!!", Toast.LENGTH_SHORT).show()
            onClick()
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        StaticMapImage(
            mapUrl = viewModel.getLocationRoute(
                origin = screenState.estimateTrip.origin,
                destination = screenState.estimateTrip.destination
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        ShopperCard(Modifier.padding(16.dp)) {
            Row(Modifier.padding(12.dp)) {
                Column {
                    Text(
                        text = screenState.origin,
                        modifier = Modifier.padding(bottom = 8.dp),
                    )
                    HorizontalDivider(Modifier.height(4.dp))
                    Text(
                        text = screenState.destination,
                        modifier = Modifier.padding(bottom = 8.dp),
                    )
                }
            }
        }

        Text(
            stringResource(R.string.user_id_title),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        ShopperCard(modifier = Modifier.padding(12.dp)) {
            Text(
                text = screenState.userId,
                modifier = Modifier.padding(12.dp),
            )
        }

        LazyColumn(modifier = Modifier.padding(horizontal = 12.dp)) {
            items(screenState.estimateTrip.options) { option ->
                DriverOption(rideOption = option) {
                    viewModel.confirmRide(
                        ConfirmTripRequest(
                            customerId = screenState.userId,
                            origin = screenState.origin,
                            destination = screenState.destination,
                            distance = screenState.estimateTrip.distance,
                            driver = Driver(option.id, option.name),
                            value = option.value
                        )
                    )
                }
            }
        }
    }
}
