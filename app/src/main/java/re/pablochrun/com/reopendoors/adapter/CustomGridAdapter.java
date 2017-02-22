package re.pablochrun.com.reopendoors.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import re.pablochrun.com.reopendoors.R;
import re.pablochrun.com.reopendoors.ihm.DoorOnePass;
import re.pablochrun.com.reopendoors.ihm.DoorPassParent;

/**
 * Created by pablo.covarrubias on 17/02/2017.
 */

public class CustomGridAdapter extends BaseAdapter {

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;

    public CustomGridAdapter(DoorPassParent doorPassParent, String[] values, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=values;
        context=doorPassParent;
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
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.custom_grid_item, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textViewGrid);
        holder.img=(ImageView) rowView.findViewById(R.id.imageViewGridView);

        holder.tv.setText(result[position]);
        //holder.img.setImageResource(imageId[position]);

        Typeface font = Typeface.createFromAsset(context.getAssets(), "JLSSpaceGothicR_NC.otf");
        holder.tv.setTypeface(font);

        if (position == 9) {
            holder.tv.setBackgroundResource(R.drawable.background_drawable_delete);
        }
        if(position==11){
            holder.tv.setBackgroundResource(R.drawable.background_drawable_tick);
        }

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((DoorPassParent)context).onClickGridItem(result[position], position);
            }
        });

        return rowView;
    }

}