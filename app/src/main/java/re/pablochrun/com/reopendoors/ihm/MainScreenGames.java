package re.pablochrun.com.reopendoors.ihm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import re.pablochrun.com.reopendoors.R;
import re.pablochrun.com.reopendoors.utils.PreferenceUtils;

public class MainScreenGames extends AppCompatActivity {

    public final int RE_PABLOCHRUN_COM_REOPENDOORS_IHM_ADMINDOORS=1;

    public static final String DOOR_1="DOOR_1";
    public static final String DOOR_2="DOOR_2";

    public static final int CONFIG_OK=1;
    public static final int CONFIG_CANCEL=2;

    public Button startGame;
    public ImageView setupImage;
    boolean escapeRoom;

    PreferenceUtils pu;
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        escapeRoom = i.getBooleanExtra("escapeRoom",false);

        if(escapeRoom)
            setContentView(R.layout.activity_main_screen_games);
        else
            setContentView(R.layout.activity_main_screen_game_2);

        //startGame = (Button) findViewById(R.id.btnStartGame);
        pu = new PreferenceUtils(this);

        setupImage = (ImageView) findViewById(R.id.imageSetup);

        if(escapeRoom) {
            mainLayout = (LinearLayout) findViewById(R.id.activity_main_screen);
            mainLayout.setBackgroundResource(R.drawable.broken_test_tube);
        }
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

        if(checkDoorsConfigured()){
            allowReleaseTVirus();
        }
        else{
            disableReleaseTVirus();
        }
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
        if(escapeRoom){
            setupImage.setBackground(ContextCompat.getDrawable(this,R.drawable.drawable_umbrella_corp));
        }
        else{
            setupImage.setBackground(ContextCompat.getDrawable(this,R.drawable.drawable_setup));
        }
    }

    private void disableReleaseTVirus() {
        startGame.setEnabled(false);
        if(escapeRoom){
            setupImage.setBackground(ContextCompat.getDrawable(this,R.drawable.drawable_umbrella_corp_remarked));
        }
        else{
            setupImage.setBackground(ContextCompat.getDrawable(this,R.drawable.drawable_setup_remarked));
        }
    }

    public void setFont(){
        startGame = (Button) findViewById(R.id.btnStartGame);
        Typeface font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
        startGame.setTypeface(font);
    }

    public void adminPass(View v){
        Intent adminDoors = new Intent(this, AdminDoorPasswords.class);
        adminDoors.putExtra("escapeRoom",escapeRoom);
        startActivity(adminDoors);
    }


    public boolean checkDoorsConfigured(){
        boolean configured =pu.checkDoorsConfigured(escapeRoom);
        return configured;
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
