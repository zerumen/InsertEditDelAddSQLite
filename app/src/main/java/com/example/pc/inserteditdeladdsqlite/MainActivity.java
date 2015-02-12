package com.example.pc.inserteditdeladdsqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    EditText codigo;
    EditText nombre;
    Button insert;
    Button update;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        codigo=(EditText)findViewById(R.id.editText);
        nombre=(EditText)findViewById(R.id.editText2);
        insert=(Button)findViewById(R.id.button);
        update=(Button)findViewById(R.id.button2);
        delete=(Button)findViewById(R.id.button3);

        SQLiteConnection sqlite=new SQLiteConnection(this,"dbUsuarios",null,1);
        final SQLiteDatabase db=sqlite.getWritableDatabase();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cod=codigo.getText().toString();
                String nom=nombre.getText().toString();
                ContentValues registro=new ContentValues();
                registro.put("codigo",cod);
                registro.put("nombre",nom);
                db.insert("usuarios",null,registro);
                Toast.makeText(MainActivity.this,"Datos Insertado!",Toast.LENGTH_LONG).show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cod=codigo.getText().toString();
                String nom=nombre.getText().toString();
                if(cod!=null||cod!=""){
                    ContentValues registro=new ContentValues();
                    registro.put("nombre",nom);
                    String[]args=new String[]{cod};
                    db.update("usuarios",registro,"codigo=?",args);
                }
                Toast.makeText(MainActivity.this,"Dato Actualizado!",Toast.LENGTH_LONG).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cod=codigo.getText().toString();
                String nom=nombre.getText().toString();
                if(cod!=null||cod!=""){
                    String[]args=new String[]{cod};
                    db.delete("usuarios","codigo=?",args);
                }
                if(nom!=null||nom!=""){
                    String[]args=new String[]{nom};
                    db.delete("usuarios","nombre=?",args);
                }
                Toast.makeText(MainActivity.this,"Dato eliminado!",Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
