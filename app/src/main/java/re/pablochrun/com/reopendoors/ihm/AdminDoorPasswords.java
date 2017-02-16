package re.pablochrun.com.reopendoors.ihm;

import android.app.Activity;
import android.content.Context;
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

/**
 * Created by pablo.covarrubias on 15/02/2017.
 */

public class AdminDoorPasswords extends Activity {

    EditText et1;
    EditText et2;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_door_passwords);
        setFont();
    }

    public void setFont(){
        TextView tv1 = (TextView) findViewById(R.id.puerta_1);
        TextView tv2 = (TextView) findViewById(R.id.puerta_2);

        et1 = (EditText) findViewById(R.id.puerta_1_pass);
        et2 = (EditText) findViewById(R.id.puerta_2_pass);

        Button cancel = (Button) findViewById(R.id.cancelAdminPass);
        Button ok = (Button) findViewById(R.id.okAdminPass);

        Typeface font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
        tv1.setTypeface(font);
        tv2.setTypeface(font);
        et1.setTypeface(font);
        et2.setTypeface(font);
        cancel.setTypeface(font);
        ok.setTypeface(font);
    }

    public void cancelAdmin(View v){
        finish();
    }

    public void confirmAdmin(View v){
        if(et1.getText() != null && et2.getText() != null
                && !et1.getText().toString().equals("")
                && !et2.getText().toString().equals("")) {
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(MainScreen.DOOR_1, Integer.parseInt(et1.getText().toString()));
            editor.putInt(MainScreen.DOOR_2, Integer.parseInt(et2.getText().toString()));
            editor.commit();
        }
        else{
            showCustomToast(R.string.securityIncomplet,5,R.color.colorErrorLogin);
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
