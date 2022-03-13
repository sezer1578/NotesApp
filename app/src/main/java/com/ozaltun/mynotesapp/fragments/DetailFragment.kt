package com.ozaltun.mynotesapp.fragments

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ozaltun.mynotesapp.MainActivity
import com.ozaltun.mynotesapp.R
import com.ozaltun.mynotesapp.databinding.FragmentDetailBinding
import com.ozaltun.mynotesapp.model.Notes
import com.ozaltun.mynotesapp.viewmodel.NotesViewModel
import java.util.*

class DetailFragment : Fragment() {
    val oldNotes by navArgs<DetailFragmentArgs>()
    lateinit var binding: FragmentDetailBinding
    var priority:String = "1"
    val viewModel:NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        binding.edtTitle.setText(oldNotes.data.title)
        binding.edtNotes.setText(oldNotes.data.notes)

        when (oldNotes.data.priority) {
            "1" -> {
                priority = "1"
                binding.pGreen.setText(R.string.pLow)
                binding.pYellow.setText(R.string.pMedium)
                binding.pRed.setText(R.string.pHigh)
            }
            "2" -> {
                priority = "2"
                binding.pYellow.setText(R.string.pLow)
                binding.pGreen.setText(R.string.pMedium)
                binding.pRed.setText(R.string.pHigh)
            }
            "3" -> {
                priority = "3"
                binding.pRed.setText(R.string.pLow)
                binding.pYellow.setText(R.string.pMedium)
                binding.pGreen.setText(R.string.pHigh)

            }
        }
        binding.btnSaveNotes.setOnClickListener {
            updateNotes(it)
        }

        return binding.root

    }

    private fun updateNotes(it: View?) {

        val title = binding.edtTitle.text.toString()
        val notes = binding.edtNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d yyyy ", d.time)

        val data = Notes(
            oldNotes.data.id,
            title = title,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewModel.updateNotes(data)
        Navigation.findNavController(it!!).navigate(R.id.action_detailFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menuDelete) {

            val bottomSheet: BottomSheetDialog =
                BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textviewYes = bottomSheet.findViewById<TextView>(R.id.textView6)
            val textviewNo = bottomSheet.findViewById<TextView>(R.id.textView7)

            textviewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()

                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)

            }
            textviewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }

        return super.onOptionsItemSelected(item)
    }

}