package com.example.navigation2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.*;

@Entity(tableName = "elementos")
public class Elemento {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String titulo;
    private String descripcion;
    private String dia;

    public Elemento(String titulo, String descripcion, String dia) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.dia = dia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDia() {
        return dia;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public static int obtenerIndiceDia(String dia) {
        switch (dia.toLowerCase()) {
            case "lunes": return 1;
            case "martes": return 2;
            case "miércoles": return 3;
            case "jueves": return 4;
            case "viernes": return 5;
            case "sábado": return 6;
            case "domingo": return 7;
            default: return 0; // Caso por defecto en caso de un error
        }
    }
}
