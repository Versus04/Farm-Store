import android.app.Notification
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.farm_store.data.navitem
import com.example.farm_store.pages.AccountPage
import com.example.farm_store.pages.SearchPage
import com.example.farm_store.pages.cartPage
import com.example.farm_store.pages.homePage
import com.example.farm_store.pages.insidepages.WeeklyReportPage
import com.example.farm_store.viewmodels.AuthViewModel

@Composable
fun AppScreen( modifier: Modifier,authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    var selected by remember { mutableIntStateOf(0) }
    val bars = listOf(
        navitem("Home", Icons.Default.Home, { navController.navigate("Home") }),
        navitem("Search", Icons.Default.Search, { navController.navigate("Search") }),
        navitem("Cart", Icons.Default.ShoppingCart, { navController.navigate("Cart") }),
        navitem("Bargain", Icons.Default.Person, { navController.navigate("Negotiation") }),
        navitem("Account", Icons.Default.AccountCircle, { navController.navigate("Account") })
    )

    Scaffold(
        bottomBar = {
            if (isLoggedIn == true) {
                NavigationBar {
                    bars.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = selected == index,
                            onClick = {
                                selected = index
                                item.onClick()
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            ContentScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }
}

@Composable
fun ContentScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    NavHost(
        navController,
        startDestination = if (isLoggedIn == true) "Home" else "login"
    ) {
        composable("login") { LoginPage(Modifier, navController, authViewModel) }
        composable("signUp") { SignUpPage(Modifier, navController, authViewModel) }
        composable("Home") { homePage(Modifier, navController, authViewModel) }
        composable("Cart") { cartPage() }
        composable("Search") { SearchPage() }
        composable("Negotiation") {NegotiationPage() }
        composable("Account") {
            AccountPage(
                onLogout = {
                    // Handle logout logic here
                    authViewModel.signout()
                    navController.navigate("login") {
                        popUpTo("Home") { inclusive = true }
                    }
                }
             , navController = navController)
        }
        composable("Notification") {NotificationsPage(navController) }
        composable("weekly") {WeeklyReportPage(navController) }

    }
}