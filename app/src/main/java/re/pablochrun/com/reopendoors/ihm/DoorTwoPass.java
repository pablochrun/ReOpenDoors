package re.pablochrun.com.reopendoors.ihm;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import re.pablochrun.com.reopendoors.R;
import re.pablochrun.com.reopendoors.adapter.CustomGridAdapter;

public class DoorTwoPass extends DoorPassParent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_two_pass);
        initComponents();
    }

    public void initComponents(){
        super.initComponents();
        Typeface font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
        tvUpper = (TextView) findViewById(R.id.textScreenUpper);
        tvUpper.setTypeface(font);
        gv=(GridView) findViewById(R.id.gridView2);
        gv.setAdapter(new CustomGridAdapter(this, values,intValues));

    }

}
