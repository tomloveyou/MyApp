package com.yl.triplibrary.net.data.mvp.presenter;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.orhanobut.logger.Logger;
import com.standards.library.base.BasePresenter;
import com.standards.library.rx.CSubscriber;
import com.standards.library.rx.ErrorThrowable;
import com.yl.triplibrary.net.data.manager.entity.RankTripZone;
import com.yl.triplibrary.net.data.mvp.contract.RankTripContract;

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

public class RankTripPresenter extends BasePresenter<RankTripContract.RankTripView> {
    private int current_page = 1;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public RankTripPresenter(Activity activity) {
        super(activity);
    }

    public RankTripPresenter(Fragment fragment) {
        super(fragment);
    }

    public void getRankTripData(String araa_code,boolean showloading) {
        String url_head = "http://m.dazijia.com/jingdian/" +araa_code+
                "?p=" + current_page;
        addSubscribe(getData(url_head).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new CSubscriber<List<RankTripZone>>() {
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
                    public void onSuccess(List<RankTripZone> rankTripZones) {
                        if (showloading){
                            mView.hideLoading();
                        }
                        mView.getRankTrip(rankTripZones);
                    }
                }));

    }

    private Observable<List<RankTripZone>> getData(String html_url) {
        return Observable.create(subscriber -> {
            List<RankTripZone> da = new ArrayList<>();
            String url_head = html_url+"?p=" + current_page;
            try {
                Document doc = Jsoup.connect(url_head).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();

                Elements elements = doc.select(".web980").select(".jingdian-list").select(".jingdian-item").select("ul").select("li");
                Logger.d(elements.toString());
                for (Element element : elements) {
                    String title = element.select(".middle").select("h3").select("a").text();
                    String content = element.select(".middle").select("p").text();
                    String thumb_img = element.select(".pic").select(".large").select("img").attr("src");
                    String original_img = element.select(".pic").select(".large").select("img").attr("data-original");
                    String url = element.select(".pic").select("a").attr("abs:href");
                    RankTripZone rankTripZone = new RankTripZone(title, content, thumb_img, original_img, url, url_head);
                    da.add(rankTripZone);

                }

                if (da.size() > 0) {
                    current_page++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            subscriber.onNext(da);
            subscriber.onCompleted();

        });
    }
}
