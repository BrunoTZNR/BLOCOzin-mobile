package com.example.blocozin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blocozin.dao.BlocoDAO;
import com.example.blocozin.model.Bloco;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BlocoAdapter blocoAdapter;
    List<Bloco> blocos = new ArrayList<>();
    Button cadastrarBloco;
    RecyclerView listaBlocos;
    BlocoDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*blocos.add(new Bloco(1, "titulo 1", "desc foda 1"));
        blocos.add(new Bloco(2, "titulo 2", "desc foda 2"));
        blocos.add(new Bloco(3, "titulo 3", "desc foda 3"));
        blocos.add(new Bloco(1, "titulo 1", "desc foda 1"));
        blocos.add(new Bloco(2, "titulo 2", "desc foda 2"));
        blocos.add(new Bloco(3, "titulo 3", "desc foda 3"));
        blocos.add(new Bloco(1, "titulo 1", "desc foda 1"));
        blocos.add(new Bloco(2, "titulo 2", "desc foda 2"));
        blocos.add(new Bloco(3, "titulo 3", "desc foda 3"));
        blocos.add(new Bloco(1, "titulo 1", "desc foda 1"));
        blocos.add(new Bloco(2, "titulo 2", "desc foda 2"));
        blocos.add(new Bloco(3, "titulo 3", "desc foda 3"));
        blocos.add(new Bloco(1, "titulo 1", "desc foda 1"));
        blocos.add(new Bloco(2, "titulo 2", "desc foda 2"));
        blocos.add(new Bloco(3, "titulo 3", "desc foda 3"));
        blocos.add(new Bloco(1, "titulo 1", "desc foda 1"));
        blocos.add(new Bloco(2, "titulo 2", "desc foda 2"));
        blocos.add(new Bloco(3, "titulo 3", "desc foda 3"));
        blocos.add(new Bloco(1, "titulo 1", "desc foda 1"));
        blocos.add(new Bloco(2, "titulo 2", "desc foda 2"));
        blocos.add(new Bloco(3, "titulo 3", "desc foda 3"));
        blocos.add(new Bloco(1, "titulo 1", "desc foda 1"));
        blocos.add(new Bloco(2, "titulo 2", "desc foda 2"));
        blocos.add(new Bloco(3, "titulo 3", "desc foda 3"));*/

        db = new BlocoDAO(MainActivity.this);

        listaBlocos = findViewById(R.id.lista_blocos);
        cadastrarBloco = findViewById(R.id.cadastrar_bloco);

        blocos = db.onSelectAll();

        blocoAdapter = new BlocoAdapter(new ArrayList<>(blocos));
        listaBlocos.setAdapter(blocoAdapter);

        cadastrarBloco.setOnClickListener(view -> {
            Intent in = new Intent(MainActivity.this, CriarBloco.class);
            startActivity(in);
        });
    }
}