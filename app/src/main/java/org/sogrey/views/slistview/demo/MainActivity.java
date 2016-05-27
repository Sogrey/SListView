package org.sogrey.views.slistview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import org.sogrey.views.slistview.DropDownCornerListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DropDownCornerListView DDCLV;
    List<String> data=new ArrayList<>();
    MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DDCLV=(DropDownCornerListView)findViewById(R.id.lst_ddcl);
        initData();
    }

    private void initData() {
        mMyAdapter=new MyAdapter(this,data);
        DDCLV.setAdapter(mMyAdapter);
        DDCLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
                //                startIntent( ProjectMngActivity.class, R.anim
                // .base_slide_right_in, R.anim
                //                        .base_slide_remain );
            }
        });
        // set drop down listener
        DDCLV.setOnDropDownListener(new DropDownCornerListView.OnDropDownListener() {

            @Override
            public void onDropDown() {
                //下拉刷新
                //TODO
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.add(0,"新数据产生...");
                        mMyAdapter.notifyDataSetChanged();
                        // should call onDropDownComplete function of DropDownListView at end of
                        // drop down complete.
                        SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd HH:mm:ss");
                        DDCLV.onDropDownComplete(getString(R.string.update_at)+
                                                 dateFormat.format(new Date()));
                    }
                },1000);

            }
        });

        // set on bottom listener
        DDCLV.setOnBottomListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //到底刷新
                //TODO
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.add("新数据产生...");
                        mMyAdapter.notifyDataSetChanged();

                        // should call onBottomComplete function of DropDownListView at end of on
                        // bottom complete.
                        DDCLV.onBottomComplete();
                    }
                },1000);
            }
        });

        //initData
        for (int i=0;i<30;i++) {
            data.add("元数据"+(i+1));
        }
        mMyAdapter.notifyDataSetChanged();
    }


}
