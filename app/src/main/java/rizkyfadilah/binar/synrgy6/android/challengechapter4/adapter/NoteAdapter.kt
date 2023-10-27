package rizkyfadilah.binar.synrgy6.android.challengechapter4.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import rizkyfadilah.binar.synrgy6.android.challengechapter4.database.note_database.NoteEntity
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.NotesItemBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.NotesViemodel


class NoteAdapter(
    val context: Context,
    val deleteListener: DeleteListener,
    val editListener: EditListener,
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val allNotes = ArrayList<NoteEntity>()

    class NoteViewHolder(val binding: NotesItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NotesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val note = allNotes[position]

        holder.binding.tvTittle.setText(note.title)
        holder.binding.tvDescription.setText(note.description)

        holder.binding.ivEdit.setOnClickListener{
            editListener.editItem(note)
        }

        holder.binding.ivDelete.setOnClickListener{
            deleteListener.deleteItem(note)
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<NoteEntity>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface EditListener {
    fun editItem(note: NoteEntity)
}

interface DeleteListener {
    fun deleteItem(note: NoteEntity)
}
