package re.pablochrun.com.reopendoors.ihm;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import re.pablochrun.com.reopendoors.R;
import re.pablochrun.com.reopendoors.adapter.CustomGridAdapter;
import re.pablochrun.com.reopendoors.utils.PreferenceUtils;

public class DoorTwoPass extends DoorPassParent {

    PreferenceUtils pu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_two_pass);
        initComponents();
        pu = new PreferenceUtils(this);
    }

    public void initComponents(){
        super.initComponents();
        Typeface font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
        tvUpper = (TextView) findViewById(R.id.textScreenUpper);
        tvUpper.setTypeface(font);
        gv=(GridView) findViewById(R.id.gridView2);
        gv.setAdapter(new CustomGridAdapter(this, values,intValues, false));
    }

    @Override
    public void onClickGridItem(String textToAppend, int position){
        String passWrited = "";
        if(position == 9){
            passWrited = tv.getText().toString();
            if(passWrited != null && !passWrited.equals("")) {
                passWrited = passWrited.substring(0, passWrited.length() - 1);
                tv.setText(passWrited);
            }
        }
        else if (position == 11){
            checkPassCorrect(passWrited);
        }
        else{
            passWrited = tv.getText().toString();
            if(passWrited != null){
                passWrited += textToAppend;
            }
            else{
                passWrited = textToAppend;
            }
            tv.setText(passWrited);
        }
    }

    public void checkPassCorrect(String passWrited){
        passWrited = tv.getText().toString();
        if(passWrited != null && !passWrited.equals("")) {

            boolean passCorrect = pu.checkPassCorrect(false,Integer.valueOf(passWrited));
            if(passCorrect){
                showCustomToast(R.string.door_opened, Toast.LENGTH_LONG,R.color.mansion_destroyed);
            }
            else{
                showCustomToast(R.string.password_wrong, Toast.LENGTH_LONG,R.color.infected);
            }
        }
        else{
            showCustomToast(R.string.password_wrong, Toast.LENGTH_LONG, R.color.infected);
        }

    }
}