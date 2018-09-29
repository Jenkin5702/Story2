package com.kisetsu.story.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kisetsu.story.R;
import com.kisetsu.story.itembeans.ItemBeanFriends;
import com.kisetsu.story.loader.ImageLoader;

import java.util.List;

public class ListAdapterFriends extends BaseAdapter {
    private List<ItemBeanFriends> listItem;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    class ViewHolder{
        public TextView publisher;
        public TextView content;
        public ImageView image;
    }

    public ListAdapterFriends(Context context, List<ItemBeanFriends> items){
        this.listItem=items;
        inflater=LayoutInflater.from(context);
        imageLoader=new ImageLoader();
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int i) {
        return listItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null){
            viewHolder=new ViewHolder();
            view=inflater.inflate(R.layout.item_home,null);
            viewHolder.publisher =view.findViewById(R.id.tv_publisher);
            viewHolder.content =view.findViewById(R.id.tv_content);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
//        viewHolder.image.setImageResource(R.mipmap.ic_launcher);
        String imageUrl=listItem.get(i).imageUrl;
        imageLoader.showImageByAsyncTask(viewHolder.image,imageUrl);
        viewHolder.image.setTag(imageUrl);
        viewHolder.publisher.setText(listItem.get(i).title);
        viewHolder.content.setText(listItem.get(i).content);
        return view;
    }
}
