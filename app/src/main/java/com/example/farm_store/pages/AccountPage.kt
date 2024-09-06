package com.example.farm_store.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.farm_store.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountPage(onLogout: () -> Unit , navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        // Green header
Row(horizontalArrangement = Arrangement.Center , verticalAlignment = Alignment.CenterVertically) {
    Text("Hello Sukant Bihari", fontSize = 20.sp, fontWeight = FontWeight.Bold)
}
        Spacer(modifier = Modifier.height(16.dp))

        // Account options
        AccountOption(
            icon = Icons.Default.DateRange,
            text = "WEEKLY REPORT"
            , onclick = { navController.navigate("weekly") }
        )
        AccountOption(
            icon = Icons.Default.Person,
            text = "MY PROFILE",onclick = {navController.navigate("weekly")}
        )
        AccountOption(
            icon = Icons.Default.Person,
            text = "TERMS AND CONDITIONS",onclick = {navController.navigate("weekly")}
        )
        AccountOption(
            icon = Icons.Default.Person,
            text = "PRIVACY CENTRE",onclick = {navController.navigate("weekly")}
        )
        AccountOption(
            icon = Icons.Default.Person,
            text = "HELP AND SUPPORT",onclick = {navController.navigate("weekly")}
        )

        Spacer(modifier = Modifier.weight(1f))

        // Logout button
        Button(
            onClick = onLogout,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        ) {
            Text(
                "LOGOUT",
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountOption(icon: ImageVector, text: String ,onclick : ()-> Unit ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = {}
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}
@Composable
fun ImprovedBanner(imageResId: Int, greeting: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF4CAF50))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .width(80.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF7E57C2))
                ) {
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = "User Avatar",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = greeting,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}