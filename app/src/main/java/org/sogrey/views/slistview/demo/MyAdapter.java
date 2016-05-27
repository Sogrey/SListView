package org.sogrey.views.slistview.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sogrey on 2016/5/25.
 */
public class MyAdapter extends BaseAdapter{
    Context mContext;
    List<String> mDatas;
    public MyAdapter(Context context,List<String> list){
        this.mContext = context;
        this.mDatas = list;
    }
    @Override
    public int getCount() {
        return this.mDatas==null?0:this.mDatas.size();
    }

    @Override
    public String getItem(int position) {
        return this.mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.mDatas.get(position).hashCode();
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;
        if (view==null){
            view=View.inflate(mContext,R.layout.item_list_app,null);
            holder = new ViewHolder();
            holder.txt=(TextView)view.findViewById(R.id.tv_name);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }
        holder.txt.setText(this.mDatas.get(position));
        return view;
    }

    public class ViewHolder{
        TextView txt;
    }
}
