package com.example.farm_store.pages.insidepages
// ... existing imports ...
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.farm_store.viewmodels.AuthViewModel
import java.text.NumberFormat
import java.util.*


data class WeeklyReport(
    val totalSales: Double,
    val totalOrders: Int,
    val averageOrderValue: Double,
    val topSellingProducts: List<ProductSale>
)

data class ProductSale(
    val name: String,
    val quantity: Int,
    val revenue: Double
)

@Composable
fun WeeklyReportPage(navController: NavHostController) {
    val report = remember {
        WeeklyReport(
            totalSales = 125000.0,
            totalOrders = 45,
            averageOrderValue = 2777.78,
            topSellingProducts = listOf(
                ProductSale("Potatoes", 500, 25000.0),
                ProductSale("Tomatoes", 300, 18000.0),
                ProductSale("Onions", 400, 16000.0),
                ProductSale("Carrots", 250, 12500.0),
                ProductSale("Cabbage", 200, 10000.0)
            )
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Weekly Report",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            item {
                KeyMetrics(report)
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Top Selling Products",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            items(report.topSellingProducts) { product ->
                ProductSaleItem(product)
            }
        }
    }
}

@Composable
fun KeyMetrics(report: WeeklyReport) {
    val currencyFormat = remember { NumberFormat.getCurrencyInstance(Locale("en", "IN")) }

    Column {
        MetricItem("Total Sales", currencyFormat.format(report.totalSales))
        MetricItem("Total Orders", report.totalOrders.toString())
        MetricItem("Average Order Value", currencyFormat.format(report.averageOrderValue))
    }
}

@Composable
fun MetricItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyLarge)
        Text(value, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ProductSaleItem(product: ProductSale) {
    val currencyFormat = remember { NumberFormat.getCurrencyInstance(Locale("en", "IN")) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(product.name, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
        Text("${product.quantity} kg", style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.End, modifier = Modifier.weight(1f))
        Text(currencyFormat.format(product.revenue), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, textAlign = TextAlign.End, modifier = Modifier.weight(1f))
    }
}

