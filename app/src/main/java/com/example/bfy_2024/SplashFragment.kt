package com.example.bfy_2024

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_splash, container, false)
        val navController = NavHostFragment.findNavController(this)
        val SharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        if((SharedPreferences.getString("email", "null")!="null") || (SharedPreferences.getString("phone", "null")!="null"))
            if(SharedPreferences.getBoolean("login_status", false)){
                navController.navigate(R.id.oneFragment)
            }
            else{
                navController.navigate(R.id.loginFragment)
            }
        else{
            navController.navigate(R.id.registrFragment)
        }
        return root
    }
}
