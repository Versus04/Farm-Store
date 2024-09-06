import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun NotificationsPage(navController: NavHostController) {
    val notifications =
       listOf(
            Notification(1, "A buyer has placed an order for your Potatoes."),
            Notification(2, "New price offer from for Desi Gobi."),
            Notification(3, "Payment of ₹48847 received for your order #88745."),
            Notification(4, "Your order #9595 has been shipped."),
            Notification(5, "Order #5 delivered successfully"),
            Notification(6, "Weekly market report: Top-selling products in your area."),
            Notification(7, "New features added to App. Check them out."),
            Notification(8, "Reminder: Respond to negotiation request from Harvey Specter.")
       )


    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Notifications",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /* Select all logic */ }) {
                Text("Select all")
            }
            Button(onClick = { /* Read all logic */ }) {
                Text("Read all")
            }
            IconButton(onClick = { /* Delete logic */ }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(notifications) { notification ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = notification.isChecked,
                        onCheckedChange = { isChecked ->
                            notification.isChecked = isChecked
                        }
                    )
                    Text(
                        text = notification.message,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

data class Notification(
    val id: Int,
    val message: String,
    var isChecked: Boolean = false
)