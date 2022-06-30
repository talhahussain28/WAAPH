package waaph.gb.com.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SaveInSharedPreference {

    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;

    // shared pref mode
    private final int PRIVATE_MODE = 0;

    @SuppressLint("CommitPrefEdits")
    public SaveInSharedPreference(Context context) {
//        Context _context = context;
        pref = context.getSharedPreferences(Constants.Companion.getPREF_NAME(), PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setBoolean(String key, boolean value){
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key) {
        return pref.getBoolean(key, false);
    }

    public void setString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return pref.getString(key, "");
    }

    public void setInteger(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public Integer getInteger(String key) {
        return pref.getInt(key, 0);
    }

    public void clearSavedInSharedPreference(){
        editor.clear();
        editor.commit();
    }

}
