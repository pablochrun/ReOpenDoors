package re.pablochrun.com.reopendoors.ihm;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import re.pablochrun.com.reopendoors.R;
import re.pablochrun.com.reopendoors.adapter.CustomGridAdapter;

public class DoorOnePass extends DoorPassParent{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_one_pass);
        initComponents();
    }

    /*
    public void initComponents(){
        gv=(GridView) findViewById(R.id.gridView1);
        gv.setAdapter(new CustomGridAdapter(this, values,intValues));
        tv = (TextView) findViewById(R.id.textScreenPass);
        Typeface font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
        tv.setTypeface(font);

        tvUC = (TextView) findViewById(R.id.textScreenUC);
        tvUC.setTypeface(font);
    }*/

    public void initComponents(){
        super.initComponents();
        Typeface font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
        tvUpper = (TextView) findViewById(R.id.textScreenUC);
        tvUpper.setTypeface(font);
        gv=(GridView) findViewById(R.id.gridView1);
        gv.setAdapter(new CustomGridAdapter(this, values,intValues));
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
