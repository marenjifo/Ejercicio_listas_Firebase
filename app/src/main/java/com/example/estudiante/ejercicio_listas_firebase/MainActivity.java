package com.example.estudiante.ejercicio_listas_firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {

    ListView lv_lista;
    FirebaseDatabase db;

    //Paso 1: Crear el adaptador
    FirebaseListAdapter<Usuario> adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=FirebaseDatabase.getInstance();
        //Paso 2: Referenciar el listview
        lv_lista=findViewById(R.id.lv_lista);

        //Paso 3: Hacer Query a mostrar
        Query ref = db.getReference().child("usuarios");

        //Paso 4: Firebaselist Options
        FirebaseListOptions<Usuario> options = new FirebaseListOptions.Builder<Usuario>().setLayout(R.layout.renglon).setQuery(ref,Usuario.class).build();

        //Paso 5: Construir el adaptador
        adapter=new FirebaseListAdapter<Usuario>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Usuario model, int position) {

                TextView tv_correo =v.findViewById(R.id.tv_correo);
                TextView tv_nombre=v.findViewById(R.id.tv_nombre);
                TextView tv_uid=v.findViewById(R.id.tv_uid);

                tv_correo.setText(model.correo);
                tv_nombre.setText(model.nombre);
                tv_uid.setText(model.uid);


            }
        };

        //Añadir el adaptador a la lista

        lv_lista.setAdapter(adapter);



    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
