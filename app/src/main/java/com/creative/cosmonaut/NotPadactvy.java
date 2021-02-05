package com.creative.cosmonaut;
//main1
import android.content.Intent;
import android.os.Bundle;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.OutputStreamWriter;

public class NotPadactvy extends AppCompatActivity {
EditText etNote;
String getFilename=null;
    Bundle extrass=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_padactvy);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_icon);
        toolbar.setTitle("Yeni Not");
        setSupportActionBar(toolbar);
        etNote=(EditText)findViewById(R.id.etNot);


         extrass=getIntent().getExtras();
        if(extrass!=null){
            toolbar.setTitle("Eski Not");
            Note n=(Note)getIntent().getSerializableExtra("Not");
            getFilename=n.FileName;
            etNote.setText(n.Content);
        }





        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),Note_List.class);
                startActivity(i);
            }

        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveNote();
            }
        });
    }
    private void SaveNote()
    {
        try {
            File directory = getFilesDir();
           int filenumber=directory.listFiles().length+1;
            OutputStreamWriter out;
    if(getFilename!=null){
   out= new OutputStreamWriter(openFileOutput(getFilename, 0));

                          }

     else{
    out= new OutputStreamWriter(openFileOutput("Note" +filenumber+".txt", 0));
          }

            out.write(etNote.getText().toString());
            out.close();
if(extrass!=null){
    Toast.makeText(this,"Notunuz Güncellendi !",Toast. LENGTH_SHORT).show();
}
else{
    Toast.makeText(this,"Notunuz Kaydoldu !",Toast. LENGTH_SHORT).show();
}

        }catch (Exception e)
        {
Toast.makeText(this,"Kayıt Sırasında Hata Oluştu"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}