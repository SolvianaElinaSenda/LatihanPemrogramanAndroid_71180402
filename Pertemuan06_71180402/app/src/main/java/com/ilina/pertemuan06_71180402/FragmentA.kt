package com.ilina.pertemuan06_71180402

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class FragmentA : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_a, container, false)
        val nextBtn: Button =view.findViewById(R.id.btnH1)
        nextBtn.setOnClickListener {
            val fragmentB=FragmentB()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmen,fragmentB)?.commit()
        }
        return view
    }

}