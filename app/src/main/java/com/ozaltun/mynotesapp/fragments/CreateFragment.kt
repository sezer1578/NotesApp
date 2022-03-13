package com.ozaltun.mynotesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.ozaltun.mynotesapp.R
import com.ozaltun.mynotesapp.databinding.FragmentCreateBinding
import com.ozaltun.mynotesapp.model.Notes
import com.ozaltun.mynotesapp.viewmodel.NotesViewModel
import java.util.*

class CreateFragment : Fragment() {
    lateinit var binding: FragmentCreateBinding
    var priority:String = "1"
    val viewModel:NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateBinding.inflate(layoutInflater,container,false)
        binding.pGreen.setOnClickListener {
            priority = "1"
            Toast.makeText(requireContext(), "${binding.pGreen.text}", Toast.LENGTH_SHORT).show()
        }
        binding.pYellow.setOnClickListener {
            priority = "2"
            Toast.makeText(requireContext(), "${binding.pYellow.text}", Toast.LENGTH_SHORT).show()
        }
        binding.pRed.setOnClickListener {
            priority = "3"
            Toast.makeText(requireContext(), "${binding.pRed.text}", Toast.LENGTH_SHORT).show()
        }

        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }
        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.edtTitle.text.toString()
        val notes = binding.edtNotes.text.toString()
        val d= Date()
        val notesDate: CharSequence = android.text.format.DateFormat.format("MMMM d yyyy ", d.time)

        val data = Notes(
            id = null,
            title = title,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewModel.addNotes(data)
        Navigation.findNavController(it!!).navigate(R.id.action_createFragment_to_homeFragment)
    }
}