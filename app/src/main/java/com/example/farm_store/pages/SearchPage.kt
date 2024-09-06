package com.example.farm_store.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.farm_store.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage() {
    var searchQuery by remember { mutableStateOf("") }
    val categories = listOf("Grains", "Vegetables", "Fruits")
    val products = listOf(
        product("Wheat", R.drawable.mango),
        product("Rice", R.drawable.pexels_pixabay_54084),
        product("Millet", R.drawable.mango),
        product("Corn", R.drawable.pexels_pixabay_54084),
        product("Buckwheat", R.drawable.pexels_pixabay_54084)
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Discover local farmers & products") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            shape = RoundedCornerShape(50)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Category chips
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            categories.forEach { category ->
                ElevatedFilterChip(
                    selected = false,
                    onClick = { /* Handle category selection */ },
                    label = { Text(category) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Product grid
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ProductImage(products[0], Modifier.fillMaxWidth().height(200.dp))
            }
            items(products.subList(1, products.size).chunked(2)) { rowProducts ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    rowProducts.forEach { product ->
                        ProductImage(
                            product,
                            Modifier.weight(1f).height(150.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductImage(product: product, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = product.imageResId),
        contentDescription = product.name,
        modifier = modifier.clip(RoundedCornerShape(16.dp)),
        contentScale = ContentScale.Crop
    )
}

data class product(val name: String, val imageResId: Int)