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

public class EditarBloco extends AppCompatActivity {

    Toolbar toolbar;
    EditText titulo, desc;
    Button salvar;
    BlocoDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_bloco);

        Bundle bundle = getIntent().getExtras();

        db = new BlocoDAO(EditarBloco.this);

        titulo = findViewById(R.id.titulo);
        desc = findViewById(R.id.desc);
        salvar = findViewById(R.id.salvar_bloco);
        toolbar = findViewById(R.id.toolbar);

        titulo.setText(bundle.getString("titulo"));
        desc.setText(bundle.getString("desc"));

        salvar.setOnClickListener(view -> {
            if(titulo.getText().length() <= 0) {
                Toast.makeText(EditarBloco.this, "O titulo não pode estar em branco!", Toast.LENGTH_SHORT).show();
            }

            if(desc.getText().length() <= 0) {
                Toast.makeText(EditarBloco.this, "A descrição não pode estar em branco!", Toast.LENGTH_SHORT).show();
            }

            if(titulo.getText().length() > 0 && desc.getText().length() > 0) {

                Bloco bloco = new Bloco(db.onSelectOne(bundle.getString("titulo")).getId(),
                        titulo.getText().toString(), desc.getText().toString());

                db.onUpdate(bloco);

                Intent in = new Intent(EditarBloco.this, MainActivity.class);
                startActivity(in);
            }
        });

        toolbar.setOnClickListener(view -> {
            Intent in = new Intent(EditarBloco.this, MainActivity.class);
            startActivity(in);
        });
    }
}