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
                "Theodore Roosevelt",
                "Cree que puedes y estarás a mitad de camino.",
                "La confianza en uno mismo es fundamental para alcanzar cualquier meta."
            ),
            Phrase(
                2,
                "Nelson Mandela",
                "Todo parece imposible hasta que se hace.",
                "Los grandes retos pueden parecer insuperables, pero con perseverancia se logran."
            ),
            Phrase(
                3,
                "Mahatma Gandhi",
                "Sé el cambio que deseas ver en el mundo.",
                "El cambio global empieza con nuestras propias acciones cotidianas."
            ),
            Phrase(
                4,
                "Confucio",
                "No importa lo lento que vayas, siempre y cuando no te detengas.",
                "El progreso es progreso, sin importar el ritmo. Lo importante es seguir adelante."
            ),
            Phrase(
                5,
                "George Eliot",
                "Nunca es demasiado tarde para ser quien podrías haber sido.",
                "No importa la edad o las circunstancias, siempre hay tiempo para reinventarse."
            ),
            Phrase(
                6,
                "Peter Drucker",
                "La mejor manera de predecir el futuro es crearlo.",
                "No debemos esperar que el futuro suceda; debemos trabajar para construirlo."
            ),
            Phrase(
                7,
                "Napoleon Hill",
                "No esperes. El momento nunca será perfecto.",
                "No existe un momento ideal para comenzar algo nuevo. La clave es actuar ya."
            ),
            Phrase(
                8,
                "Robert Collier",
                "El éxito es la suma de pequeños esfuerzos repetidos día tras día.",
                "Las pequeñas acciones constantes son las que realmente nos acercan a nuestras metas."
            ),
            Phrase(
                9,
                "Dalai Lama",
                "La felicidad no es algo hecho. Viene de tus propias acciones.",
                "La felicidad es un resultado directo de nuestras elecciones y actitudes diarias."
            ),
            Phrase(
                10,
                "Henry Ford",
                "El fracaso es una gran oportunidad para empezar otra vez con más inteligencia.",
                "El fracaso es una gran oportunidad para empezar otra vez con más inteligencia."
            ),
            Phrase(
                11,
                "Muhammad Ali",
                "Cuanto más duro trabajo, más suerte tengo.",
                "El esfuerzo constante es la clave para atraer oportunidades y crear suerte."
            ),
            Phrase(
                12,
                "Roy T. Bennett",
                "No temas equivocarte, teme no intentarlo.",
                "El verdadero fracaso no está en cometer errores, sino en nunca haberlo intentado."
            ),
            Phrase(
                13,
                "Vidal Sassoon",
                "No tengas miedo de renunciar a lo bueno para ir por lo grandioso.",
                "No hay atajos para el éxito. Siempre requiere esfuerzo, trabajo duro y dedicación."
            ),
            Phrase(
                14,
                "Michael Jordan",
                "Tienes que esperar cosas de ti mismo antes de poder hacerlas.",
                "La fe en nuestras propias habilidades es el primer paso para lograr algo grande."
            ),
            Phrase(
                15,
                "Jim Rohn",
                "La disciplina es el puente entre las metas y los logros.",
                "La disciplina diaria es lo que convierte los sueños en realidad. Sin ella, las metas siguen siendo deseos."
            ),
            Phrase(
                16,
                "Sócrates",
                "El secreto del cambio es enfocar toda tu energía, no en luchar contra lo viejo, sino en construir lo nuevo",
                "El cambio positivo proviene de concentrarse en el futuro y en lo que podemos crear, no en lo que hemos dejado atrás"
            ),
            Phrase(
                17,
                "Robert T. Kiyosaki",
                "El fracaso derrota a los perdedores e inspira a los ganadores.",
                "Los fracasos son oportunidades para aprender y mejorar. Los vencedores los ven como parte del camino hacia el éxito."
            ),
            Phrase(
                18,
                "Maya Angelou",
                "El éxito es amar la vida y atreverte a vivirla.",
                "Vivir plenamente y con valentía es la verdadera definición de éxito."
            ),
            Phrase(
                19,
                "Samuel Johnson",
                "Las grandes obras no son hechas con fuerza, sino con perseverancia",
                "La constancia, más que la fuerza, es lo que permite lograr cosas extraordinarias."
            ),
            Phrase(
                20,
                "F. Scott Fitzgerald",
                "Nuestras vidas se definen por las oportunidades, incluso las que perdemos.",
                "Cada oportunidad, tomada o no, moldea el curso de nuestras vidas. Debemos estar atentos a las que se presentan."
            ),
            Phrase(
                21,
                "John Wooden",
                "Haz de cada día tu obra maestra.",
                "No esperes para hacer algo extraordinario en el futuro. Aprovecha cada día para crear algo valioso."
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
