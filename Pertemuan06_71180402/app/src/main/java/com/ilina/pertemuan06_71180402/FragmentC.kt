package com.ilina.pertemuan06_71180402

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class FragmentC : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_c, container, false)
        val hal1Btn: Button =view.findViewById(R.id.button3)
        hal1Btn.setOnClickListener {
            val fragment=FragmentA()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmen,fragment)?.commit()
        }
        return view
    }
}


