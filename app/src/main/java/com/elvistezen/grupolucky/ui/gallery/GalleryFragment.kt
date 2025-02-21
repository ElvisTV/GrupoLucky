package com.elvistezen.grupolucky.ui.gallery

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.elvistezen.grupolucky.R
import com.elvistezen.grupolucky.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {
    private var binding: FragmentGalleryBinding? = null
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding!!.getRoot()

        // Reproduce el sonido
        playSound();

        // Inicializar el SurfaceView animado de estrellas
        val starSurfaceView: StarSurfaceView = root.findViewById(R.id.star_surface_view)

        // Obtener datos del bundle
        val args = arguments
        if (args != null) {
            val author = args.getString("author")
            val phrase = args.getString("phrase")
            val meaning = args.getString("meaning")

            // Mostrar los datos
            binding!!.textAutor.setText(author)
            binding!!.textFrase.setText(phrase)
        }

        return root
    }

    private fun playSound() {
        if (context != null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.sonido)
            if (mediaPlayer != null) {
                mediaPlayer!!.start()
                mediaPlayer!!.setOnCompletionListener { mp: MediaPlayer -> mp.release() }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (mediaPlayer != null) {
            mediaPlayer!!.release()
            mediaPlayer = null
        }
        binding = null
    }
}
