package com.example.navigation2;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BottomFragment1 extends Fragment {
    private ElementosAdapter elementosAdapter;
    private ElementosViewModel elementosViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom1, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        elementosAdapter = new ElementosAdapter(new ArrayList<>());
        recyclerView.setAdapter(elementosAdapter);

        elementosViewModel = new ViewModelProvider(this).get(ElementosViewModel.class);

        elementosViewModel.obtenerTodos().observe(getViewLifecycleOwner(), nuevosElementos -> {
            elementosAdapter.setElementos(nuevosElementos);
        });

        elementosAdapter.setOnElementoClickListener(new ElementosAdapter.OnElementoClickListener() {
            @Override
            public void onEditarElemento(Elemento elemento) {
                mostrarDialogoEdicion(elemento);
            }

            @Override
            public void onEliminarElemento(Elemento elemento) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Eliminar tarea")
                        .setMessage("¿Estás seguro de que quieres eliminar esta tarea?")
                        .setPositiveButton("Sí", (dialog, which) -> elementosViewModel.borrarElemento(elemento.getId()))
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        return view;
    }


    private void mostrarDialogoEdicion(Elemento elemento) {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_editar_elemento, null);
        EditText etTitulo = dialogView.findViewById(R.id.et_titulo);
        EditText etDescripcion = dialogView.findViewById(R.id.et_descripcion);
        Spinner spinnerDia = dialogView.findViewById(R.id.spinnerDia);

        etTitulo.setText(elemento.getTitulo());
        etDescripcion.setText(elemento.getDescripcion());

        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, dias);
        spinnerDia.setAdapter(adapter);
        spinnerDia.setSelection(Elemento.obtenerIndiceDia(elemento.getDia()) - 1);

        new AlertDialog.Builder(getContext())
                .setTitle("Editar tarea")
                .setView(dialogView)
                .setPositiveButton("Guardar", (dialog, which) -> {
                    String nuevoTitulo = etTitulo.getText().toString();
                    String nuevaDescripcion = etDescripcion.getText().toString();
                    String nuevoDia = spinnerDia.getSelectedItem().toString();

                    if (!nuevoTitulo.isEmpty() && !nuevaDescripcion.isEmpty()) {
                        elemento.setTitulo(nuevoTitulo);
                        elemento.setDescripcion(nuevaDescripcion);
                        elemento.setDia(nuevoDia);
                        elementosViewModel.actualizarElemento(elemento);
                    } else {
                        Toast.makeText(getContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }




}
