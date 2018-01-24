package com.kanchan.honeydolist.honeydolist;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText entertext;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entertext = (EditText) findViewById(R.id.entertextID);
        save = (Button) findViewById(R.id.saveID);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = entertext.getText().toString();
                if (message!= "") {
                    writeToFile(message);
                }

            }
        });

        try {
            if (readFromFile() != null) {
              entertext.setText(readFromFile());
            }
        }catch (IOException e) {e.printStackTrace();}
    }

    private void writeToFile(String message){

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("todo.txt", Context.MODE_PRIVATE));
             outputStreamWriter.append(message);
             outputStreamWriter.close();
        }catch (FileNotFoundException  e){
         e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String readFromFile() throws IOException {
         String result ;
        InputStream inputStream = openFileInput("todo.txt");
        result = "";
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String tempread = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((tempread = bufferedReader.readLine()) != null) {
                stringBuilder.append(tempread);
            }

            inputStream.close();
            result = stringBuilder.toString();
        }
        return result;
    }

}
