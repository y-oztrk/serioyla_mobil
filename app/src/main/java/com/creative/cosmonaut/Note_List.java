package com.creative.cosmonaut;
//main2
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Note_List extends AppCompatActivity {
    LinearLayout notes_layout;
    List<Note>note_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Notlarım");
        setSupportActionBar(toolbar);

        notes_layout=(LinearLayout)findViewById(R.id.notesLayout);
        getAllNote();
        filNotes();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(),NotPadactvy.class);
                startActivity(i);
            }
        });
    }

          public void getAllNote(){
              note_list =new ArrayList<Note>();
              String fileName = null;
              String content=null;
              File directory = getFilesDir();
              File[] files = directory.listFiles();
              for (int i = 1 ; i<=files.length;i++){

                  fileName = "note" + i + ".txt";
                  content=getNote(fileName);
                  Note n=new Note();
                  if (content.toString().length()<=5)
                      n.Title=content;

                  else
                      n.Title=content.substring(0,25)+".....";
                  n.Content=content.toString();
                  n.FileName=fileName;
                  note_list.add(n);
              }
          }

//not çağırma
public String getNote(String fileName){
        String content=null;

        try {
            InputStream st=openFileInput("fileName");
            if (st!=null){
                InputStreamReader sr=new InputStreamReader(st);
                BufferedReader reader=new BufferedReader(sr);
                StringBuilder buf = new StringBuilder();
                String str=null;
                while ((str=reader.readLine()) !=null){
                    buf.append(str + "\n");
                    content=buf.toString();
                }
            }
        }
        catch (Throwable t){
            Toast.makeText(this,"hata : "+ t.toString(),Toast.LENGTH_SHORT).show();
 }
        return content;
}
View.OnClickListener txClick=new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        TextView tx=(TextView)v;
        Note n=new Note();
        n.FileName=tx.getTag().toString();
        for (Note note:note_list) {
            if (note.FileName==n.FileName)
            {
            n.Content=note.Content;
            }
            
        }
Bundle bnd=new Bundle();
        bnd.putSerializable("Note",n);
        Intent i=new Intent(getBaseContext(),NotPadactvy.class);
        i.putExtras(bnd);
        startActivity(i);
    }
};
//layout doldurma
    public void filNotes()
    {
        if (note_list!=null){
            for (Note n:note_list){
                TextView tx=new TextView(this);
                //tx.setPadding(30, 10,30,10);
                tx.setText(n.Title);
                tx.setTag(n.FileName);
                tx.setBackgroundResource(R.drawable.textview_bottom_border);
                tx.setOnClickListener(txClick);
                tx.setTextSize(20);
                notes_layout.addView(tx);
            }
        }
    }
}