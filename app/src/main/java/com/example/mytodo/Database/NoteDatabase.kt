package com.example.mytodo.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mytodo.Models.Note
import com.example.mytodo.utilities.DATABASE_NAME

// This is Singleton Pattern, only single object of this class can be created
// If already object is created it will return the reference of the already created object.

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){

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

                instance

            }

        }

    }

}