package com.studio49.mobi_mss_b_ass1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private LinkedList<User> items;
    private UsersAdapter itemsAdapter;
    private ListView lvItems;

    Button addB, clreaB, rejBB;
    EditText breedEd, numberEd;
    TextView countTxt;
    Gson gson;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new Gson();

        items = new LinkedList<>();
        addB = (Button) findViewById(R.id.addB);
        rejBB = (Button) findViewById(R.id.rejB);
        clreaB = (Button) findViewById(R.id.clearB);

        breedEd = (EditText) findViewById(R.id.idEd);
        numberEd = (EditText) findViewById(R.id.nameEd);

        countTxt = (TextView) findViewById(R.id.countTxt);

        for (int i = 0; i < 3; i++) {
            //items.addFirst(new User("1", "Word "));
        }


        ListView listView = (ListView) findViewById(R.id.lvItems);
        itemsAdapter = new UsersAdapter(this, items);
        listView.setAdapter(itemsAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String breedStr = breedEd.getText().toString();
                String numberStr = numberEd.getText().toString();
                if(isValided(breedStr, numberStr)){

                    items.addFirst(new User(breedStr, numberStr));
                    itemsAdapter.notifyDataSetChanged();
                    countTxt.setText("Cows: " + items.size());
                }
            }
        });

        rejBB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breedEd.setText("");
                numberEd.setText("");
            }
        });

        clreaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.clear();
                items = new LinkedList<>();
                itemsAdapter.notifyDataSetChanged();
                countTxt.setText("Cows: " + items.size());
            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putString("ed1", breedEd.getText().toString());
        savedInstanceState.putString("ed2", numberEd.getText().toString());
        savedInstanceState.putString("itemListData", gson.toJson(items));
        // etc.
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        String ed1Str = savedInstanceState.getString("ed1");
        String ed2Str = savedInstanceState.getString("ed2");
        String itemListData = savedInstanceState.getString("itemListData");

        breedEd.setText(ed1Str);
        numberEd.setText(ed2Str);
        items =  gson.fromJson(itemListData, (Type) User.class);
    }


    public boolean isValided(String breedStr, String numberStr)
    {

        String tag = "Breed";
        if(breedStr.equals(""))
        {
            showAlertDialog(tag+" can't be empty");
            return false;
        }else if(checkInt(breedStr)==-1){
            showAlertDialog(tag+" need to be an Integer");
            return false;
        }

        tag = "ID";

        if(numberStr.equals(""))
        {
            showAlertDialog(tag+" can't be empty");
            return false;
        }else if(checkInt(numberStr)==-1){
            showAlertDialog(tag+" need to be Integer");
            return false;
        }else if(checkIntWithLimit(numberStr)==-2){
            showAlertDialog(" Please Enter a ID within in 0 to 999");
            return false;
        }

        return true;
    }

    public int checkInt(String value){
        try{
            int num = Integer.parseInt(value);
            return num;
        }catch (Exception e) {
            return -1;
        }
    }

    public int checkIntWithLimit(String value){
        try{
            int num = Integer.parseInt(value);
            if(num>-1 && num<1000) return num;
            else return -2;
        }catch (Exception e) {
            return -1;
        }
    }

    public void showAlertDialog(String alert)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(alert);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}