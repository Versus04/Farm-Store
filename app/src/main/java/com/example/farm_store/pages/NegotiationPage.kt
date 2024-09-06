import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NegotiationPage() {
    val users = listOf("User 1", "User 2", "User 3", "User 4", "User 5", "User 6", "User 7")
    var selectedUsers by remember { mutableStateOf(emptySet<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Text(
            "Negotiations",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4CAF50),
            modifier = Modifier.padding(16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SelectAllButton(
                isChecked = selectedUsers.size == users.size,
                onCheckedChange = { isChecked ->
                    selectedUsers = if (isChecked) users.toSet() else emptySet()
                }
            )
            ActionButton("Accept", Color(0xFFB8E0D2))
            ActionButton("Decline", Color(0xFFB8E0D2))
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(users) { user ->
                NegotiationItem(
                    user = user,
                    isSelected = user in selectedUsers,
                    onSelectChange = { isSelected ->
                        selectedUsers = if (isSelected) {
                            selectedUsers + user
                        } else {
                            selectedUsers - user
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun SelectAllButton(isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Button(
        onClick = { onCheckedChange(!isChecked) },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB8E0D2)),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = null,
                colors = CheckboxDefaults.colors(checkedColor = Color.White, checkmarkColor = Color(0xFFB8E0D2))
            )
            Text("Select all", color = Color.Black)
        }
    }
}

@Composable
fun ActionButton(text: String, color: Color) {
    Button(
        onClick = { /* Handle action */ },
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text, color = Color.Black)
    }
}

@Composable
fun NegotiationItem(user: String, isSelected: Boolean, onSelectChange: (Boolean) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFB8E0D2))
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(getColorForUser(user), CircleShape)
            )
            Text(
                "Discount request from $user",
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                color = Color.Black
            )
            IconButton(onClick = { onSelectChange(true) }) {
                Icon(Icons.Default.Check, contentDescription = "Accept", tint = Color(0xFF4CAF50))
            }
            IconButton(onClick = { onSelectChange(false) }) {
                Icon(Icons.Default.Close, contentDescription = "Decline", tint = Color.Red)
            }
        }
    }
}

fun getColorForUser(user: String): Color {
    return when (user) {
        "User 1" -> Color.Red
        "User 2" -> Color.Yellow
        "User 3" -> Color.Blue
        "User 4" -> Color.Cyan
        "User 5" -> Color.Magenta
        "User 6" -> Color.Yellow
        "User 7" -> Color.Magenta
        else -> Color.Gray
    }
}