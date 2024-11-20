package com.example.bfy_2024

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

class RegisterFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_registration, container, false)
        val navController= NavHostFragment.findNavController(this)

        var isOptionSelected = false //Флаг(кнопка)
        val number_phone_button = root.findViewById<Button>(R.id.buttonByNumber)
        val email_phone = root.findViewById<Button>(R.id.buttonByEmail)
        val email_users_taxt = root.findViewById<EditText>(R.id.user_email)
        val password_try = root.findViewById<EditText>(R.id.user_pasword)
        val regestr_button = root.findViewById<Button>(R.id.reg_butt)
        val password_more = root.findViewById<EditText>(R.id.user_pasword_more)
        val storage = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

        number_phone_button.setOnClickListener(){
            if (!isOptionSelected){
                number_phone_button.setBackgroundColor(Color.GREEN)
                email_phone.setBackgroundColor(Color.WHITE)
                isOptionSelected = true
            }
            email_users_taxt.hint = "Введите телефон"
            email_users_taxt.inputType = InputType.TYPE_CLASS_PHONE
        }

        email_phone.setOnClickListener(){
            if(isOptionSelected){
                email_phone.setBackgroundColor(Color.RED)
                number_phone_button.setBackgroundColor(Color.WHITE)
                isOptionSelected = false
            }
            email_users_taxt.hint = "Введите email"
            email_users_taxt.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }
            regestr_button.setOnClickListener {
            val input = email_users_taxt.text.toString().trim() // Либо email, либо телефон
            val password = password_more.text.toString().trim()

            // Проверка на корректность введеных данных
            if (isOptionSelected) { // Регистрация по номеру телефона
                if (!input.contains("+")) {
                    Toast.makeText(activity, "Некорректный номер телефона", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            } else { // Регистрация по email
                if (!input.contains("@")) {
                    Toast.makeText(activity, "Некорректный email", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            if (password.length < 8) {
                Toast.makeText(activity, "Пароль должен содержать 8 символов", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password != password_try.text.toString()) {
                Toast.makeText(activity, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                if(email_users_taxt.hint == "Введите email"){
                    storage.edit().putString("email", email_users_taxt.text.toString()).apply()
                }
                else if(email_users_taxt.hint == "Введите телефон"){
                    storage.edit().putString("phone", email_users_taxt.text.toString()).apply()
                }
                storage.edit().putString("password", password_try.text.toString()).apply()
                navController.navigate(R.id.oneFragment)
            }
        }
        return root
    }
}