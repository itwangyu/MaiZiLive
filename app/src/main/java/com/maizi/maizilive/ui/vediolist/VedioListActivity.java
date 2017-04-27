package com.maizi.maizilive.ui.vediolist;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.maizi.maizilive.R;
import com.maizi.maizilive.adapter.VideoListAdapter;
import com.maizi.maizilive.bean.PDRoom;
import com.maizi.maizilive.mvp.MVPBaseActivity;
import com.maizi.maizilive.ui.room.RoomActivity;

import java.util.List;

import butterknife.BindView;

/**
 * MVPPlugin
 */

public class VedioListActivity extends MVPBaseActivity<VedioListContract.View, VedioListPresenter> implements VedioListContract.View {

    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    private VideoListAdapter adapter;

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }
    @Override
    public void init() {
        mPresenter.getVedioList();
        recycleview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void showVedioList(final List<PDRoom.Room> list) {
        if (adapter == null) {
            adapter = new VideoListAdapter(this, list);
            adapter.setOnItemClickListener(new VideoListAdapter.OnItemClickListener() {
                @Override
                public void onClick(int po) {
                    startActivity(new Intent(VedioListActivity.this, RoomActivity.class).putExtra("id",list.get(po).getId()));
                }
            });
            recycleview.setAdapter(adapter);
        } else {
            adapter.setData(list);
        }
    }

}
