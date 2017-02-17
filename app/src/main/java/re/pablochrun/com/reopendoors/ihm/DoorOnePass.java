package re.pablochrun.com.reopendoors.ihm;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import re.pablochrun.com.reopendoors.R;
import re.pablochrun.com.reopendoors.adapter.CustomGridAdapter;

public class DoorOnePass extends AppCompatActivity {

    GridView gv;
    TextView tv, tvUC;
    public static String [] values={"1","2","3","4","5","6","7","8","9","","0",""};
    public static int [] intValues =  {1,2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.door_one_pass);
        initComponents();
    }

    public void initComponents(){
        gv=(GridView) findViewById(R.id.gridView1);
        gv.setAdapter(new CustomGridAdapter(this, values,intValues));
        tv = (TextView) findViewById(R.id.textScreenPass);
        Typeface font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
        tv.setTypeface(font);

        tvUC = (TextView) findViewById(R.id.textScreenUC);
        tvUC.setTypeface(font);
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
