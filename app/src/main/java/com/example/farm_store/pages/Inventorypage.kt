package com.example.farm_store.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farm_store.R

@Composable
fun InventoryScreen() {
    val backgroundColor = Color(0xFFE8F5E9)
    val buttonColor = Color(0xFF4CAF50)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "My Inventory",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                onClick = { /* Add new product logic */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Add a new Product", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Inventory Items",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            InventoryItemsHeader()

            InventoryItemsList()
        }

        // Bottom Navigation
        BottomNavigation(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun InventoryItemsHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Items", fontWeight = FontWeight.Bold)
        Text("Quantity", fontWeight = FontWeight.Bold)
        Text("Price", fontWeight = FontWeight.Bold)
    }
}

@Composable
fun InventoryItemsList() {
    val items = listOf(
        InventoryItem("WHEAT", 25, 40, R.drawable.pexels_pixabay_54084),
        InventoryItem("TOMATO", 10, 20, R.drawable.pexels_pixabay_54084),
        InventoryItem("POTATO", 30, 50, R.drawable.pexels_pixabay_54084),
        InventoryItem("CARROT", 15, 55, R.drawable.pexels_pixabay_54084)
    )

    LazyColumn {
        items(items) { item ->
            InventoryItemRow(item)
        }
    }
}

@Composable
fun InventoryItemRow(item: InventoryItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFB9F6CA))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.name,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(item.name)
            }
            Text("${item.quantity} kg")
            Text("₹${item.price}/kg")
        }
    }
}

@Composable
fun BottomNavigation(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(imageVector = Icons.Default.ShoppingCart , contentDescription = null)
      Icon(imageVector = Icons.Default.ShoppingCart , contentDescription = null)
        Icon(imageVector = Icons.Default.ShoppingCart , contentDescription = null)
    }
}

data class InventoryItem(val name: String, val quantity: Int, val price: Int, val imageRes: Int)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InventoryScreenPreview() {
    // Wrap your InventoryScreen in your app's theme if you have one
    // MyAppTheme {
    InventoryScreen()
    // }
}

// If you need to preview different states, you can use a PreviewParameterProvider
