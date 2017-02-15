package re.pablochrun.com.reopendoors;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginMain extends AppCompatActivity {

    public static String LOGIN_PASS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        initPreferences();

        setFont();
       //hideActionBar();
    }

    public void initPreferences(){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        if(!sharedPref.contains(LOGIN_PASS)){
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(LOGIN_PASS, 111111);
            editor.commit();
        }
    }


    public void setFont(){
        Button button = (Button) findViewById(R.id.btLogin);
        Typeface font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
        button.setTypeface(font);
    }

    public boolean checkLogin(){
        boolean loginOK = false;

        EditText et = (EditText) findViewById(R.id.etPassword);
        String password = et.getText().toString();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        sharedPref.getInt(LOGIN_PASS,0);




        return loginOK;
    }

    /*
    public void hideActionBar(){
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getActionBar();
        if(null != actionBar)
            actionBar.hide();
    }
    */
}
