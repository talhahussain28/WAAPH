package waaph.gb.com.adapters.dropDown;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import waaph.gb.com.R;
import waaph.gb.com.entities.cdf.GetAllRegionResponse;
import waaph.gb.com.entities.cdf.RegionData;

public class RegionBottomAdapter extends ArrayAdapter<RegionData> {
    private Context context;
    private String type;
    private LayoutInflater layoutInflater;

    public RegionBottomAdapter(Context context, String type, ArrayList<RegionData> list){
        super(context, 0, list);
        this.context = context;
        this.type = type;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.row_item_general_bottom, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        RegionData data = getItem(position);

        if (data.getTitle() != null) {
           // viewHolder.imageView.setImageResource(data.image);
            viewHolder.tv_title.setText(data.getTitle());
        } else {

        }

        return convertView;
    }

    protected class ViewHolder{

        private ImageView imageView;
        private TextView tv_title;

        public ViewHolder(View view){
           // imageView = view.findViewById(R.id.imageView);
            tv_title = view.findViewById(R.id.textView_title);
        }
    }
}
