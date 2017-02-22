package re.pablochrun.com.reopendoors.ihm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import re.pablochrun.com.reopendoors.R;
import re.pablochrun.com.reopendoors.utils.PreferenceUtils;

/**
 * Created by pablo.covarrubias on 15/02/2017.
 */

public class AdminDoorPasswords extends Activity {

    EditText et1;
    EditText et2;

    boolean escapeRoomMode;

    PreferenceUtils pu;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        escapeRoomMode = i.getBooleanExtra("escapeRoom",false);

        if(escapeRoomMode) {
            setContentView(R.layout.admin_door_one_password);
        }
        else{
            setContentView(R.layout.admin_door_two_password);
        }

        setFont(escapeRoomMode);

        pu = new PreferenceUtils(this);

    }

    public void setFont(boolean escapeRoomMode){

        Typeface font;
        if(escapeRoomMode){
            TextView tv1 = (TextView) findViewById(R.id.puerta_1);
            et1 = (EditText) findViewById(R.id.puerta_1_pass);
            font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
            tv1.setTypeface(font);
            et1.setTypeface(font);

        }
        else{
            TextView tv2 = (TextView) findViewById(R.id.puerta_2);
            et2 = (EditText) findViewById(R.id.puerta_2_pass);
            font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
            tv2.setTypeface(font);
            et2.setTypeface(font);
        }

        Button cancel = (Button) findViewById(R.id.cancelAdminPass);
        Button ok = (Button) findViewById(R.id.okAdminPass);

        cancel.setTypeface(font);
        ok.setTypeface(font);
    }

    public void cancelAdmin(View v){
        //setResult(MainScreenGames.CONFIG_CANCEL);
        finish();
    }

    public void confirmAdmin(View v){

        if(escapeRoomMode){
            if(et1.getText() != null && !et1.getText().toString().equals("")) {
                pu.setDoorPassword(escapeRoomMode,et1.getText().toString());
                finish();
            }
            else{
                showCustomToast(R.string.securityIncomplet,5,R.color.colorErrorLogin);
            }
        }
        else{
            if(et2.getText() != null && !et2.getText().toString().equals("")) {
                pu.setDoorPassword(escapeRoomMode,et2.getText().toString());
                finish();
            }
            else{
                showCustomToast(R.string.securityIncomplet,5,R.color.colorErrorLogin);
            }
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
