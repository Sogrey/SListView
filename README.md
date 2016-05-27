# SListView
##SListView 有哪些功能：<br/>


* `CornerListView` 圓角ListView
* `DropDownCornerListView` 支持上下拉刷新的圓角ListView
* `DropDownListView` 支持上下拉刷新的ListView
* `PullToRefreshSwipeMenuListView` 支持上下拉刷新並且可側滑刪除Item的ListView
* `PullToRefreshSwipeMenuAdapter` 支持上下拉刷新並且可側滑刪除Item的ListView的策劃菜單適配器



##如何使用？
### CornerListView 圓角ListView
Xml文件寫法：
``` xml
<org.sogrey.views.slistview.CornerListView
        android:id="@+id/lst_ddcl"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@drawable/bg_lyt_white_corner"
        >
```

### DropDownListView 支持上下拉刷新的ListView [`dropdown-to-refresh-and-bottom-load-more-listview`](http://www.trinea.cn/android/dropdown-to-refresh-and-bottom-load-more-listview/) from trinea
Xml文件寫法：
``` xml
<org.sogrey.views.slistview.DropDownListView 
        android:id="@+id/lst_ddcl"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:isAutoLoadOnBottom="true"
        app:isDropDownStyle="true"
        app:isOnBottomStyle="true"
        >
```
其中：<br/>
是否支持下拉刷新：
``` xml
    app:isDropDownStyle="true"
```
是否支持上拉刷新：
``` xml
    app:isOnBottomStyle="true"
```
是否支持到底自動刷新：
``` xml
    app:isAutoLoadOnBottom="true"
```

### DropDownCornerListView 支持上下拉刷新的圓角ListView
Xml文件寫法：
``` xml
<org.sogrey.views.slistview.DropDownCornerListView
        android:id="@+id/lst_ddcl"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:bg="@drawable/bg_lyt_white_corner"
        app:isAutoLoadOnBottom="true"
        app:isCorner="true"
        app:isDropDownStyle="true"
        app:isOnBottomStyle="true"
        >
```
其中：<br/>
下面屬性上面已解釋過。
``` xml
    app:isAutoLoadOnBottom="true"
    app:isDropDownStyle="true"
    app:isOnBottomStyle="true"
```
是否支持圓角：
``` xml
    app:isCorner="true"
```
圓角時List的背景資源，僅圓角下有效：
``` xml
    app:bg="@drawable/bg_lyt_white_corner"
```
```java
DropDownCornerListView DDCLV;
MyAdapter mMyAdapter;
 private void initViews() {
 DDCLV=(DropDownCornerListView)findViewById(R.id.lst_ddcl);
        initData();
    }

    private void initData() {
        mMyAdapter=new MyAdapter(this,data);
        DDCLV.setAdapter(mMyAdapter);
        DDCLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
                //TODO
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
```
### PullToRefreshSwipeMenuListView 支持上下拉刷新並且可側滑刪除Item的ListView [`SwipeMenuListView`](https://github.com/baoyongzhang/SwipeMenuListView) from baoyongzhang <br/>
xml寫法：
``` xml
<org.sogrey.views.slistview.PullToRefreshSwipeMenuListView
    android:layout_width="match_parent"
    android:id="@+id/lst_smlv_content"
    android:layout_height="match_parent">

</org.sogrey.views.slistview.PullToRefreshSwipeMenuListView>
``` 
java：
```java
PullToRefreshSwipeMenuListView mListView=(PullToRefreshSwipeMenuListView)findViewById(R.id.lst_smlv_content);
        mAdapter=new AppAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setPullRefreshEnable(true);//[開啟下拉刷新 ]
        mListView.setPullLoadEnable(true);//[開啟上拉加載 ]
        mListView.setXListViewListener(this);// [見 onRefresh() & onLoadMore() ]
        mHandler=new Handler();

        // step 1. create a MenuCreator
        SwipeMenuCreator creator=new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem=new SwipeMenuItem(getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9,0xC9,0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem=new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,0x3F,0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mListView.setMenuCreator(creator);

        // step 2. listener item click event
        mListView.setOnMenuItemClickListener(new PullToRefreshSwipeMenuListView
                .OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position,SwipeMenu menu,int index) {
                ApplicationInfo item=mAppList.get(position);
                switch (index) {
                    case 0:
                        // open
                        open(item);
                        break;
                    case 1:
                        // delete(item);
                        mAppList.remove(position);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });

        // set SwipeListener
        mListView.setOnSwipeListener(new PullToRefreshSwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        // other setting
        // listView.setCloseInterpolator(new BounceInterpolator());

        // test item long click
        mListView.setOnItemLongClickListener(new AbsListView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent,View view,int position,long id) {
                Toast.makeText(getApplicationContext(),position+" long click",Toast.LENGTH_SHORT)
                     .show();
                return false;
            }
        });
```