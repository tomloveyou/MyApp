package com.yl.triplibrary.net.data.mvp.presenter;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.orhanobut.logger.Logger;
import com.standards.library.base.BasePresenter;
import com.standards.library.rx.CSubscriber;
import com.standards.library.rx.ErrorThrowable;
import com.yl.triplibrary.net.data.mvp.contract.HomeTripContract;
import com.yl.triplibrary.net.data.mvp.contract.TripHomeMainContract;
import com.yl.triplibrary.net.data.mvp.module.HomeTripTab;
import com.yl.triplibrary.net.data.mvp.module.ImaInfoTitleEntity;
import com.yl.triplibrary.net.data.mvp.module.TripHomeMainEntity;
import com.yl.triplibrary.net.data.mvp.module.TripHomeRecommnetEntity;
import com.yl.triplibrary.net.data.mvp.module.TripHomeSortConoditionEntity;

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

public class TripHomeMainPresenter extends BasePresenter<TripHomeMainContract.TripHomeMainView> {
    public TripHomeMainPresenter(Activity activity) {
        super(activity);
    }

    public TripHomeMainPresenter(Fragment fragment) {
        super(fragment);
    }

    public void getHomeTripData() {
        addSubscribe(getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new CSubscriber<TripHomeMainEntity>() {
                    @Override
                    public void onPrepare() {

                    }

                    @Override
                    public void onError(ErrorThrowable throwable) {

                        mView.showError(throwable);
                    }

                    @Override
                    public void onSuccess(TripHomeMainEntity rankTripZones) {

                        mView.getTripHomeMain(rankTripZones);
                    }
                }));

    }

    private Observable<TripHomeMainEntity> getData() {
        return Observable.create(subscriber -> {
            TripHomeMainEntity mainEntity = new TripHomeMainEntity();
            String url_head = "http://m.dazijia.com";
            try {
                Document doc = Jsoup.connect(url_head).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();

                Elements elements = doc.select(".web980").select(".swipslider").select("ul").select("li");
                /*解析封装banner数据*/
                List<ImaInfoTitleEntity> banners_list = new ArrayList<>();
                for (Element element : elements) {
                    String title = element.select("a").select(".title").text();
                    String goto_url = element.select("a").attr("abs:href");
                    String img_url = element.select("a").select("img").attr("abs:src");
                    ImaInfoTitleEntity imaInfoTitleEntity = new ImaInfoTitleEntity(title, img_url, url_head, goto_url);
                    banners_list.add(imaInfoTitleEntity);

                }
                mainEntity.setBanners_list(banners_list);
                /*解析封装九空格数据*/
                Elements menu_elements = doc.select(".web980").select(".menu_home").select("ul").select("li");
                List<ImaInfoTitleEntity> grid_buttons_list = new ArrayList<>();
                for (Element element : menu_elements) {
                    String title = element.select("a").text();
                    String goto_url = element.select("a").attr("abs:href");
                    String img_url = element.select("a").select("img").attr("abs:src");
                    ImaInfoTitleEntity imaInfoTitleEntity = new ImaInfoTitleEntity(title, img_url, url_head, goto_url);
                    grid_buttons_list.add(imaInfoTitleEntity);

                }
                mainEntity.setGrid_buttons_list(grid_buttons_list);
                /*解析封装分类搜索数据*/
                Elements sort_elements = doc.select(".web980").select(".list-filtrate").select(".sort");
                TripHomeSortConoditionEntity sortConoditionEntity=new TripHomeSortConoditionEntity();
                List<List<ImaInfoTitleEntity>>sort_data=new ArrayList<>();
                for (Element element : sort_elements) {
                    List<ImaInfoTitleEntity> entityList=new ArrayList<>();
                    Elements sort_inner_elements = element.select("ul").select("li");
                    for (Element inner_element : sort_inner_elements) {
                        String title = inner_element.select("a").text();
                        String goto_url = inner_element.select("a").attr("abs:href");
                        ImaInfoTitleEntity imaInfoTitleEntity = new ImaInfoTitleEntity(title, null, null, goto_url);
                        entityList.add(imaInfoTitleEntity);

                    }
                    sort_data.add(entityList);
                }
                sortConoditionEntity.setStart_area(sort_data.get(0));
                sortConoditionEntity.setStart_month(sort_data.get(1));
                sortConoditionEntity.setEnd_area(sort_data.get(2));
                mainEntity.setConoditionEntity(sortConoditionEntity);
                /*解析封装推荐数据*/
                Elements recomment_elements = doc.select(".web980").select(".list").select("ul").select("li");
                List<TripHomeRecommnetEntity>recomment_list=new ArrayList<>();
                for (Element element : recomment_elements) {

                    String title = element.select(".bigimg-dec").select(".line2hid").select("a").text();
                    String goto_url = element.select(".bigimg-dec").select(".line2hid").select("a").attr("abs:href");
                    String time_info = element.select(".bigimg-dec").select(".bigimg-info").select(".left").text();
                    String people_info = element.select(".bigimg-dec").select(".bigimg-info").select(".right").text();
                    String price_info = element.select(".bigimg-head").select(".price").text();
                    String start_area_info = element.select(".bigimg-head").select(".line-cfd").text();
                    String buyed_info = element.select(".bigimg-head").select(".line-zrs").text();
                    String img_url = element.select(".bigimg-head").select(".bigimg-head-img").select(".img-cov").select("a").select("img").attr("abs:src");
                    TripHomeRecommnetEntity recommnetEntity=new TripHomeRecommnetEntity(title,time_info,people_info,price_info,start_area_info,buyed_info,img_url,url_head,goto_url);
                    recomment_list.add(recommnetEntity);

                }
                mainEntity.setRecomment_list(recomment_list);
            } catch (IOException e) {
                e.printStackTrace();
            }

            subscriber.onNext(mainEntity);
            subscriber.onCompleted();

        });
    }
}
