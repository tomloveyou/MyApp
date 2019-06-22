package com.yl.triplibrary.net.data.mvp.presenter;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.standards.library.base.BasePresenter;
import com.standards.library.rx.CSubscriber;
import com.standards.library.rx.ErrorThrowable;
import com.yl.triplibrary.net.data.mvp.contract.TripLineContract;
import com.yl.triplibrary.net.data.mvp.contract.TripLineDetailContract;
import com.yl.triplibrary.net.data.mvp.module.ImaInfoTitleEntity;
import com.yl.triplibrary.net.data.mvp.module.ImgInfoEntity;
import com.yl.triplibrary.net.data.mvp.module.PreBookInfoEntity;
import com.yl.triplibrary.net.data.mvp.module.TripClubEntity;
import com.yl.triplibrary.net.data.mvp.module.TripCurrentDayInfoEntity;
import com.yl.triplibrary.net.data.mvp.module.TripCurrentDayItemInfoEntity;
import com.yl.triplibrary.net.data.mvp.module.TripLineEntity;
import com.yl.triplibrary.net.data.mvp.module.TripLinelDetailMainEntity;
import com.yl.triplibrary.net.data.mvp.module.TripProfileEntity;
import com.yl.triplibrary.net.data.mvp.module.TripTitleContentImgEntity;

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

public class TripLineDetailPresenter extends BasePresenter<TripLineDetailContract.TripLineDetailView> {


    public TripLineDetailPresenter(Activity activity) {
        super(activity);
    }

    public TripLineDetailPresenter(Fragment fragment) {
        super(fragment);
    }

    public void getLanScadeDeailData(boolean showloading, String url) {

        addSubscribe(getData(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new CSubscriber<TripLinelDetailMainEntity>() {
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
                    public void onSuccess(TripLinelDetailMainEntity rankTripZones) {
                        if (showloading) {
                            mView.hideLoading();
                        }
                        mView.getTripLineDetailData(rankTripZones);
                    }
                }));

    }

    private Observable<TripLinelDetailMainEntity> getData(String sourece_url) {
        return Observable.create(subscriber -> {
            TripLinelDetailMainEntity detailMainEntity = new TripLinelDetailMainEntity();
            try {
                Document doc = Jsoup.connect(sourece_url).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();
                Elements elements = doc.select(".web980").select(".content");
                /*封装head数据*/
                String img_url = elements.select(".topimg").select("img").attr("abs:src");
                String title = elements.select(".topimg").select(".title").select("h1").text();
                ImaInfoTitleEntity imaInfoTitleEntity = new ImaInfoTitleEntity(title, img_url, sourece_url, null);
                detailMainEntity.setTitleHeadEntity(imaInfoTitleEntity);
                /*封装预定数据*/
                Elements book_elements = elements.select(".yuding").select(".ydinfo").select(".ln-inf-item");
                List<PreBookInfoEntity> bookInfoEntityList = new ArrayList<>();
                for (Element element : book_elements) {
                    String title_name = element.select("dt").text();
                    String title_value = element.select("dd").text();
                    PreBookInfoEntity bookInfoEntity = new PreBookInfoEntity(title_name, title_value);
                    bookInfoEntityList.add(bookInfoEntity);
                }
                detailMainEntity.setPreBookInfoEntityList(bookInfoEntityList);
                /*封装俱乐部数据*/
                Elements club_elements = elements.select("a").select(".jlbshow");
                String club_goto_url = elements.select("a").attr("abs:href");
                String club_icom_url = club_elements.select("img").attr("abs:src");
                String club_name = club_elements.select(".jlbname").text();
                String club_focu_cout = club_elements.select(".jlbinfo").select("span").get(0).text();
                String club_rute_count = club_elements.select(".jlbinfo").select("span").get(1).text();
                String club_people_count = club_elements.select(".jlbinfo").select("span").get(2).text();
                TripClubEntity tripClubEntity = new TripClubEntity(club_icom_url, sourece_url, club_goto_url, club_name, club_people_count, club_focu_cout, club_rute_count);
                detailMainEntity.setTripClubEntity(tripClubEntity);
                /*f封装路线介绍*/
                Elements travel_line_desc__elements = elements.select(".con").select(".line-intro").select(".line-con");
                String content = travel_line_desc__elements.text();
                detailMainEntity.setTrip_line_features(content);
                /*封装行程概括数据*/
                Elements travel_desc_elements = elements.select(".con").select(".line-trip").get(0).select(".line-con").select("dl");
                List<TripProfileEntity> tripProfileEntities = new ArrayList<>();
                for (Element element : travel_desc_elements) {
                    String title_name = element.select(".xcap").text();
                    String title_value = element.select(".can").text();
                    TripProfileEntity tripProfileEntity = new TripProfileEntity(title_name, title_value);
                    tripProfileEntities.add(tripProfileEntity);
                }
                detailMainEntity.setTripProfileEntities(tripProfileEntities);
                /*封装当天旅游情况数据*/
                Elements travel_current_day_elements = elements.select(".con").select(".line-trip").get(1).select(".line-con");
                Elements travel_current_day_elements2 = elements.select(".con").select(".line-trip").get(1).select(".travel-con");
                Elements travel_current_day_elements3 = elements.select(".con").select(".line-trip").get(1).select(".line-scen");
                List<TripCurrentDayItemInfoEntity> itemInfoEntityList = new ArrayList<>();
                for (int i = 0; i < travel_current_day_elements.size(); i += 4) {
                    if (i + 3 <= travel_current_day_elements.size()) {
                        TripCurrentDayItemInfoEntity itemInfoEntity = new TripCurrentDayItemInfoEntity();
                        String trip_current_day_info_title = travel_current_day_elements.get(i).text();
                        String trip_current_day_info_dining = travel_current_day_elements.get(i + 1).text();
                        String trip_current_day_info_accommodation = travel_current_day_elements.get(i + 2).text();
                        String trip_current_day_info_drive = travel_current_day_elements.get(i + 3).text();

                        TripCurrentDayInfoEntity currentDayInfoEntity = new TripCurrentDayInfoEntity(trip_current_day_info_title, trip_current_day_info_dining, trip_current_day_info_accommodation, trip_current_day_info_drive, null);
                        itemInfoEntity.setCurrentDayInfoEntity(currentDayInfoEntity);
                        itemInfoEntityList.add(itemInfoEntity);
                    }

                }
                for (int i = 0; i < itemInfoEntityList.size(); i++) {
                    TripCurrentDayItemInfoEntity itemInfoEntity = itemInfoEntityList.get(i);
                    TripCurrentDayInfoEntity tripCurrentDayInfoEntity = itemInfoEntity.getCurrentDayInfoEntity();
                    String trip_current_day_info_desc = travel_current_day_elements2.get(i).text();
                    tripCurrentDayInfoEntity.setTrip_current_day_info_desc(trip_current_day_info_desc);

                    List<TripTitleContentImgEntity> tripTitleContentImgEntityArrayList = new ArrayList<>();
                    Element element = travel_current_day_elements3.get(i);

                    String contentImg_title = element.select(".jingdian").select(".line-jingdian-title").text();
                    String contentImg_content = element.select(".jingdian").select(".line-jingdian-des").text();
                    Elements travel_current_day_elements5 = element.select(".jingdian").select(".jingdian-pic");
                    List<ImgInfoEntity> imgInfoEntityList = new ArrayList<>();
                    for (Element inner_element : travel_current_day_elements5) {

                        String contentImg_img_url = inner_element.select("img").attr("abs:data-original");
                        ImgInfoEntity imgInfoEntity = new ImgInfoEntity(contentImg_img_url, sourece_url, null);
                        imgInfoEntityList.add(imgInfoEntity);
                    }
                    TripTitleContentImgEntity contentImgEntity = new TripTitleContentImgEntity(contentImg_title, contentImg_content);
                    contentImgEntity.setImgInfoEntityList(imgInfoEntityList);
                    tripTitleContentImgEntityArrayList.add(contentImgEntity);


                    itemInfoEntity.setTripTitleContentImgEntity(tripTitleContentImgEntityArrayList);

                }
                detailMainEntity.setTripCurrentDayItemInfoEntityList(itemInfoEntityList);


                /*费用说明*/
                Elements travel_cost_elements = elements.select(".con").select(".line-cost").select(".line-con").select("p");
                List<String> cost_list_text = new ArrayList<>();
                for (Element element : travel_cost_elements) {
                    String text = element.text();
                    cost_list_text.add(text);
                }
                detailMainEntity.setCost_info_list(cost_list_text);
                /*路线须知*/
                Elements travel_line_tips_elements = elements.select(".con").select(".line-tips").select(".line-con").select("p");
                List<String> ine_tips_text = new ArrayList<>();
                for (Element element : travel_line_tips_elements) {
                    String text = element.text();
                    ine_tips_text.add(text);
                }
                detailMainEntity.setTip_info_list(ine_tips_text);
            } catch (IOException e) {
                e.printStackTrace();
            }
            subscriber.onNext(detailMainEntity);
            subscriber.onCompleted();

        });
    }
}
