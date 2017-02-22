package re.pablochrun.com.reopendoors.ihm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import re.pablochrun.com.reopendoors.R;
import re.pablochrun.com.reopendoors.utils.PreferenceUtils;

public class MainScreenGames extends AppCompatActivity {

    public final int RE_PABLOCHRUN_COM_REOPENDOORS_IHM_ADMINDOORS=1;

    public static final String DOOR_1="DOOR_1";
    public static final String DOOR_2="DOOR_2";

    public static final int CONFIG_OK=1;
    public static final int CONFIG_CANCEL=2;

    public Button startGame;

    boolean escapeRoom;

    PreferenceUtils pu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_games);
        Intent i = getIntent();
        escapeRoom = i.getBooleanExtra("escapeRoom",false);
        //startGame = (Button) findViewById(R.id.btnStartGame);
        pu = new PreferenceUtils(this);

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("RESUME","resume");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d("START","start");
        setFont();

        /*TODO: When selector is implemented
        if(checkDoorsConfigured()){
            allowReleaseTVirus();
        }
        else{
            disableReleaseTVirus();
        }
        */
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d("STOP","stop");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d("RESTART","restart");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("DESTROY","destroy");
    }

    /*
    For use when selectos will be implemented. Until then, enable/disable isn't visible.
     */
    private void allowReleaseTVirus() {
        startGame.setEnabled(true);
    }

    private void disableReleaseTVirus() {
        startGame.setEnabled(false);
    }

    public void setFont(){
        startGame = (Button) findViewById(R.id.btnStartGame);
        Typeface font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
        startGame.setTypeface(font);

        if(!escapeRoom){
            startGame.setText(R.string.startGame2);
        }
        else{
            startGame.setText(R.string.startGame);
        }
    }

    public void adminPass(View v){
        Intent adminDoors = new Intent(this, AdminDoorPasswords.class);
        adminDoors.putExtra("escapeRoom",escapeRoom);
        startActivity(adminDoors);
    }


    public boolean checkDoorsConfigured(){

        boolean configured =pu.checkDoorsConfigured(escapeRoom);
        return configured;
        /*
        boolean configured = false;
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
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
        */
    }

    public void startGame(View v){
        Log.d("Click","click start game");
        Intent i;
        if(escapeRoom){
             i = new Intent(this,DoorOnePass.class);
        }
        else{
            i = new Intent(this,DoorTwoPass.class);
        }
        startActivity(i);
    }
}
