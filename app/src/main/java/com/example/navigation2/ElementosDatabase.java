package com.example.navigation2;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Elemento.class}, version = 1)
public abstract class ElementosDatabase extends RoomDatabase {

    private static volatile ElementosDatabase instancia;

    public abstract ElementoDao elementoDao();

    public static synchronized ElementosDatabase obtenerInstancia(Context context) {
        if (instancia == null) {
            instancia = Room.databaseBuilder(context.getApplicationContext(),
                            ElementosDatabase.class, "elementos_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instancia;
    }
}
