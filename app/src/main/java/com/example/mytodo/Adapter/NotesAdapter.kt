package com.example.mytodo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.Models.Note
import com.example.mytodo.R
import kotlin.random.Random

class NotesAdapter(private val context : Context, val listner: NotesClickListner) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val NotesList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.NoteViewHolder {

        return NoteViewHolder(

            LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        )

    }

    override fun onBindViewHolder(holder: NotesAdapter.NoteViewHolder, position: Int) {

        val currentNote = NotesList[position]

        holder.title.text = currentNote.title
        holder.title.isSelected = true
        holder.note.text = currentNote.note
        holder.date.text = currentNote.date
        holder.date.isSelected = true

        // Setting the Background Color of the Card
        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))

        holder.notes_layout.setOnClickListener{
            listner.onItemClicked(NotesList[holder.adapterPosition])
        }

        holder.notes_layout.setOnLongClickListener {

            listner.onLongItemClicked(NotesList[holder.adapterPosition], holder.notes_layout)
            true

        }

    }

    override fun getItemCount(): Int {
        return NotesList.size
    }

    fun updateList(newList:List<Note>){

        fullList.clear()
        fullList.addAll(newList)

        NotesList.clear()
        NotesList.addAll(fullList)

        notifyDataSetChanged()

    }

    fun filterList(search : String){

        NotesList.clear()

        for (item in fullList){

            if(item.title?.lowercase()?.contains(search.lowercase())==true ||
                    item.note?.lowercase()?.contains(search.lowercase())==true){
                NotesList.add(item)
            }

        }

        notifyDataSetChanged()

    }

    fun randomColor() : Int{

        var list = ArrayList<Int>()

        list.add(R.color.random_color_1)
        list.add(R.color.random_color_2)
        list.add(R.color.random_color_3)
        list.add(R.color.random_color_4)
        list.add(R.color.random_color_5)
        list.add(R.color.random_color_6)
        list.add(R.color.random_color_7)
        list.add(R.color.random_color_8)
        list.add(R.color.random_color_9)
        list.add(R.color.random_color_10)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)

        return list[randomIndex]

    }

    inner class NoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val notes_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val note = itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_date)

    }

    interface NotesClickListner{

        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note, cardView: CardView)

    }


}