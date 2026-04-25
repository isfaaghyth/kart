package app.isfa.kart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.isfa.kart.shared.Greeting
import com.isfa.kart.design.KartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KartTheme {
                AppHost()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        println("fromAndroid: ${Greeting().greet()}")
    }
}
