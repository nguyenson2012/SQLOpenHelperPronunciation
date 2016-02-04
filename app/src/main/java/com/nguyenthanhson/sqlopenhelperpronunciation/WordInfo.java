package com.nguyenthanhson.sqlopenhelperpronunciation;

/**
 * Created by SON on 2/3/2016.
 */
public class WordInfo {
    String word;
    String phonetic;
    String numberphonetic;
    String group;

    public WordInfo(){

    }

    public WordInfo(String word, String phonetic, String numberphonetic, String group) {
        this.word = word;
        this.phonetic = phonetic;
        this.numberphonetic = numberphonetic;
        this.group = group;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getNumberphonetic() {
        return numberphonetic;
    }

    public void setNumberphonetic(String numberphonetic) {
        this.numberphonetic = numberphonetic;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
