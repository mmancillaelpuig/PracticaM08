package com.example.navigation2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class BottomFragment2 extends Fragment {

    private EditText etTitulo, etDescripcion;
    private Spinner spinnerDia;
    private Button btnGuardar;
    private ElementosViewModel elementosViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom2, container, false);

        etTitulo = view.findViewById(R.id.et_titulo);
        etDescripcion = view.findViewById(R.id.et_descripcion);
        spinnerDia = view.findViewById(R.id.spinnerDia);
        btnGuardar = view.findViewById(R.id.btn_guardar);


        elementosViewModel = new ViewModelProvider(this).get(ElementosViewModel.class);


        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, dias);
        spinnerDia.setAdapter(adapter);

        btnGuardar.setOnClickListener(v -> guardarTarea());

        return view;
    }

    private void guardarTarea() {
        String titulo = etTitulo.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String dia = spinnerDia.getSelectedItem().toString();

        if (titulo.isEmpty() || descripcion.isEmpty() || dia.isEmpty()) {
            Toast.makeText(getContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
        } else {
            Elemento nuevaTarea = new Elemento(titulo, descripcion, dia);
            elementosViewModel.insertar(nuevaTarea);

            etTitulo.setText("");
            etDescripcion.setText("");
            spinnerDia.setSelection(0);

            Toast.makeText(getContext(), "Tarea guardada", Toast.LENGTH_SHORT).show();
        }
    }
}
