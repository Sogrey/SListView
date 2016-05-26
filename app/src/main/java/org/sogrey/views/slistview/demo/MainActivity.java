package org.sogrey.views.slistview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;

import org.sogrey.views.slistview.DropDownCornerListView.MessageItem;
import org.sogrey.views.slistview.SlideListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SlideListView mRecyclerView;
    MyAdapter        mMyAdapter;
    SlideAdapter        mSlideAdapter;
    List<String> data= new ArrayList<>();
    List<MessageItem> data2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDatas();
    }

    private void initViews() {
        mRecyclerView = (SlideListView)findViewById(R.id
                                                                    .rv_act_idex);
    }
    private void initDatas() {
        mMyAdapter = new MyAdapter(this,data);
        mSlideAdapter = new SlideAdapter(this,data,data2);
        mRecyclerView.setAdapter( mSlideAdapter );
        mRecyclerView.setOnItemClickListener( new AdapterView.OnItemClickListener( ) {
            @Override
            public void onItemClick (AdapterView< ? > parent,View view,int position,long id ) {
//                startIntent( ProjectMngActivity.class, R.anim.base_slide_right_in, R.anim
//                        .base_slide_remain );
            }
        } );
        // set drop down listener
//        mRecyclerView.setOnDropDownListener( new DropDownCornerListView.OnDropDownListener( ) {
//
//            @Override
//            public void onDropDown ( ) {
//                //下拉刷新
//                //TODO
//                new android.os.Handler( ).postDelayed( new Runnable( ) {
//                    @Override
//                    public void run ( ) {
//                        data.add(0,"新数据产生...");
//                        mMyAdapter.notifyDataSetChanged();
//                        // should call onDropDownComplete function of DropDownListView at end of
//                        // drop down complete.
//                        SimpleDateFormat dateFormat= new SimpleDateFormat("MM-dd HH:mm:ss" );
//                        mRecyclerView.onDropDownComplete( getString( R.string.update_at ) +
//                                                          dateFormat.format( new Date( ) ) );
//                    }
//                }, 1000 );
//
//            }
//        } );
//
//        // set on bottom listener
//        mRecyclerView.setOnBottomListener( new View.OnClickListener( ) {
//
//            @Override
//            public void onClick ( View v ) {
//                //到底刷新
//                //TODO
//                new android.os.Handler( ).postDelayed( new Runnable( ) {
//                    @Override
//                    public void run ( ){
//                        data.add("新数据产生...");
//                        mMyAdapter.notifyDataSetChanged();
//
//                        // should call onBottomComplete function of DropDownListView at end of on
//                        // bottom complete.
//                        mRecyclerView.onBottomComplete( );
//                    }
//                }, 1000 );
//            }
//        } );



        //swipeMenu
        // step 1. create a MenuCreator
//        SwipeMenuCreator creator = new SwipeMenuCreator() {
//
//            @Override
//            public void create(SwipeMenu menu) {
//                // create "open" item
//                SwipeMenuItem openItem= new SwipeMenuItem(getApplicationContext());
//                // set item background
//                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9,0xC9,0xCE)));
//                // set item width
//                openItem.setWidth(dp2px(90));
//                // set item title
//                openItem.setTitle("Open");
//                // set item title fontsize
//                openItem.setTitleSize(18);
//                // set item title font color
//                openItem.setTitleColor(Color.WHITE);
//                // add to menu
//                menu.addMenuItem(openItem);
//
//                // create "delete" item
//                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
//                // set item background
//                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
//                // set item width
//                deleteItem.setWidth(dp2px(90));
//                // set a icon
//                deleteItem.setIcon(R.drawable.ic_delete);
//                // add to menu
//                menu.addMenuItem(deleteItem);
//            }
//        };
//        // set creator
//        mRecyclerView.setMenuCreator(creator);
//
//        // step 2. listener item click event
//        mRecyclerView.setOnMenuItemClickListener(new DropDownListView.OnMenuItemClickListener() {
//            @Override
//            public void onMenuItemClick(int position,SwipeMenu menu,int index) {
//                switch (index) {
//                    case 0:
//                        // open
//                        Toast.makeText(MainActivity.this,"点击第"+position+"条",Toast.LENGTH_LONG).show();
//                        break;
//                    case 1:
//                        // delete(item);
//                        data.remove(position);
//                        mMyAdapter.notifyDataSetChanged();
//                        break;
//                }
//
//            }
//        });





        //initData
        for (int i=0;i<30;i++){
            data.add("元数据"+(i+1));
        }
//        mMyAdapter.notifyDataSetChanged();
//        for (int i=0;i<30;i++){
//            data.add("元数据"+(i+1));
//            MessageItem item = new MessageItem<String>();
//
//        }
        mSlideAdapter.notifyDataSetChanged();


    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics());
    }
}
