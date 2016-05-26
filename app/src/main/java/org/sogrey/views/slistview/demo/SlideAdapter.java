package org.sogrey.views.slistview.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.sogrey.views.slistview.DropDownCornerListView.MessageItem;
import org.sogrey.views.slistview.SlideView;
import org.sogrey.views.slistview.SlideView.OnSlideListener;

import java.util.List;

/**
 * Created by Sogrey on 2016/5/26.
 */
public class SlideAdapter extends BaseAdapter implements OnSlideListener {

    private List<MessageItem> mMessageItems;
    private List<String> mDatas;
    private Context           mContext;
    private SlideView         mLastSlideViewWithStatusOn;
    private LayoutInflater   mLayoutInflater;

    public SlideAdapter(Context context,List<String> data,List<MessageItem> list) {
        this.mContext=context;
        this.mMessageItems=list;
        this.mDatas=data;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return this.mDatas==null?0:this.mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position,View convertView,ViewGroup parent) {
        ViewHolder holder;
        SlideView  slideView=(SlideView)convertView;

        if (slideView==null) {
            View itemView=this.mLayoutInflater.inflate(R.layout.item,null);
            View itemSlideView=this.mLayoutInflater.inflate(R.layout.item_silde,null);

            slideView=new SlideView(mContext);
            slideView.setContentView(itemView);
            slideView.setSlideView(itemSlideView);

            holder=new ViewHolder(slideView);
            slideView.setOnSlideListener(this);
            slideView.setTag(holder);
        } else {
            holder=(ViewHolder)slideView.getTag();
        }

        //回退的效果
//        MessageItem item=mMessageItems.get(position);
//        item.slideView=slideView;
        slideView.shrink();

        holder.itemText.setText((String)this.mDatas.get(position));
        holder.del.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"DEL "+position,Toast.LENGTH_SHORT).show();
            }
        });
        return slideView;
    }

    @Override
    public void onSlide(View view,int status) {
        if (mLastSlideViewWithStatusOn!=null&&mLastSlideViewWithStatusOn!=view) {
            mLastSlideViewWithStatusOn.shrink();
        }

        if (status==SLIDE_STATUS_ON) {
            mLastSlideViewWithStatusOn=(SlideView)view;
        }
    }

    public class ViewHolder {
        TextView itemText;
        TextView del;
        public ViewHolder(View v){
            itemText = (TextView)v.findViewById(R.id.txt_item_text);
            del = (TextView)v.findViewById(R.id.txt_item_del);
        }
    }
}
