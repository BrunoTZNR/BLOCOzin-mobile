package com.example.blocozin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.blocozin.dao.BlocoDAO;

public class VizualizarBloco extends AppCompatActivity {

    Toolbar toolbar;
    TextView titulo, desc;
    Button editar, deletar;
    BlocoDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizar_bloco);

        Bundle bundle = getIntent().getExtras();

        db = new BlocoDAO(VizualizarBloco.this);

        titulo = findViewById(R.id.titulo);
        desc = findViewById(R.id.desc);
        editar = findViewById(R.id.editar_bloco);
        deletar = findViewById(R.id.deletar_bloco);
        toolbar = findViewById(R.id.toolbar);

        titulo.setText(bundle.getString("titulo"));
        desc.setText(bundle.getString("desc"));

        editar.setOnClickListener(view -> {
            Intent in = new Intent(VizualizarBloco.this, EditarBloco.class);
            Bundle bundle0 = new Bundle();
            bundle0.putString("titulo", bundle.getString("titulo"));
            bundle0.putString("desc", bundle.getString("desc"));
            in.putExtras(bundle0);
            startActivity(in);
        });

        deletar.setOnClickListener(view -> exibirDialog());

        toolbar.setOnClickListener(view -> {
            Intent in = new Intent(VizualizarBloco.this, MainActivity.class);
            startActivity(in);
        });
    }

    private void exibirDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Deseja realmente excluir esse bloco?");

        builder.setPositiveButton("SIM", (dialog, id) -> {
            db.onDelete(db.onSelectOne(titulo.getText().toString()).getId());

            Intent in = new Intent(VizualizarBloco.this, MainActivity.class);
            startActivity(in);
        });

        builder.setNegativeButton("NÃO", (dialog, id) -> { });

        AlertDialog dialog = builder.create();

        dialog.show();
    }
}