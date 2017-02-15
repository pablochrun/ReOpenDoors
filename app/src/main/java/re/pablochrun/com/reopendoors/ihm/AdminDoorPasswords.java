package re.pablochrun.com.reopendoors.ihm;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import re.pablochrun.com.reopendoors.R;

/**
 * Created by pablo.covarrubias on 15/02/2017.
 */

public class AdminDoorPasswords extends Activity {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_door_passwords);
        setFont();
    }

    public void setFont(){
        TextView tv1 = (TextView) findViewById(R.id.puerta_1);
        TextView tv2 = (TextView) findViewById(R.id.puerta_2);

        EditText et1 = (EditText) findViewById(R.id.puerta_1_pass);
        EditText et2 = (EditText) findViewById(R.id.puerta_2_pass);

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

    }
}
