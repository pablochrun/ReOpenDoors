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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import re.pablochrun.com.reopendoors.R;
import re.pablochrun.com.reopendoors.utils.PreferenceUtils;

public class LoginMain extends AppCompatActivity {

    public final String LOGIN_PASS="LOGIN";
    PreferenceUtils pu;

    EditText et;
    Button button;

    private PopupWindow riddleWindow;
    private View contentView;
    private LayoutInflater inflater;

    public LoginMain() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        pu = new PreferenceUtils(this);
        pu.initPreferences();
        setFont();

        riddleWindow = new PopupWindow(this);
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.riddle_popup, null);

        button = (Button) findViewById(R.id.btLogin);
        checkButtonAllowed("");//Initialize button disabled

        et = (EditText) findViewById(R.id.etPassword);
        et.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                checkButtonAllowed(s.toString());
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                checkButtonAllowed(s.toString());
            }
        });
    }

    public void showRiddle(View anchor) {
        riddleWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        riddleWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        riddleWindow.setOutsideTouchable(true);
        riddleWindow.setTouchable(true);
        riddleWindow.setFocusable(true);

        riddleWindow.setContentView(contentView);
        riddleWindow.showAtLocation(anchor, Gravity.CENTER, 0, 0);


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

    public void checkButtonAllowed(String s){
        if(s.toString().equals("")){
            button.setEnabled(false);
        }
        else{
            button.setEnabled(true);
        }
    }

    public void setFont(){
        Button button = (Button) findViewById(R.id.btLogin);
        Typeface font = Typeface.createFromAsset(getAssets(), "JLSSpaceGothicR_NC.otf");
        button.setTypeface(font);
    }

    public void checkLogin(View v){
        boolean loginOK = false;
        String password = et.getText().toString();
        loginOK = pu.checkLogin(password);

        Log.d("Button pressed: " + ((Button)v).isPressed(), "Button enable: " + ((Button)v).isEnabled());
        if( loginOK){
            Intent gameSelectorScreen = new Intent(this, GameSelectorScreen.class);
            startActivity(gameSelectorScreen);
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
