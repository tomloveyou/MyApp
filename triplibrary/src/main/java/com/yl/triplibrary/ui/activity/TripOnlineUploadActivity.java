package com.yl.triplibrary.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.common.Zone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.util.Auth;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.standards.library.adapter.BaseQuickAdapter;
import com.standards.library.base.BaseTitleBarActivity;
import com.yl.triplibrary.R;
import com.yl.triplibrary.net.data.mvp.contract.TripLineContract;
import com.yl.triplibrary.net.data.mvp.module.TripLineEntity;
import com.yl.triplibrary.net.data.mvp.presenter.TripLinePresenter;
import com.yl.triplibrary.ui.activity.adapter.TripLineAdapter;

import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;

public class TripOnlineUploadActivity extends BaseTitleBarActivity<TripLinePresenter> implements TripLineContract.TripLineView {
    private RecyclerView myRecyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private TripLineAdapter tripAdapter2;
    private List<TripLineEntity> da = new ArrayList<>();
    private String area_code = "quanguo.html";
    private String area_name = "出发地";
    private String upToken = null;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_refresh_recycleview;
    }

    @Override
    public TripLinePresenter getPresenter() {
        return new TripLinePresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_submit) {
            DownloadImg(da.get(0));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void DownloadImg(TripLineEntity item) {
        GlideUrl cookie = new GlideUrl(item.getImg_url(), new Headers() {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> head = new HashMap<>();
                head.put("Referer", item.getSource_url());
                return head;
            }
        });
        Configuration config = new Configuration.Builder()
                .zone(FixedZone.zone0)
                .build();
        UploadManager uploadManager = new UploadManager(config);
        Glide.with(TripOnlineUploadActivity.this).load(cookie).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {

                uploadManager.put(resource, System.currentTimeMillis() + ".png", upToken,
                        new UpCompletionHandler() {
                            @Override
                            public void complete(String key, ResponseInfo info, JSONObject res) {
                                //res包含hash、key等信息，具体字段取决于上传策略的设置
                                if (info.isOK()) {
                                    Log.i("qiniu", "Upload Success");
                                   // subscriber.onNext(key);
                                } else {
                                    Log.i("qiniu", "Upload Fail");
                                  //  subscriber.onNext(new Throwable(res.toString()));
                                    //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                                }

                            }
                        }, null);
            }
        });
    }


    @Override
    public void init() {
        String accessKey = "QNtFCe2kP7ZFevvYt3ek8grJeCh6d8CgepsCGcRi";
        String secretKey = "SAAdBHNLr_aTT-XXNC437vwUzGTiw_jJvOuq8oS3";
        String bucket = "yl";
        Auth auth = Auth.create(accessKey, secretKey);
        upToken = auth.uploadToken(bucket);

        area_code = "/" + area_code;
        setTitle("从" + area_name + "出发");
        myRecyclerView = (RecyclerView) findView(R.id.my_recycler_view);
        smartRefreshLayout = findView(R.id.refreshLayout);
        tripAdapter2 = new TripLineAdapter(da);
        LinearLayoutManager manager = new LinearLayoutManager(TripOnlineUploadActivity.this);
        manager.setOrientation(RecyclerView.VERTICAL);
        myRecyclerView.setLayoutManager(manager);
        tripAdapter2.bindToRecyclerView(myRecyclerView);
        mPresenter.getLanScadeDeailData(true, area_code, true);
    }

    @Override
    public void setListener() {
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getLanScadeDeailData(true, area_code, false);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.setCurrent_page(0);
                mPresenter.getLanScadeDeailData(true, area_code, false);
            }
        });
        tripAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(TripOnlineUploadActivity.this, TripLineDetailAcitivity.class);
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", tripAdapter2.getItem(position).getGoto_ur());
                    intent.putExtras(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });
    }


    @Override
    public void getTripLineData(List<TripLineEntity> data) {
        if (mPresenter.getCurrent_page() == 1) {
            tripAdapter2.setNewData(data);
            smartRefreshLayout.finishRefresh();
        } else {
            tripAdapter2.addData(data);
            smartRefreshLayout.finishLoadmore();
        }
    }
}
