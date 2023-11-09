package com.example.sistemalogin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemalogin.Adapter.AdapterMenu
import com.example.sistemalogin.model.clubes
import com.example.sistemalogin.model.opcoes


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [menu.newInstance] factory method to
 * create an instance of this fragment.
 */
class menu : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var adapter: AdapterMenu
    private lateinit var recyclerView: RecyclerView
    private lateinit var opcoesArrayList: ArrayList<opcoes>

    lateinit var img_menu: Array<Int>
    lateinit var nome_opcao: Array<String>

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
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        val telaPerfil = view.findViewById<TextView>(R.id.ver_perfil)
        telaPerfil.setOnClickListener {
            val intent = Intent(requireContext(), tela_perfil::class.java)
            startActivity(intent)
        }

        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment menu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            menu().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerview_menu)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = AdapterMenu(opcoesArrayList)
        recyclerView.adapter=adapter
    }

    private fun dataInitialize() {
        opcoesArrayList = arrayListOf()

            img_menu = arrayOf(
                R.drawable.baseline_people_alt_24,
                R.drawable.baseline_computer_24,
                R.drawable.baseline_insert_comment_24
            )
            nome_opcao = arrayOf (
            getString(R.string.Configuracoes),
            getString(R.string.Sobre),
            getString(R.string.Comente),
        )



        for (i in img_menu.indices) {
            val opcoes_menu = opcoes(img_menu[i], nome_opcao[i])
            opcoesArrayList.add(opcoes_menu)
        }
    }

}
