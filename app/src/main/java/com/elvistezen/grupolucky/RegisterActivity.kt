package com.elvistezen.grupolucky

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import at.favre.lib.crypto.bcrypt.BCrypt
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializar la base de datos y el DAO
        val database = AppDatabase.getDatabase(applicationContext)
        userDao = database.userDao()

        // Referencias a los elementos del diseño
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword1)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnBackToLogin = findViewById<Button>(R.id.btnBackToLogin1)

        // Evento de botón para registrar usuario
        btnRegister.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            if (validateInput(username, password, confirmPassword)) {
                registerUser(username, password)
            }
        }

        // Botón para volver a Login
        btnBackToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    /**
     * Función para validar que los campos no estén vacíos y que las contraseñas coincidan.
     */
    private fun validateInput(username: String, password: String, confirmPassword: String): Boolean {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    /**
     * Función para registrar al usuario en la base de datos con la contraseña encriptada.
     */
    private fun registerUser(username: String, password: String) {
        lifecycleScope.launch {
            val existingUser = userDao.getUserByUsername(username)
            if (existingUser == null) {
                // Hashear la contraseña antes de almacenarla
                val hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray())

                val newUser = User(username = username, password = hashedPassword)
                userDao.insertUser(newUser)

                runOnUiThread {
                    Toast.makeText(applicationContext, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    finish() // Cerrar la actividad actual
                }
            } else {
                runOnUiThread {
                    Toast.makeText(applicationContext, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
