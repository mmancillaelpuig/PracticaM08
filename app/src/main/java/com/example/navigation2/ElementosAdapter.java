package com.example.navigation2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ElementosAdapter extends RecyclerView.Adapter<ElementosAdapter.ElementoViewHolder> {

    private List<Elemento> elementos;

    public ElementosAdapter(List<Elemento> elementos) {
        this.elementos = elementos;
    }

    public void setElementos(List<Elemento> nuevosElementos) {
        this.elementos = nuevosElementos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ElementoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_elemento, parent, false);
        return new ElementoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElementoViewHolder holder, int position) {
        Elemento elemento = elementos.get(position);
        holder.diaTextView.setText("Día: " + elemento.getDia());
        holder.tituloTextView.setText("Tarea: " + elemento.getTitulo());
        holder.descripcionTextView.setText("Descripción: " + elemento.getDescripcion());

        holder.itemView.setOnClickListener(v -> {
            if (onElementoClickListener != null) {
                onElementoClickListener.onEditarElemento(elemento);
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (onElementoClickListener != null) {
                onElementoClickListener.onEliminarElemento(elemento);
            }
            return true;
        });
    }

    public interface OnElementoClickListener {
        void onEditarElemento(Elemento elemento);
        void onEliminarElemento(Elemento elemento);
    }

    private OnElementoClickListener onElementoClickListener;

    public void setOnElementoClickListener(OnElementoClickListener listener) {
        this.onElementoClickListener = listener;
    }


    @Override
    public int getItemCount() {
        return elementos.size();
    }

    static class ElementoViewHolder extends RecyclerView.ViewHolder {
        TextView diaTextView;
        TextView tituloTextView;
        TextView descripcionTextView;

        public ElementoViewHolder(@NonNull View itemView) {
            super(itemView);
            diaTextView = itemView.findViewById(R.id.tv_dia);
            tituloTextView = itemView.findViewById(R.id.tv_titulo);
            descripcionTextView = itemView.findViewById(R.id.tv_descripcion);
        }
    }
}
