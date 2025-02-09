package com.example.navigation2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ElementoDao {
    @Insert
    void insertar(Elemento elemento);

    @Query("SELECT * FROM elementos ORDER BY dia ASC, id DESC")
    LiveData<List<Elemento>> obtenerTodos();

    @Query("DELETE FROM elementos WHERE id = :id")
    void borrarElemento(int id);

    @Query("UPDATE elementos SET titulo = :titulo, descripcion = :descripcion, dia = :dia WHERE id = :id")
    void actualizarElemento(int id, String titulo, String descripcion, String dia);

}
