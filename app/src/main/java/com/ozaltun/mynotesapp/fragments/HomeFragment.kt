package com.ozaltun.mynotesapp.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ozaltun.mynotesapp.R
import com.ozaltun.mynotesapp.adapter.NotesAdapter
import com.ozaltun.mynotesapp.databinding.FragmentHomeBinding
import com.ozaltun.mynotesapp.model.Notes
import com.ozaltun.mynotesapp.viewmodel.NotesViewModel

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
        binding.rcvAllNotes.setHasFixedSize(true)
        setHasOptionsMenu(true)

        //getAllNotes
        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            oldMyNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(), notesList)
            binding.rcvAllNotes.adapter = adapter
        }
        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                oldMyNotes = notesList as ArrayList<Notes>
                adapter =  NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter

            }
        }
        binding.filterMedium.setOnClickListener {
            viewModel.getNormalNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                oldMyNotes = notesList as ArrayList<Notes>
                adapter =  NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter

            }
        }
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                oldMyNotes = notesList as ArrayList<Notes>
                adapter =  NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter

            }
        }
        binding.allNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }


        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                NotesFiltering(p0)
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }
    private fun NotesFiltering(p0: String?) {
        val newFilteredList = arrayListOf<Notes>()
        for (i in oldMyNotes) {
            if (i.title!!.contains(p0!!) || i.notes!!.contains(p0!!)){
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }

}