@file:Suppress("DEPRECATION")

package com.example.biometric_custom

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
import androidx.core.hardware.fingerprint.FingerprintManagerCompat.AuthenticationResult
import androidx.core.hardware.fingerprint.FingerprintManagerCompat.from
import androidx.core.os.CancellationSignal
import androidx.fragment.app.FragmentActivity
import com.example.biometric_custom.ui.theme.Biometric_customTheme
import java.util.concurrent.Executor


class MainActivity : FragmentActivity() {
    private lateinit var executor: Executor

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Biometric_customTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        executor = ContextCompat.getMainExecutor(this)
                        FloatingActionButton(
                            onClick = {
                                val fingerprintManager = from(this@MainActivity)
                                val cancellationSignal = CancellationSignal()
                                fingerprintManager.authenticate(
                                    null, 0, cancellationSignal,
                                    object : AuthenticationCallback() {
                                        override fun onAuthenticationError(
                                            errorCode: Int,
                                            errString: CharSequence
                                        ) {
                                            super.onAuthenticationError(errorCode, errString)
                                            Toast.makeText(
                                                applicationContext,
                                                "Authentication error: $errString",
                                                Toast.LENGTH_SHORT,
                                            )
                                                .show()
                                        }

                                        override fun onAuthenticationSucceeded(result: AuthenticationResult?) {
                                            super.onAuthenticationSucceeded(result)
                                            Toast.makeText(
                                                applicationContext,
                                                "Authentication succeeded!", Toast.LENGTH_SHORT
                                            )
                                                .show()
                                        }

                                        override fun onAuthenticationFailed() {
                                            super.onAuthenticationFailed()
                                            Toast.makeText(
                                                applicationContext, "Authentication failed",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                        }
                                    },
                                    null,
                                )
                            }
                        ) {
                            Text(text = "Scan")
                        }
                    },
                ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Biometric_customTheme {
        Greeting("Android")
    }
}