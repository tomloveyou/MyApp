package com.yl.triplibrary.net.data.mvp.presenter;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.standards.library.base.BasePresenter;
import com.standards.library.rx.CSubscriber;
import com.standards.library.rx.ErrorThrowable;
import com.yl.triplibrary.net.data.mvp.contract.TripLineContract;
import com.yl.triplibrary.net.data.mvp.contract.TripStrategyContract;
import com.yl.triplibrary.net.data.mvp.module.ImgInfoEntity;
import com.yl.triplibrary.net.data.mvp.module.TripLineEntity;
import com.yl.triplibrary.net.data.mvp.module.TripStrategyEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TripStrategyPresenter extends BasePresenter<TripStrategyContract.TTripStrategyView> {
    private int current_page = 1;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public TripStrategyPresenter(Activity activity) {
        super(activity);
    }

    public TripStrategyPresenter(Fragment fragment) {
        super(fragment);
    }

    public void getLanScadeDeailData(boolean showloading) {
        String url_head = "http://m.dazijia.com/gonglue/taiguo?p=" + current_page;
        addSubscribe(getData(url_head).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new CSubscriber<List<TripStrategyEntity>>() {
                    @Override
                    public void onPrepare() {
                        if (showloading){
                            mView.showLoading();
                        }
                    }

                    @Override
                    public void onError(ErrorThrowable throwable) {
                        if (showloading){
                            mView.hideLoading();
                        }
                        mView.showError(throwable);
                    }

                    @Override
                    public void onSuccess(List<TripStrategyEntity> rankTripZones) {
                        if (showloading){
                            mView.hideLoading();
                        }
                        mView.getTripStrategyData(rankTripZones);
                    }
                }));

    }

    private Observable<List<TripStrategyEntity>> getData(String sourece_url) {
        return Observable.create(subscriber -> {
            List<TripStrategyEntity> body_info_Img = new ArrayList<>();
            try {
                Document doc = Jsoup.connect(sourece_url).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();
                Elements elements = doc.select(".web980").select(".gl-list").select(".gl-item").select("ul").select("li");
                for (Element element : elements) {
                    /*title解析封装*/
                    TripStrategyEntity imgInfoEntity = new TripStrategyEntity();
                    String title = element.select(".title").select("h3").text();
                    imgInfoEntity.setTitle(title);
                    /*封装图片数据*/
                    String goto_url=element.select("a").attr("abs:href");
                    Elements elements2 = element.select(".pic");
                    List<ImgInfoEntity>imgInfoEntityList=new ArrayList<>();
                    for (Element element3 : elements2){
                        String img_url = element3.select("img").attr("data-original");
                        ImgInfoEntity entity=new ImgInfoEntity(img_url, sourece_url, goto_url);
                        imgInfoEntityList.add(entity);
                    }
                    imgInfoEntity.setImgInfoEntityList(imgInfoEntityList);
                    /*基本信息*/
                    String time = element.select(".item-info").select(".after-25").text();
                    String user = element.select(".item-info").select(".after-25").text();
                    String read_count = element.select(".item-info").select("pubdate").text();
                    imgInfoEntity.setUserinfo(user);
                    imgInfoEntity.setReadinfo(read_count);
                    imgInfoEntity.setTime(time);
                    body_info_Img.add(imgInfoEntity);

                }
                if (body_info_Img.size() > 0) {
                    current_page++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            subscriber.onNext(body_info_Img);
            subscriber.onCompleted();

        });
    }
}
