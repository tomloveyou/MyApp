package com.yl.triplibrary.net.data.mvp.presenter;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.standards.library.base.BasePresenter;
import com.standards.library.rx.CSubscriber;
import com.standards.library.rx.ErrorThrowable;
import com.yl.triplibrary.net.data.mvp.contract.LanScadeDeailContract;
import com.yl.triplibrary.net.data.mvp.contract.TripLineContract;
import com.yl.triplibrary.net.data.mvp.module.ImgInfoEntity;
import com.yl.triplibrary.net.data.mvp.module.LanScadeDetailBodyEntity;
import com.yl.triplibrary.net.data.mvp.module.LanScadeDetailEntity;
import com.yl.triplibrary.net.data.mvp.module.LanScadeDetailHeadEntity;
import com.yl.triplibrary.net.data.mvp.module.LanScadeRecomentEntity;
import com.yl.triplibrary.net.data.mvp.module.TripLineEntity;

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

public class TripLinePresenter extends BasePresenter<TripLineContract.TripLineView> {
    private int current_page = 1;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public TripLinePresenter(Activity activity) {
        super(activity);
    }

    public TripLinePresenter(Fragment fragment) {
        super(fragment);
    }

    public void getLanScadeDeailData(boolean isSearch,String area_code,boolean showloading) {
        String url_head = "http://m.dazijia.com" +area_code+
                "?p=" + current_page;
        addSubscribe((isSearch?getDataBySingleSearch(url_head):getData(url_head)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new CSubscriber<List<TripLineEntity>>() {
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
                    public void onSuccess(List<TripLineEntity> rankTripZones) {
                        if (showloading){
                            mView.hideLoading();
                        }
                        mView.getTripLineData(rankTripZones);
                    }
                }));

    }
    private Observable<List<TripLineEntity>> getDataBySingleSearch(String sourece_url) {
        return Observable.create(subscriber -> {
            List<TripLineEntity> body_info_Img = new ArrayList<>();
            try {
                Document doc = Jsoup.connect(sourece_url).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();
                Elements elements = doc.select(".web980").select(".list").select("ul").select("li");
                for (Element element : elements) {
                    Elements wap_left_Element=null;
                    if (element.select(".wap-left").size()==0){
                        wap_left_Element=   element.select(".wapleft");
                    }else {
                        wap_left_Element=   element.select(".wap-left");
                    }
                    String img_url = wap_left_Element.select("a").select("img").attr("data-original");
                    String areal = wap_left_Element.select(".chufadi").text();
                    String people_count= wap_left_Element.select(".yibaoming").text();
                    Elements wap_right_Element=null;
                    if (element.select(".wap-right").size()==0){
                        wap_right_Element=   element.select(".wapright");
                    }else {
                        wap_right_Element=   element.select(".wap-right");
                    }
                    String price = wap_right_Element.select(".wapprice").text();
                    String title = wap_right_Element.select("a").text();
                    String goto_url=wap_right_Element.select("a").attr("abs:href");


                    TripLineEntity imgInfoEntity = new TripLineEntity(img_url,sourece_url,goto_url,title, areal, people_count,price);
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
    private Observable<List<TripLineEntity>> getData(String sourece_url) {
        return Observable.create(subscriber -> {
            List<TripLineEntity> body_info_Img = new ArrayList<>();
            try {
                Document doc = Jsoup.connect(sourece_url).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();
                Elements elements = doc.select(".web980").select(".list").select("ul").select("li");
                for (Element element : elements) {
                    String img_url = element.select(".bigimg-list").select(".bigimg-head").select(".bigimg-head-img").select(".img-cov").select("a").select("img").attr("src");
                    String price = element.select(".bigimg-list").select(".bigimg-head").select(".price").text();
                    String areal = element.select(".bigimg-list").select(".bigimg-head").select(".line-cfd").text();
                    String title = element.select(".bigimg-list").select(".bigimg-dec").select(".line2hid").select("a").text();
                    String people_count= element.select(".bigimg-list").select(".bigimg-dec").select(".bigimg-info").select(".right").select(".after-25").text();
                    String goto_url=element.select(".bigimg-list").select(".bigimg-dec").select(".line2hid").select("a").attr("abs:href");
                    TripLineEntity imgInfoEntity = new TripLineEntity(img_url,sourece_url,goto_url,title, areal,people_count, price);
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
