package re.pablochrun.com.reopendoors.ihm;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import re.pablochrun.com.reopendoors.R;

public class LoginMain extends AppCompatActivity {

    public static String LOGIN_PASS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        initPreferences();
        setFont();
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

    public void checkLogin(View v){
        boolean loginOK = false;

        EditText et = (EditText) findViewById(R.id.etPassword);
        String password = et.getText().toString();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        sharedPref.getInt(LOGIN_PASS,0);

        if( !password.equals(null)
                && !password.equals("")
                && Integer.getInteger(password)==sharedPref.getInt(LOGIN_PASS,0)){
            //NEW ACTIVITY TO ADMIN DOORs PASSWORDS.
            Intent adminPasswords = new Intent(this, AdminDoorPasswords.class);
        }
        else{
            showCustomToast(R.string.error_login, 500, R.color.colorErrorLogin);
        }
    }

    public void showCustomToast(int messageId,int duration, int color){
        LayoutInflater inflater=getLayoutInflater();
        View customToastView =inflater.inflate(R.layout.custom_toast, null);

        TextView text = (TextView) customToastView.findViewById(R.id.custom_toast_tv);
        text.setText(getResources().getString(messageId));
        Typeface font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
        text.setTypeface(font);

        final Toast customToast=new Toast(this);
        customToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM,0, 0);
        customToast.setDuration(duration);
        customToast.setView(customToastView);
        customToast.show();
    }

}
