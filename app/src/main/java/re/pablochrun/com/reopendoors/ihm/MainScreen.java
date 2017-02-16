package re.pablochrun.com.reopendoors.ihm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import re.pablochrun.com.reopendoors.R;

public class MainScreen extends AppCompatActivity {

    public final int RE_PABLOCHRUN_COM_REOPENDOORS_IHM_ADMINDOORS=1;
    public static final String DOOR_1="DOOR_1";
    public static final String DOOR_2="DOOR_2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        setFont();

        if(checkDoorsConfigured()){
            allowReleaseTVirus();
        }
    }

    private void allowReleaseTVirus() {
        //enable btn release T-Virus
    }

    public void setFont(){
        Button button = (Button) findViewById(R.id.btnStartGame);
        Typeface font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
        button.setTypeface(font);
    }

    public void adminPass(View v){
        Intent adminDoors = new Intent(this, AdminDoorPasswords.class);
        startActivityForResult(adminDoors,RE_PABLOCHRUN_COM_REOPENDOORS_IHM_ADMINDOORS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == RE_PABLOCHRUN_COM_REOPENDOORS_IHM_ADMINDOORS){
            if(checkDoorsConfigured()){
                allowReleaseTVirus();
            }
        }
    }

    public boolean checkDoorsConfigured(){
        boolean configured = false;
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int door1Stored = sharedPref.getInt(DOOR_1,0);
        int door2Stored = sharedPref.getInt(DOOR_2,0);

        if(door1Stored != 0 && door2Stored != 0){
            configured = true;
        }

        return configured;
    }

}
