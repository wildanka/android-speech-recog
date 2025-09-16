package com.example.speechrecognitionapp

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Objects

class MainActivity : AppCompatActivity() {

    // Request code for speech input intent
    val REQUEST_CODE_SPEECH_INPUT: Int = 1
    // UI elements
    private lateinit var microphoneButton: ImageView
    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        microphoneButton = findViewById(R.id.iv_mic)
        textView = findViewById(R.id.tv_speech_to_text)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    // Handle the result from speech recognition activity
    @Deprecated("Deprecated in Java") // Still works, just marked as deprecated in newer APIs
    override fun onActivityResult(
        requestCode: Int, resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check if the result is from our speech input request
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                // Get the list of results from speech recognizer
                val result = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS
                )

                // Display the first recognized phrase in the TextView
                textView.text = Objects.requireNonNull<ArrayList<String>?>(result)[0]
            }
        }
    }
}