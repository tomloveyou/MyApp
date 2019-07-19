package com.yl.triplibrary.net.data.mvp.presenter;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.standards.library.base.BasePresenter;
import com.standards.library.rx.CSubscriber;
import com.standards.library.rx.ErrorThrowable;
import com.yl.triplibrary.net.data.mvp.contract.TripFootPrintContract;
import com.yl.triplibrary.net.data.mvp.contract.TripWenDaContract;
import com.yl.triplibrary.net.data.mvp.module.ImgInfoEntity;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintEntity;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintItemEndEntity;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintItemHeadEntity;
import com.yl.triplibrary.net.data.mvp.module.TripWenDaEntity;
import com.yl.triplibrary.net.data.mvp.module.UserInfoEntity;

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

public class TripWenDaPresenter extends BasePresenter<TripWenDaContract.TripWenDaView> {
    private int current_page = 1;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public TripWenDaPresenter(Activity activity) {
        super(activity);
    }

    public TripWenDaPresenter(Fragment fragment) {
        super(fragment);
    }

    public void getLanScadeDeailData(String area_code,boolean showloading) {
        String url_head = "http://m.dazijia.com/wenda/" +area_code+
                "?p=" + current_page;
        addSubscribe(getData(url_head).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new CSubscriber<List<TripWenDaEntity>>() {
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
                    public void onSuccess(List<TripWenDaEntity> rankTripZones) {
                        if (showloading){
                            mView.hideLoading();
                        }
                        mView.getWenDaData(rankTripZones);
                    }
                }));

    }

    private Observable<List<TripWenDaEntity>> getData(String sourece_url) {
        return Observable.create(subscriber -> {
            List<TripWenDaEntity> body_info_Img = new ArrayList<>();
            try {
                Document doc = Jsoup.connect(sourece_url).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();
                Elements elements = doc.select(".web980").select(".ask-body").select(".ask-list").select("ul").select("li");
                for (Element element : elements) {
                    TripWenDaEntity tripWenDaEntity=new TripWenDaEntity();
                    String user_name = element.select(".ask-item").select(".ask-ans").select(".use").select("span").text();
                    String user_avator = element.select(".ask-item").select(".ask-ans").select(".use").select("img").attr("abs:src");
                    String goto_url=element.select(".ask-item").select("a").attr("abs:href");
                    String title=element.select(".ask-item").select("a").select("h2").text();
                    String content=element.select(".ask-item").select(".ask-ans").select(".con").select(".txt").text();
                    String other_desc_info=element.select(".ask-item").select(".ask-info").select("a").select(".ask-mdd").text();
                    String address_info=element.select(".ask-item").select(".ask-info").select(".ask-num").text();
                    tripWenDaEntity.setUserInfoEntity(new UserInfoEntity(user_name,user_avator,sourece_url,goto_url));
                    tripWenDaEntity.setAddress(address_info);
                    tripWenDaEntity.setContent(content);
                    tripWenDaEntity.setAddress(address_info);
                    tripWenDaEntity.setTitle(title);
                    tripWenDaEntity.setOther_desc(other_desc_info);
                    body_info_Img.add(tripWenDaEntity);

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
