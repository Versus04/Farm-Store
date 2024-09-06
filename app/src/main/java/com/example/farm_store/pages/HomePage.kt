package com.example.farm_store.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.farm_store.R
import com.example.farm_store.viewmodels.AuthViewModel

@Composable
fun homePage(
    modifier: Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        WelcomeSection(navController)
        Spacer(modifier = Modifier.height(24.dp))
        FeaturedProductsSection()
        Spacer(modifier = Modifier.height(24.dp))
        CategoriesSection(navController)
    }
}

@Composable
fun WelcomeSection(navController: NavHostController) {
    Row(verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceEvenly){
    Text(
        text = "Welcome to Farm Store",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
    )
        IconButton(onClick = {navController.navigate("Notification")}) { Icon(Icons.Default.Notifications , contentDescription = null ) }
    }
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Fresh produce straight from the farm to your table!",
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun FeaturedProductsSection() {
    Text(
        text = "Featured Products",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(8.dp))
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(featuredProducts) { product ->
            ProductCard(product)
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(200.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = product.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "${product.price} Rupees", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun CategoriesSection(navController: NavHostController) {
    Text(
        text = "Categories",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(8.dp))
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        categories.forEach { category ->
            CategoryButton(category, navController)
        }
    }
}

@Composable
fun CategoryButton(category: String, navController: NavHostController) {
    Button(
        onClick = { navController.navigate("category/$category") },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = category)
    }
}

data class Product(val name: String, val price: Double, val imageResId: Int)

val featuredProducts = listOf(
    Product("Fresh Mangoes", 2.99, R.drawable.mango),
    Product("Organic Apples", 1.99, R.drawable.apple),
    Product("Farm Eggs", 3.99, R.drawable.apple),
    Product("Whole Milk", 2.49, R.drawable.apple)
)

val categories = listOf("Fruits", "Vegetables", "Dairy", "Meat", "Bakery")