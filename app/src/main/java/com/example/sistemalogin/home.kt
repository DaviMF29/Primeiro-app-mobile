package com.example.sistemalogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemalogin.Adapter.AdapterClube
import com.example.sistemalogin.form.viewModel
import com.example.sistemalogin.model.clubes

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



class home : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var clubesViewModel: viewModel
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter: AdapterClube
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        clubesViewModel = ViewModelProvider(this).get(viewModel::class.java)
        // Restante do código do seu onCreateView
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clubesViewModel = ViewModelProvider(this).get(viewModel::class.java)
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerview_clubes)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        // Obtenha a lista de todos os clubes e configure o Adapter
        val todosOsClubes = clubesViewModel.listaTimesFutebol
        adapter = AdapterClube(todosOsClubes)
        recyclerView.adapter = adapter
    }



}