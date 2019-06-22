package com.yl.triplibrary.net.data.mvp.presenter;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.standards.library.base.BasePresenter;
import com.standards.library.rx.CSubscriber;
import com.standards.library.rx.ErrorThrowable;
import com.yl.triplibrary.net.data.mvp.contract.TripFootPrintContract;
import com.yl.triplibrary.net.data.mvp.contract.TripFootPrintDetailContract;
import com.yl.triplibrary.net.data.mvp.module.ImgInfoEntity;
import com.yl.triplibrary.net.data.mvp.module.LanScadeInfoEntity;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintDetaiEntity;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintEntity;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintItemEndEntity;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintItemHeadEntity;

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

public class TripFootPrintDetailPresenter extends BasePresenter<TripFootPrintDetailContract.TripFootPrintDetailView> {


    public TripFootPrintDetailPresenter(Activity activity) {
        super(activity);
    }

    public TripFootPrintDetailPresenter(Fragment fragment) {
        super(fragment);
    }

    public void getLanScadeDeailData(boolean showloading,String url) {
        addSubscribe(getData(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new CSubscriber<TripFootPrintDetaiEntity>() {
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
                    public void onSuccess(TripFootPrintDetaiEntity rankTripZones) {
                        if (showloading){
                            mView.hideLoading();
                        }
                        mView.getFootPrinteDetailData(rankTripZones);
                    }
                }));

    }

    private Observable<TripFootPrintDetaiEntity> getData(String sourece_url) {
        return Observable.create(subscriber -> {
            TripFootPrintDetaiEntity tripFootPrintDetaiEntity=new TripFootPrintDetaiEntity();
            try {
                Document doc = Jsoup.connect(sourece_url).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();
                /*封装详情头部个人信息数据*/
                Elements elements = doc.select(".web980").select(".zuji-item");
                String user_name = elements.get(0).select(".trip-summary").select("a").select(".name").text();
                String user_avator = elements.get(0).select(".trip-summary").select("a").select(".avatar-20").select("img").attr("abs:src");
                String read_count = elements.get(0).select(".trip-summary").select(".view").text();
                String goto_url=elements.get(0).select(".trip-summary").select("a").attr("abs:href");
                TripFootPrintItemHeadEntity headEntity=new TripFootPrintItemHeadEntity(user_name,read_count,user_avator,sourece_url,goto_url);

                tripFootPrintDetaiEntity.setHeadEntity(headEntity);
                /*封装conent数据*/
                Elements elements2 = doc.select(".web980").select(".gl-body");
                String content=elements2.text();
                tripFootPrintDetaiEntity.setContent(content);
                /*f封装图片*/
                Elements elements3 = doc.select(".web980").select(".gl-body").select(".zuji-pic").select("p");
                List<ImgInfoEntity>imgInfoEntityList=new ArrayList<>();
                for (Element element : elements3){
                    String img_url = element.select("img").attr("src");
                    if ("".equals(img_url)){
                        img_url= element.select("img").attr("data-original");
                    }
                    ImgInfoEntity entity=new ImgInfoEntity(img_url, sourece_url, goto_url);
                    imgInfoEntityList.add(entity);
                }
                tripFootPrintDetaiEntity.setImgInfoEntityList(imgInfoEntityList);
                /*封装景区信息数据*/
                Elements elements4 = doc.select(".web980").select(".gl-body").select(".gl-start");
                String landscade_goto_url=elements4.select("a").attr("abs:href");
                String landscade_img_url=elements4.select("a").select(".start-img").select("img").attr("abs:src");
                String landscade_name=elements4.select("a").select(".start-info").select(".in-t").text();
                String landscade_content=elements4.select("a").select(".start-info").select("p").text();
                String landscade_time=elements2.select(".zuji-info").select(".zuji-time").text();
                String landscade_detail_address=elements4.select("a").select(".start-info").select("p").text();
                String landscade_simple_address=elements2.select(".zuji-info").select(".zuji-mdd").select("a").text();
                String landscade_simple_address_goto_url=elements2.select(".zuji-info").select(".zuji-mdd").select("a").attr("abs:href");
                LanScadeInfoEntity lanScadeInfoEntity=new LanScadeInfoEntity(landscade_img_url,sourece_url,landscade_goto_url,landscade_name,landscade_content,landscade_time,landscade_detail_address,landscade_simple_address,landscade_simple_address_goto_url);
                tripFootPrintDetaiEntity.setLanScadeInfoEntity(lanScadeInfoEntity);
                /*封装走过的足迹数据*/
                Elements elements5 = doc.select(".web980").select(".gl-relate").select(".zuji-item").select("li");
                List<TripFootPrintEntity>footPrintEntities=new ArrayList<>();
                for (Element element : elements5) {
                    TripFootPrintEntity footPrintEntity=new TripFootPrintEntity();
                    String user_name2 = element.select(".trip-summary").select(".name").text();
                    String user_avator2 = element.select(".trip-summary").select(".u-avatar").select("img").attr("abs:src");
                    String read_count2 = element.select(".trip-summary").select(".view").text();
                    String goto_url2=element.select("a").get(1).attr("abs:href");
                    String content2=element.select("a").select(".txt").text();
                    String address_info2=element.select(".item-info").select(".after-25").text();
                    String time2=element.select(".item-info").select(".pubdate").text();
                    ImgInfoEntity avator_bean2=new ImgInfoEntity(user_avator2,sourece_url,null);
                    TripFootPrintItemHeadEntity headEntity2=new TripFootPrintItemHeadEntity(user_name2,avator_bean2,read_count2);
                    TripFootPrintItemEndEntity endEntity2=new TripFootPrintItemEndEntity(address_info2,address_info2,time2);
                    footPrintEntity.setPrintItemHeadInfoEntity(headEntity2);
                    footPrintEntity.setContent(content2);
                    footPrintEntity.setPrintItemEndEntity(endEntity2);
                    footPrintEntity.setGoto_url(goto_url2);
                    Elements elements6 = element.select("a").select(".pic");
                    List<ImgInfoEntity>imgInfoEntityList2=new ArrayList<>();
                    for (Element element3 : elements6){
                        String img_url = element3.select("img").attr("data-original");
                        ImgInfoEntity entity=new ImgInfoEntity(img_url, sourece_url, goto_url2);
                        imgInfoEntityList2.add(entity);
                    }
                    footPrintEntity.setImgInfoEntityList(imgInfoEntityList2);
                    footPrintEntities.add(footPrintEntity);

                }
                tripFootPrintDetaiEntity.setFootPrintEntityList(footPrintEntities);

            } catch (IOException e) {
                e.printStackTrace();
            }
            subscriber.onNext(tripFootPrintDetaiEntity);
            subscriber.onCompleted();

        });
    }
}
