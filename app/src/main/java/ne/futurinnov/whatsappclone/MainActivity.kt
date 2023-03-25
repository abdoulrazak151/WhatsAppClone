package ne.futurinnov.whatsappclone

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ne.futurinnov.whatsappclone.composable.Center
import ne.futurinnov.whatsappclone.ui.theme.WhatsAppCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsAppCloneTheme {
                val composableScope =rememberCoroutineScope()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    SplashView()
                    composableScope.launch{
                        delay(1000)
                        startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                    }

                }
            }
        }
    }
}

@Composable
fun SplashView() {
     Center(
         modifier = Modifier.fillMaxWidth()
     ) {
        Image(
            painter = painterResource(id = R.drawable.ic_whatsapp), contentDescription =null, modifier = Modifier.size(100.dp) )
     }
}

