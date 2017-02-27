package re.pablochrun.com.reopendoors.ihm;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.util.TimeUnit;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import re.pablochrun.com.reopendoors.R;
import re.pablochrun.com.reopendoors.adapter.CustomGridAdapter;
import re.pablochrun.com.reopendoors.utils.PreferenceUtils;

public class DoorOnePass extends DoorPassParent{

    TextView tvCountdown;
    PreferenceUtils pu;
    CountDownTimer cdt;

    LinearLayout mainLayout;

    PopupWindow riddleWindow;
    View contentView;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_one_pass);
        initComponents();
        pu = new PreferenceUtils(this);
    }

    public void initComponents(){
        super.initComponents();
        Typeface font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
        tvUpper = (TextView) findViewById(R.id.textScreenUC);
        tvUpper.setTypeface(font);
        gv=(GridView) findViewById(R.id.gridView1);
        gv.setAdapter(new CustomGridAdapter(this, values,intValues,true));

        mainLayout = (LinearLayout) findViewById(R.id.mainLinearLayoutDoorOne);
        mainLayout.setBackgroundResource(R.drawable.blood_splatter);

        tvCountdown = (TextView) findViewById(R.id.countdown);
        tvCountdown.setTypeface(font);

        cdt = new CountDownTimer(3600000, 1000) {
            public void onTick(long millisUntilFinished) {
                long minutes = java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                long seconds = (java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - minutes*60);

                String countdownUpdated = "";
                if(minutes == 0){
                    countdownUpdated = (seconds<10?"0".concat(String.valueOf(seconds)):String.valueOf(seconds)) + " s.";
                }
                else{
                    if(minutes<10){
                        if(seconds<10){
                            countdownUpdated = "0".concat(String.valueOf(minutes)) + ":"
                                                    + ("0".concat(String.valueOf(seconds)));
                        }
                        else{
                            countdownUpdated = "0".concat(String.valueOf(minutes)) +":"+String.valueOf(seconds);
                        }
                    }
                    else{
                        countdownUpdated = String.valueOf(minutes) +":"+String.valueOf(seconds);
                    }
                }
                String countdownFormatted = getString(R.string.spreadingIn, countdownUpdated);

                tvCountdown.setText(countdownFormatted);

                if(minutes <=29){
                    tvCountdown.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.countdown_2));
                }
            }

            public void onFinish() {
                tvCountdown.setText(R.string.infectados);
                riddleWindow = new PopupWindow(getApplicationContext());
                inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                contentView = inflater.inflate(R.layout.riddle_popup_infected, null);
                showRiddle(mainLayout);
            }
        }.start();
    }

    public void showRiddle(View anchor) {
        riddleWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        riddleWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        riddleWindow.setOutsideTouchable(true);
        riddleWindow.setTouchable(true);
        riddleWindow.setFocusable(true);

        riddleWindow.setContentView(contentView);
        riddleWindow.showAtLocation(anchor, Gravity.CENTER, 0, 0);

        TextView riddleTV = (TextView) contentView.findViewById(R.id.riddle_infected_text);
        TextView helloSergio = (TextView) contentView.findViewById(R.id.title_riddle_infected);
        Typeface font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
        riddleTV.setTypeface(font);
        helloSergio.setTypeface(font);
    }

    boolean isRiddleShown() {
        if (riddleWindow != null && riddleWindow.isShowing()){
            return true;
        }
        return false;
    }

    void dismissTooltip() {
        if (riddleWindow != null && riddleWindow.isShowing())
            riddleWindow.dismiss();
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
            boolean passCorrect = pu.checkPassCorrect(true, Integer.valueOf(passWrited));

            if (passCorrect) {
                cdt.cancel();
                showCustomToast(R.string.mansion_destroyed, Toast.LENGTH_LONG, R.color.mansion_destroyed);
            } else {
                showCustomToast(R.string.password_wrong, Toast.LENGTH_LONG, R.color.infected);
            }
        }
        else{
            showCustomToast(R.string.password_wrong, Toast.LENGTH_LONG, R.color.infected);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("onDestroy","called");
        cdt.cancel();
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d("onPause","called");
    }


}
