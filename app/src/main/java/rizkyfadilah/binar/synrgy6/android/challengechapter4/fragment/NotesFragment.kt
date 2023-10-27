package rizkyfadilah.binar.synrgy6.android.challengechapter4.fragment

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import rizkyfadilah.binar.synrgy6.android.challengechapter4.R
import rizkyfadilah.binar.synrgy6.android.challengechapter4.adapter.DeleteListener
import rizkyfadilah.binar.synrgy6.android.challengechapter4.adapter.EditListener
import rizkyfadilah.binar.synrgy6.android.challengechapter4.adapter.NoteAdapter
import rizkyfadilah.binar.synrgy6.android.challengechapter4.database.note_database.NoteEntity
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.AddDialogBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.DeleteDialogBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.FragmentNotesBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.pref.SharedPref
import rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.NotesFactory
import rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.NotesViemodel

class NotesFragment : Fragment(), DeleteListener, EditListener {
    private lateinit var binding: FragmentNotesBinding
    lateinit var viewModel: NotesViemodel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO 6 : Get username from sharedpref
        val sharedPref = SharedPref(requireContext())

        // TODO 6 : Set textview to "Welcome, username"
        binding.tvWelcomeUser.text = "Welcome, ${sharedPref.getUsername()}!"

        // TODO 7 : Create function for logout
        binding.tvLogout.setOnClickListener {
            sharedPref.clearData()
            findNavController().navigate(R.id.action_notesFragment_to_loginFragment)
        }

        // TODO 8 : Create recyclerview
        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())

        // TODO 9 : Create adapter for recyclerview
        val adapter = NoteAdapter(requireContext(), this, this)
        binding.rvNotes.adapter = adapter


        // TODO 10 : Create viewmodel
        val application = requireNotNull(this.activity).application
        val factory = NotesFactory(application)
        // TODO 11 : Initialize viewmodel
        viewModel = ViewModelProvider(this, factory).get(NotesViemodel::class.java)

        viewModel.allNote.observe(viewLifecycleOwner, { list ->
            list?.let {
                adapter.updateList(it)
            }
        })
        // dialog
        binding.fabAddNote.setOnClickListener {
            addDialog()
        }
    }

    override fun editItem(note: NoteEntity) {
        editDialog()
    }

    override fun deleteItem(note: NoteEntity) {
        deleteDialog()
    }

    // TODO 12 : Create function for add dialog
    private fun addDialog() {
        val addDialogBinding: AddDialogBinding = AddDialogBinding.inflate(layoutInflater)

        val addDialog = AlertDialog.Builder(requireContext(), 0).create()

        addDialog.apply {
            setView(addDialogBinding.root)
            setCancelable(false)
        }.show()

        addDialogBinding.btnInput.setOnClickListener {
            val title = addDialogBinding.etTittle.text.toString()
            val desc = addDialogBinding.etDescription.text.toString()
            val note = NoteEntity(title = title, description = desc)
            viewModel.insert(note)
            addDialog.dismiss()
        }
    }

    private fun deleteDialog(){
        val deleteDialogBinding: DeleteDialogBinding = DeleteDialogBinding.inflate(layoutInflater)
        val deleteDialog = AlertDialog.Builder(requireContext(), 0).create()

        deleteDialog.apply {
            setView(deleteDialogBinding.root)
            setCancelable(false)
        }.show()

        deleteDialogBinding.btnDelete.setOnClickListener {
            deleteDialog.dismiss()
        }

        deleteDialogBinding.btnCancel.setOnClickListener {
            deleteDialog.dismiss()
        }
    }

    private  fun editDialog(){

    }

}