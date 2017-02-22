package re.pablochrun.com.reopendoors.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by pablo.covarrubias on 22/02/2017.
 */

public class PreferenceUtils {

    private final String PREFERENCES_NAME= "OPEN_DOORS_PREFERENCES";
    private final String LOGIN_PASS="LOGIN";

    private static final String DOOR_1="DOOR_1";
    private static final String DOOR_2="DOOR_2";

    private Context context;

    public PreferenceUtils(Context context){
        this.context = context;
    }

    public void initPreferences(){
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        if(!sharedPref.contains(LOGIN_PASS)){
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(LOGIN_PASS, 111111);
            editor.apply();
        }
    }

    public boolean checkLogin(String password){
        boolean loginOK = false;
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        int passStored = sharedPref.getInt(LOGIN_PASS,0);

        if( password != null && !password.equals("")
                && Integer.parseInt(password)==passStored){
            loginOK = true;
        }
        return loginOK;
    }

    public boolean checkDoorsConfigured(boolean escapeRoom){
        boolean configured = false;
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
        int door1Stored = sharedPref.getInt(DOOR_1,0);
        int door2Stored = sharedPref.getInt(DOOR_2,0);

        if(escapeRoom){
            if(door1Stored != 0){
                configured = true;
            }
        }
        else{
            if(door2Stored != 0){
                configured = true;
            }
        }

        return configured;
    }

    public void setDoorPassword(boolean escapeRoom, String doorPassWrited){

        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if(escapeRoom){
            editor.putInt(DOOR_1, Integer.parseInt(doorPassWrited));
            editor.commit();
        }
        else{
            editor.putInt(DOOR_2, Integer.parseInt(doorPassWrited));
            editor.commit();
        }
    }

    public boolean checkPassCorrect(boolean escapeRoom, int password){
        boolean passCorrect = false;

        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        int passStored;

        if(escapeRoom){
            passStored = sharedPref.getInt(DOOR_1,0);
        }
        else{
            passStored = sharedPref.getInt(DOOR_2,0);
        }

        passCorrect = (password==passStored)?true:false;
        return passCorrect;
    }
}
