package com.example.blocozin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blocozin.model.Bloco;

import java.util.ArrayList;
import java.util.List;

public class BlocoAdapter extends RecyclerView.Adapter<BlocoAdapter.BlocoViewHolder>{

    private final List<Bloco> blocos;

    public BlocoAdapter(ArrayList<Bloco> blocos) {
        this.blocos = blocos;
    }

    @NonNull
    @Override
    public BlocoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //pega a estilização do card
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bloco_item, parent, false);

        return new BlocoViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull BlocoViewHolder holder, int position) {
        Bloco bloco = blocos.get(position);

        holder.bind(bloco);
    }

    @Override
    public int getItemCount() {
        return blocos.size();
    }

    class BlocoViewHolder extends RecyclerView.ViewHolder {
        TextView bloco_titulo;
        TextView bloco_desc;

        //atribui a estilização do card para o recyclerView
        public BlocoViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            bloco_titulo = itemView.findViewById(R.id.bloco_titulo);
            bloco_desc = itemView.findViewById(R.id.bloco_desc);

            itemView.setOnClickListener(view -> {
                Intent in = new Intent(context, VizualizarBloco.class);
                Bundle bundle = new Bundle();
                bundle.putString("titulo", bloco_titulo.getText().toString());
                bundle.putString("desc", bloco_desc.getText().toString());
                in.putExtras(bundle);
                context.startActivity(in);
            });
        }

        public void bind(Bloco bloco) {
            bloco_titulo.setText(bloco.getTitle());
            bloco_desc.setText(bloco.getDesc());
        }
    }
}
