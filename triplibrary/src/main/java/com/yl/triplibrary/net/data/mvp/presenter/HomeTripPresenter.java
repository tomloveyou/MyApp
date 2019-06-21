package com.yl.triplibrary.net.data.mvp.presenter;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.orhanobut.logger.Logger;
import com.standards.library.base.BasePresenter;
import com.standards.library.rx.CSubscriber;
import com.standards.library.rx.ErrorThrowable;
import com.yl.triplibrary.net.data.manager.entity.RankTripZone;
import com.yl.triplibrary.net.data.mvp.contract.HomeTripContract;
import com.yl.triplibrary.net.data.mvp.contract.RankTripContract;
import com.yl.triplibrary.net.data.mvp.module.HomeTripTab;

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

public class HomeTripPresenter extends BasePresenter<HomeTripContract.HomeTripView> {
    public HomeTripPresenter(Activity activity) {
        super(activity);
    }

    public HomeTripPresenter(Fragment fragment) {
        super(fragment);
    }
    public void getHomeTripData() {
        addSubscribe(getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new CSubscriber<List<HomeTripTab>>() {
                    @Override
                    public void onPrepare() {

                    }

                    @Override
                    public void onError(ErrorThrowable throwable) {

                        mView.showError(throwable);
                    }

                    @Override
                    public void onSuccess(List<HomeTripTab> rankTripZones) {

                        mView.getHomeTripTrip(rankTripZones);
                    }
                }));

    }
    private Observable<List<HomeTripTab>> getData() {
        return Observable.create(subscriber -> {
            List<HomeTripTab> da = new ArrayList<>();
            String url_head = "http://m.dazijia.com/jingdian/taiguo.html";
            try {
                Document doc = Jsoup.connect(url_head).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();

                Elements elements = doc.select(".web980").select(".mdd-nav").select("ul").select("li");
                for (Element element : elements) {
                    String title = element.select("a").text();
                    String url = element.select("a").attr("abs:href");
                    HomeTripTab rankTripZone = new HomeTripTab(title,url);
                    da.add(rankTripZone);

                }
                Logger.d(da.size());

            } catch (IOException e) {
                e.printStackTrace();
            }

            subscriber.onNext(da);
            subscriber.onCompleted();

        });
    }
}
