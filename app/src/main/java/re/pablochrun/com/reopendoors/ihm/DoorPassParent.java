package re.pablochrun.com.reopendoors.ihm;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import re.pablochrun.com.reopendoors.R;
import re.pablochrun.com.reopendoors.adapter.CustomGridAdapter;

public class DoorPassParent extends AppCompatActivity {

    GridView gv;
    TextView tv, tvUpper;
    public static String [] values={"1","2","3","4","5","6","7","8","9","","0",""};
    public static int [] intValues =  {1,2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initComponents(){
        tv = (TextView) findViewById(R.id.textScreenPass);
        Typeface font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
        tv.setTypeface(font);
    }

    public void onClickGridItem(String textToAppend){
        String passWrited = tv.getText().toString();

        if(passWrited != null){
            passWrited += textToAppend;
        }
        else{
            passWrited = textToAppend;
        }
        tv.setText(passWrited);
    }
}
