package com.elvistezen.grupolucky.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elvistezen.grupolucky.R
import com.elvistezen.grupolucky.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private var recyclerView: RecyclerView? = null
    private var phraseAdapter: PhraseAdapter? = null
    private var phraseList: MutableList<Phrase>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding!!.getRoot()

        recyclerView = binding!!.recyclerView
        recyclerView!!.layoutManager = LinearLayoutManager(context)

        phraseList = ArrayList()
        loadPhrases()

        phraseAdapter = PhraseAdapter(phraseList) { phrase ->
            // Navegar a GalleryFragment y pasar la frase seleccionada
            val navController =
                findNavController(requireActivity(), R.id.nav_host_fragment_content_main)
            val bundle = Bundle()
            bundle.putString("author", phrase.author)
            bundle.putString("phrase", phrase.phrase)
            bundle.putString("meaning", phrase.meaning)
            navController.navigate(R.id.nav_gallery, bundle)
        }
        recyclerView!!.adapter = phraseAdapter

        return root
    }

    private fun loadPhrases() {
        val phrases = arrayOf(
            Phrase(
                1,
                "YOSLY AMALI SEGUILAR",
                "Código: 109283 - REAL S/N Urb: ESQ. 10 NOVIEMBRE",
                "--"
            ),
            Phrase(
                2,
                "METRO ALFONSO UGARTE",
                "Código: 409184 - AV. ALFONSO UGARTE 740",
                "--"
            ),
            Phrase(
                3,
                "TOTTUS ZORRITOS",
                "Código: 409185 - AV. COLONIAL 1520",
                "--"
            ),
            Phrase(
                4,
                "METRO FAUCETT",
                "Código: 409186 - AV. VENEZUELA 5415",
                "--"
            ),
            Phrase(
                5,
                "METRO LA MOLINA",
                "Código: 409187 - AV. LA MARINA 2500",
                "--"
            )
        )
        for (phrase in phrases) {
            phraseList!!.add(phrase)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
