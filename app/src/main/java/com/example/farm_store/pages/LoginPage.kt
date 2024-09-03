import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.farm_store.viewmodels.AuthState
import com.example.farm_store.viewmodels.AuthViewModel

@Composable
fun LoginPage(modifier: Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val context = LocalContext.current

    val authState=authViewModel.authState.observeAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val gradientColors = listOf(Color(0xFFCFFFBE), Color(0xFF7BFF4C))
    val brush = Brush.verticalGradient(gradientColors)
    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> {
                navController.navigate("Home")
            }
            is AuthState.Error ->{
                Toast.makeText(context,authState.value.toString(),Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                drawRect(brush = brush, alpha = 0.3f)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Welcome Back",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(48.dp))

            CustomTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                icon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password,
                isPassword = true
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { authViewModel.login(email,password) },
                enabled = authState.value!=AuthState.Loading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(28.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    "Login",
                    color = gradientColors[0],
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = { navController.navigate("signup") }) {
                Text("Don't have an account? Sign Up", color = Color.Black)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            disabledTextColor = Color.Black.copy(alpha = 0.5f),
            cursorColor = Color.Black,
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black.copy(alpha = 0.7f),
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black.copy(alpha = 0.7f),
            focusedLeadingIconColor = Color.Black,
            unfocusedLeadingIconColor = Color.Black.copy(alpha = 0.7f)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = true
    )
}