package com.example.blocozin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blocozin.dao.BlocoDAO;
import com.example.blocozin.model.Bloco;

public class CriarBloco extends AppCompatActivity {

    Toolbar toolbar;
    EditText titulo, desc;
    Button cadastrar;
    BlocoDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_bloco);

        db = new BlocoDAO(CriarBloco.this);

        titulo = findViewById(R.id.titulo);
        desc = findViewById(R.id.desc);
        cadastrar = findViewById(R.id.cadastrar_bloco);
        toolbar = findViewById(R.id.toolbar);

        cadastrar.setOnClickListener(view -> {
            if(titulo.getText().length() <= 0) {
                Toast.makeText(CriarBloco.this, "O titulo não pode estar em branco!", Toast.LENGTH_SHORT).show();
            }

            if(desc.getText().length() <= 0) {
                Toast.makeText(CriarBloco.this, "A descrição não pode estar em branco!", Toast.LENGTH_SHORT).show();
            }

            if(titulo.getText().length() > 0 && desc.getText().length() > 0) {
                Bloco bloco = new Bloco(titulo.getText().toString(), desc.getText().toString());

                db.onInsert(bloco);

                Intent in = new Intent(CriarBloco.this, MainActivity.class);
                startActivity(in);
            }
        });

        toolbar.setOnClickListener(view -> {
            Intent in = new Intent(CriarBloco.this, MainActivity.class);
            startActivity(in);
        });
    }
}