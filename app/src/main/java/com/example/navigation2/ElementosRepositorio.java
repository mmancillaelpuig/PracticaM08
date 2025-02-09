package com.example.navigation2;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ElementosRepositorio {
    private final ElementoDao elementoDao;
    private final LiveData<List<Elemento>> todosLosElementos;
    private final ExecutorService executorService;

    public ElementosRepositorio(Application application) {
        ElementosDatabase database = ElementosDatabase.obtenerInstancia(application);
        elementoDao = database.elementoDao();
        todosLosElementos = elementoDao.obtenerTodos();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Elemento>> obtenerTodos() {
        return todosLosElementos;
    }

    public void insertar(Elemento elemento) {
        executorService.execute(() -> elementoDao.insertar(elemento));
    }

    public void borrarElemento(int id) {
        executorService.execute(() -> elementoDao.borrarElemento(id));
    }

    public void actualizarElemento(Elemento elemento) {
        executorService.execute(() -> elementoDao.actualizarElemento(
                elemento.getId(),
                elemento.getTitulo(),
                elemento.getDescripcion(),
                elemento.getDia()
        ));
    }

}
