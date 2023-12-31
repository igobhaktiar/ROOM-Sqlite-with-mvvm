package rizkyfadilah.binar.synrgy6.android.challengechapter4.fragment

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import rizkyfadilah.binar.synrgy6.android.challengechapter4.adapter.SelectListener
import rizkyfadilah.binar.synrgy6.android.challengechapter4.database.note_database.NoteEntity
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.AddDialogBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.DeleteDialogBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.FragmentNotesBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.pref.SharedPref
import rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.NotesFactory
import rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.NotesViemodel

class NotesFragment : Fragment(),  SelectListener,DeleteListener, EditListener {
    private lateinit var binding: FragmentNotesBinding
    lateinit var viewModel: NotesViemodel
    private var noteId = -1;
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
        binding.tvUser.text = "${sharedPref.getUsername()}!"

        // TODO 7 : Create function for logout
        binding.llLogout.setOnClickListener {
            logout()
        }

        // TODO 8 : Create recyclerview
        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())

        // TODO 9 : Create adapter for recyclerview
        val adapter = NoteAdapter(requireContext(), this, this, this)
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

    // TODO 11 : Create function for edit and delete item
    override fun editItem(note: NoteEntity) {
        val bundle = Bundle()
        bundle.putInt("noteId", note.id)
        editDialog(note)
    }

    override fun deleteItem(note: NoteEntity) {
        deleteDialog(note)
    }

    override fun selectItem(note: NoteEntity) {
        editDialog(note)
    }

    // TODO 12 : Create function for add dialog
    private fun addDialog() {
        // TODO 13 : Create add dialog
        val addDialogBinding: AddDialogBinding = AddDialogBinding.inflate(layoutInflater)
        val addDialog = AlertDialog.Builder(requireContext(), 0).create()

        addDialog.apply {
            setView(addDialogBinding.root)
            setCancelable(false)
        }.show()

        // TODO 14 : Set title and button text to "Add Note"
        addDialogBinding.btnInput.setOnClickListener {
            val title = addDialogBinding.etTittle.text.toString()
            val desc = addDialogBinding.etDescription.text.toString()
            val note = NoteEntity(title = title, description = desc)
            viewModel.insert(note)
            addDialog.dismiss()
        }

        addDialog.setCanceledOnTouchOutside(true)

    }

    // TODO 15 : Create function for delete dialog
    private fun deleteDialog(note: NoteEntity){
        // TODO 16 : Create delete dialog
        val deleteDialogBinding: DeleteDialogBinding = DeleteDialogBinding.inflate(layoutInflater)
        val deleteDialog = AlertDialog.Builder(requireContext(), 0).create()

        // TODO 17 : Set title and button text to "Delete Note"
        deleteDialog.apply {
            setView(deleteDialogBinding.root)
            setCancelable(false)
        }.show()

        // TODO 18 : Create function for delete and cancel button
        deleteDialogBinding.btnDelete.setOnClickListener {
            viewModel.deleteNote(note)
            Toast.makeText(requireContext(), "${note.title} Deleted", Toast.LENGTH_SHORT).show()
            deleteDialog.dismiss()
        }

        deleteDialogBinding.btnCancel.setOnClickListener {
            deleteDialog.dismiss()
        }
        deleteDialog.setCanceledOnTouchOutside(true)
    }

    // TODO 19 : Create function for edit dialog
    private fun editDialog(note: NoteEntity){
        // TODO 20 : Create edit dialog
        val binding: AddDialogBinding = AddDialogBinding.inflate(layoutInflater)
        val editDialog = AlertDialog.Builder(requireContext(), 0).create()
        val bundle = Bundle()

        editDialog.apply {
            setView(binding.root)
            setCancelable(false)
        }.show()

        // TODO 21 : Set title and button text to "Edit Note"
        binding.tvAddNote.text = "Edit Note"
        binding.btnInput.text = "Save"
        binding.etTittle.setText(note.title)
        binding.etDescription.setText(note.description)
        noteId = bundle.getInt("noteId")

        // TODO 22 : Create function for save and cancel button
        binding.btnInput.setOnClickListener {
            val title = binding.etTittle.text.toString()
            val desc = binding.etDescription.text.toString()
            val updateNotes = NoteEntity(note.id, title, desc)
            viewModel.updateNote(updateNotes)
            editDialog.dismiss()
            Toast.makeText(requireContext(), "${note.title} Updated", Toast.LENGTH_SHORT).show()
        }

        editDialog.setCanceledOnTouchOutside(true)

    }

    private fun logout(){
        // TODO 23 : Create logout function
        val binding: DeleteDialogBinding = DeleteDialogBinding.inflate(layoutInflater)
        val logoutDialog = AlertDialog.Builder(requireContext(), 0).create()

        logoutDialog.apply {
            setView(binding.root)
            setCancelable(false)
        }.show()

        binding.tvDeleteNote.text = "Are you sure want to logout?"
        binding.btnDelete.text = "Logout"

        binding.btnDelete.setOnClickListener {
            val sharedPref = SharedPref(requireContext())
            sharedPref.clearData()
            findNavController().navigate(R.id.action_notesFragment_to_loginFragment)
            logoutDialog.dismiss()
        }

        binding.btnCancel.setOnClickListener {
            logoutDialog.dismiss()
        }
    }


}