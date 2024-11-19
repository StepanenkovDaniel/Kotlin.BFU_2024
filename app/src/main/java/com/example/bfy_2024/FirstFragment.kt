package com.example.bfy_2024


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FirstFragment : Fragment() {

    private val myAdapter = PhonesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_first, container, false)

        root.findViewById<RecyclerView>(R.id.recyclerView).layoutManager=LinearLayoutManager(requireContext())
        root.findViewById<RecyclerView>(R.id.recyclerView).adapter=myAdapter

        loadData()

        return root
    }

    private fun loadData(){
        myAdapter.setupPhones(PhoneData.phonesArr)
    }
}