package com.nguyenthanhson.sqlopenhelperpronunciation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listWord;
    ArrayList<WordInfo> listAllWord=new ArrayList<WordInfo>();
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        setDefaultData();
        insertDefaultData();
        setAdapterForListView();
        registerEvent();

    }

    private void setAdapterForListView() {
        ArrayList arrayList=dbHelper.getWordByGroup("01");
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listWord.setAdapter(arrayAdapter);
    }

    private void registerEvent() {
        listWord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WordInfo wordInfo=listAllWord.get(position);
                Toast.makeText(MainActivity.this,wordInfo.getWord()+","+wordInfo.getPhonetic()+","+wordInfo.getNumberphonetic()+","+wordInfo.getGroup(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDefaultData() {
        WordInfo near=new WordInfo("near","nɪə","3801","01");
        listAllWord.add(near);
        WordInfo clear=new WordInfo("clear","klɪə","324101","01");
        listAllWord.add(clear);
        WordInfo book=new WordInfo("book","bʊk","211532","15");
        listAllWord.add(book);

    }
    public void insertDefaultData(){
        for(WordInfo wordInfo:listAllWord){
            if(dbHelper.insertWord(wordInfo.getWord(), wordInfo.getPhonetic(), wordInfo.getNumberphonetic(), wordInfo.getGroup())){
                Toast.makeText(MainActivity.this,"Insert"+wordInfo.getWord()+" successful",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this,"Insert"+wordInfo.getWord()+" failed",Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void setUpView() {
        listWord=(ListView)findViewById(R.id.list_word);
        dbHelper=new DBHelper(this);
    }
}
