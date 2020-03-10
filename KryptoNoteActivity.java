package ca.yorku.eecs.kryptonote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class KryptoNoteActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kryptonote_layout);
    }

    public void onEncrypt(View v){
        try{
            EditText noteView = (EditText) findViewById(R.id.data); //search for text in 'data' id
            String note = noteView.getText().toString();            //get the string from the note textbox
            EditText keyView = (EditText) findViewById(R.id.key);   //search for text in 'key' id
            String key = keyView.getText().toString();              //get the string from the key textbox
            Cipher cp = new Cipher(key);                            //create an object using the key from the user
            ((EditText) findViewById(R.id.data)).setText(cp.encrypt(note));    //display the encrypt message to the note
                                                                              //overwriting the note
        }catch(Exception e){
            Toast label = Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT);
            label.setGravity(Gravity.CENTER,0,0);
            label.show();
        }


    }


    public void onDecrypt(View v){
        try{
            EditText noteView = (EditText) findViewById(R.id.data); //search for text in 'data' id
            String note = noteView.getText().toString();            //get the string from the note textbox
            EditText keyView = (EditText) findViewById(R.id.key);   //search for text in 'key' id
            String key = keyView.getText().toString();              //get the string from the key textbox
            Cipher cp = new Cipher(key);                            //create an object using the key from the user
            ((EditText) findViewById(R.id.data)).setText(cp.decrypt(note));


        }catch (Exception e){
            Toast label = Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT);
            label.setGravity(Gravity.CENTER,0,0);
            label.show();
        }

    }

    public void onSave(View v){
        try{
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();  //getFilesDir is within File API
            File file = new File(dir,name);
            FileWriter fw = new FileWriter(file);   //write into the created-file
            fw.write(((EditText) findViewById(R.id.data)).getText().toString());
            fw.close();

            Toast toast = Toast.makeText(this,"Note Saved.", Toast.LENGTH_LONG);
            toast.show();

        }catch(Exception e){
            Toast toast = Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }



    }

    public void onLoad(View v){
        try{
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir,name);
            FileReader fr = new FileReader(file);

            String show = "";
            for(int c = fr.read(); c != -1; c = fr.read()){
                show += (char) c;
            }
            fr.close();
            ((EditText) findViewById(R.id.data)).setText(show);
        }catch (Exception e){
            Toast toast = Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG);
            toast.show();

        }


    }


}
