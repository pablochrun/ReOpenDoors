package re.pablochrun.com.reopendoors.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import re.pablochrun.com.reopendoors.R;
import re.pablochrun.com.reopendoors.ihm.DoorOnePass;
import re.pablochrun.com.reopendoors.ihm.MainScreen;

/**
 * Created by pablo.covarrubias on 17/02/2017.
 */

public class CustomGridAdapter extends BaseAdapter {

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;

    public CustomGridAdapter(DoorOnePass doorOnePass, String[] values, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=values;
        context=doorOnePass;
        imageId=prgmImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        //ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.custom_grid_item, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textViewGrid);
        //holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

        holder.tv.setText(result[position]);
        //holder.img.setImageResource(imageId[position]);

        Typeface font = Typeface.createFromAsset(context.getAssets(), "JLSSpaceGothicR_NC.otf");
        holder.tv.setTypeface(font);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ((DoorOnePass)context).onClickGridItem(result[position]);
            }
        });

        return rowView;
    }

}