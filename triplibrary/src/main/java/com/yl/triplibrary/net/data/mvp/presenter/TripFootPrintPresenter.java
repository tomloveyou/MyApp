package com.yl.triplibrary.net.data.mvp.presenter;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.standards.library.base.BasePresenter;
import com.standards.library.rx.CSubscriber;
import com.standards.library.rx.ErrorThrowable;
import com.yl.triplibrary.net.data.mvp.contract.LanScadeDeailContract;
import com.yl.triplibrary.net.data.mvp.contract.TripFootPrintContract;
import com.yl.triplibrary.net.data.mvp.contract.TripLineContract;
import com.yl.triplibrary.net.data.mvp.module.ImgInfoEntity;
import com.yl.triplibrary.net.data.mvp.module.LanScadeDetailBodyEntity;
import com.yl.triplibrary.net.data.mvp.module.LanScadeDetailEntity;
import com.yl.triplibrary.net.data.mvp.module.LanScadeDetailHeadEntity;
import com.yl.triplibrary.net.data.mvp.module.LanScadeRecomentEntity;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintEntity;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintItemEndEntity;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintItemHeadEntity;
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

public class TripFootPrintPresenter extends BasePresenter<TripFootPrintContract.TripFootPrintView> {
    private int current_page = 1;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public TripFootPrintPresenter(Activity activity) {
        super(activity);
    }

    public TripFootPrintPresenter(Fragment fragment) {
        super(fragment);
    }

    public void getLanScadeDeailData(boolean showloading) {
        String url_head = "http://m.dazijia.com/zuji/taiguo.html?p=" + current_page;
        addSubscribe(getData(url_head).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new CSubscriber<List<TripFootPrintEntity>>() {
                    @Override
                    public void onPrepare() {
                        if (showloading) {
                            mView.showLoading();
                        }
                    }

                    @Override
                    public void onError(ErrorThrowable throwable) {
                        if (showloading) {
                            mView.hideLoading();
                        }
                        mView.showError(throwable);
                    }

                    @Override
                    public void onSuccess(List<TripFootPrintEntity> rankTripZones) {
                        if (showloading) {
                            mView.hideLoading();
                        }
                        mView.getFootPrinteData(rankTripZones);
                    }
                }));

    }

    private Observable<List<TripFootPrintEntity>> getData(String sourece_url) {
        return Observable.create(subscriber -> {
            List<TripFootPrintEntity> body_info_Img = new ArrayList<>();
            try {
                Document doc = Jsoup.connect(sourece_url).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();
                Elements elements = doc.select(".web980").select(".zuji-list").select(".zuji-item").select("ul").select("li");
                for (Element element : elements) {
                    TripFootPrintEntity footPrintEntity = new TripFootPrintEntity();
                    String user_name = element.select(".trip-summary").select(".name").text();
                    String user_avator = element.select(".trip-summary").select(".u-avatar").select("img").attr("abs:src");
                    String read_count = element.select(".trip-summary").select(".view").text();
                    String goto_url = element.select(".trip-summary").select("a").attr("abs:href");
                    String content = element.select("a").select(".txt").text();
                    String address_info = element.select(".item-info").select(".after-25").text();
                    String time = element.select(".item-info").select(".pubdate").text();

                    ImgInfoEntity avator_bean = new ImgInfoEntity(user_avator, sourece_url, null);
                    TripFootPrintItemHeadEntity headEntity = new TripFootPrintItemHeadEntity(user_name, avator_bean, read_count);
                    TripFootPrintItemEndEntity endEntity = new TripFootPrintItemEndEntity(address_info, address_info, time);
                    footPrintEntity.setPrintItemHeadInfoEntity(headEntity);
                    footPrintEntity.setContent(content);
                    footPrintEntity.setPrintItemEndEntity(endEntity);
                    /*获取跳转路径接口*/
                    String goto_url2 = element.select("a").get(1).attr("abs:href");
                    footPrintEntity.setGoto_url(goto_url2);
                    Elements elements2 = element.select("a").select(".pic");
                    List<ImgInfoEntity> imgInfoEntityList = new ArrayList<>();
                    for (Element element3 : elements2) {
                        String img_url = element3.select("img").attr("data-original");
                        ImgInfoEntity entity = new ImgInfoEntity(img_url, sourece_url, goto_url2);
                        imgInfoEntityList.add(entity);
                    }
                    footPrintEntity.setImgInfoEntityList(imgInfoEntityList);
                    body_info_Img.add(footPrintEntity);

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
