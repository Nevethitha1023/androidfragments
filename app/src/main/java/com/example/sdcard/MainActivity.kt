package com.example.sdcard

import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sdcard.ui.theme.SdcardTheme

class MainActivity : ComponentActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextCGPA: EditText
    private lateinit var buttonSave: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editTextName = findViewById(R.id.editTextName)
        editTextCGPA = findViewById(R.id.editTextCGPA)
        buttonSave = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            saveToSDCard(editTextName.text.toString(), editTextCGPA.text.toString())
        }
    }

    private fun saveToSDCard(name: String, cgpa: String) {
        val state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED == state) {
            val sdCard = Environment.getExternalStorageDirectory()
            val directory = File(sdCard.absolutePath + "/MyApp")
            directory.mkdirs()
            val file = File(directory, "student.txt")
            val fos = FileOutputStream(file)
            val osw = OutputStreamWriter(fos)
            osw.write("Name: $name\n")
            osw.write("CGPA: $cgpa\n")
            osw.close()
            fos.close()
            editTextName.setText("")
            editTextCGPA.setText("")
        }
    }

}