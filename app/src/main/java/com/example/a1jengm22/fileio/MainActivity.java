package com.example.a1jengm22.fileio;

import android.app.AlertDialog;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {


    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save)
        {
            // react to the menu item being selected...
            try {
                PrintWriter pw =
                        new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/data.txt"));

                EditText text = (EditText) findViewById(R.id.text);

                pw.println(text.getText().toString());

                pw.close(); // close the file to ensure data is flushed to file
            } catch (IOException e) {
                new AlertDialog.Builder(this).setMessage("ERROR: " + e).
                        setPositiveButton("OK", null).show();
            }

            return true;
        }
        else if (item.getItemId() == R.id.load) {
            // react to the menu item being selected
            EditText eText = (EditText) findViewById(R.id.text);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getAbsolutePath() + "/data.txt"));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    eText.setText(eText.getText()+ line);
                }
            } catch (IOException e) {
                System.out.println("ERROR: " + e);

            }
        }
            return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
