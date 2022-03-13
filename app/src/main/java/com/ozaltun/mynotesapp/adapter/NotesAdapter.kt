package com.ozaltun.mynotesapp.adapter

import android.animation.LayoutTransition
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ozaltun.mynotesapp.R
import com.ozaltun.mynotesapp.databinding.FragmentCreateBinding
import com.ozaltun.mynotesapp.databinding.ItemNotesBinding
import com.ozaltun.mynotesapp.fragments.HomeFragmentDirections
import com.ozaltun.mynotesapp.model.Notes

class NotesAdapter(val mContext: Context, var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged()

    }

    class NotesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.textView2.text = data.title.toString()
        holder.binding.textView3.text = data.notes.toString()
        holder.binding.textView4.text = data.date.toString()
        when (data.priority) {
            "1" ->{
                holder.binding.view3.setBackgroundResource(R.drawable.green_dot)
            }
            "2" ->{
                holder.binding.view3.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3" ->{
                holder.binding.view3.setBackgroundResource(R.drawable.red_dot)
            }
        }
        holder.binding.root.setOnClickListener {
            TransitionManager.beginDelayedTransition(holder.binding.layout, AutoTransition())
            holder.binding.layout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
            if(holder.binding.textView3.visibility == View.GONE){
                holder.binding.textView3.visibility = View.VISIBLE

            }else{
                holder.binding.textView3.visibility = View.GONE
            }
            holder.binding.root.setOnLongClickListener {

                val ad = MaterialAlertDialogBuilder(mContext)
                ad.setTitle(data.title)
                ad.setMessage(data.notes)
                ad.setPositiveButton(R.string.edit) { dialogInterface, i->
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
                    Navigation.findNavController(it).navigate(action)
                }
                ad.setNegativeButton(R.string.close) { dialogInterface, i->
                }
                ad.create().show()


                return@setOnLongClickListener true
            }




        }

    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}