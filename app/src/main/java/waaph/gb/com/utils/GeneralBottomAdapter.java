package waaph.gb.com.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import waaph.gb.com.R;

public class GeneralBottomAdapter extends ArrayAdapter<GeneralBottomAdapter.ListItemModel> {
    private Context context;
    private String type;
    private LayoutInflater layoutInflater;

    public GeneralBottomAdapter(Context context, String type, List<ListItemModel> list){
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

        ListItemModel data = getItem(position);

        if (data.title != null) {
           // viewHolder.imageView.setImageResource(data.image);
            viewHolder.tv_title.setText(data.title);
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

    public static class ListItemModel {

       // public int image;
        public String title;


        public ListItemModel(/*int image,*/ String title) {
            //this.image = image;
            this.title = title;
        }
    }
}
