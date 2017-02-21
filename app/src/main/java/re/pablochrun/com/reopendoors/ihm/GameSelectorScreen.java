package re.pablochrun.com.reopendoors.ihm;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import re.pablochrun.com.reopendoors.R;

public class GameSelectorScreen extends AppCompatActivity {

    Button escape_room;
    Button game_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selector_screen);
        setFont();
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

    public void setFont(){
        escape_room = (Button) findViewById(R.id.selector_escape_room);
        Typeface font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
        escape_room.setTypeface(font);

        game_2 = (Button) findViewById(R.id.select_game_2);
        game_2.setTypeface(font);
    }

    public void startEscapeRoom(View v){
        Intent mainScreen = new Intent(this, MainScreenGames.class);
        mainScreen.putExtra("escapeRoom",true);
        startActivity(mainScreen);
    }

    public void startGame2(View v){
        Intent mainScreen = new Intent(this, MainScreenGames.class);
        mainScreen.putExtra("escapeRoom",false);
        startActivity(mainScreen);
    }
}
