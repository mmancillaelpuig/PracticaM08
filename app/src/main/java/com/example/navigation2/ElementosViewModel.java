package com.example.navigation2;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ElementosViewModel extends AndroidViewModel {

    private final ElementosRepositorio elementosRepositorio;

    public ElementosViewModel(@NonNull Application application) {
        super(application);
        elementosRepositorio = new ElementosRepositorio(application);
    }

    public LiveData<List<Elemento>> obtenerTodos() {
        return elementosRepositorio.obtenerTodos();
    }

    public void insertar(Elemento elemento) {
        elementosRepositorio.insertar(elemento);
    }


    public void borrarElemento(int id) {
        elementosRepositorio.borrarElemento(id);
    }

    public void actualizarElemento(Elemento elemento) {
        elementosRepositorio.actualizarElemento(elemento);
    }

}
