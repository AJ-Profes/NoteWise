package com.example.mytodo.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.mytodo.Models.Note

// This is Single Time Pattern

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase {

    abstract fun getNoteDao() : NoteDao

    companion object{

        @Volatile
        private var INSTANCE : NoteDatabase? = null

        fun getDatabase(contex : Context) : NoteDatabase{

            return INSTANCE ?: synchronized(this){

                val instance = Room.databaseBuilder(

                    contex.applicationContext,
                    NoteDatabase::class.java,
                    DATABASE_NAME

                ).build()

                INSTANCE = instance

            }

        }

    }

}