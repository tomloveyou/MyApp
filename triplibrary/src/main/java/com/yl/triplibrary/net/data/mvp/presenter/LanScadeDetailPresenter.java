package com.yl.triplibrary.net.data.mvp.presenter;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.orhanobut.logger.Logger;
import com.standards.library.base.BasePresenter;
import com.standards.library.rx.CSubscriber;
import com.standards.library.rx.ErrorThrowable;
import com.yl.triplibrary.net.data.mvp.contract.HomeTripContract;
import com.yl.triplibrary.net.data.mvp.contract.LanScadeDeailContract;
import com.yl.triplibrary.net.data.mvp.module.HomeTripTab;
import com.yl.triplibrary.net.data.mvp.module.ImgInfoEntity;
import com.yl.triplibrary.net.data.mvp.module.LanScadeDetailBodyEntity;
import com.yl.triplibrary.net.data.mvp.module.LanScadeDetailEntity;
import com.yl.triplibrary.net.data.mvp.module.LanScadeDetailHeadEntity;
import com.yl.triplibrary.net.data.mvp.module.LanScadeRecomentEntity;

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

public class LanScadeDetailPresenter extends BasePresenter<LanScadeDeailContract.LanScadeDeailView> {
    public LanScadeDetailPresenter(Activity activity) {
        super(activity);
    }

    public LanScadeDetailPresenter(Fragment fragment) {
        super(fragment);
    }

    public void getLanScadeDeailData(String url) {
        addSubscribe(getData(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new CSubscriber<LanScadeDetailEntity>() {
                    @Override
                    public void onPrepare() {

                    }

                    @Override
                    public void onError(ErrorThrowable throwable) {

                        mView.showError(throwable);
                    }

                    @Override
                    public void onSuccess(LanScadeDetailEntity rankTripZones) {

                        mView.getLanScadeDeailData(rankTripZones);
                    }
                }));

    }

    private Observable<LanScadeDetailEntity> getData(String sourece_url) {
        return Observable.create(subscriber -> {
            LanScadeDetailEntity scadeDetailEntity = new LanScadeDetailEntity();
            try {
                Document doc = Jsoup.connect(sourece_url).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0").get();


                /*封装head数据*/
                String head_img = doc.select(".web980").select(".jingdian-head").select(".jingdian-cover").select("img").attr("src");
                String head_title = doc.select(".web980").select(".jingdian-head").select(".jingdian-title").select("span").select("i").text();
                String head_content = doc.select(".web980").select(".jingdian-des").text();
                LanScadeDetailHeadEntity headEntity = new LanScadeDetailHeadEntity(head_title, head_img, sourece_url, null, head_content);
                scadeDetailEntity.setHeadEntity(headEntity);
                /*封装body-map数据*/
                List<LanScadeDetailBodyEntity> detailBodyEntity = new ArrayList<>();
                LanScadeDetailBodyEntity bodyEntity = new LanScadeDetailBodyEntity();

                String body_img_map = doc.select(".web980").select(".jingdian-map").select(".map-location").select("img").attr("src");
                bodyEntity.setBody_title("景点位置");
                List<ImgInfoEntity> mapImg = new ArrayList<>();
                ImgInfoEntity infoEntity_map = new ImgInfoEntity(body_img_map, sourece_url, null);
                mapImg.add(infoEntity_map);
                bodyEntity.setImgInfoEntityList(mapImg);
                detailBodyEntity.add(bodyEntity);
                /*封住body——img数据*/

                LanScadeDetailBodyEntity bodyImgEntity = new LanScadeDetailBodyEntity();
                bodyImgEntity.setBody_title("最新图片");
                Elements body_img_info = doc.select(".web980").select(".jingdian-photo").select("p");
                List<ImgInfoEntity> body_info_Img = new ArrayList<>();
                for (Element element : body_img_info) {
                    String img_url = element.select(".lazy").attr("data-original");
                    ImgInfoEntity imgInfoEntity = new ImgInfoEntity(img_url, sourece_url, null);
                    body_info_Img.add(imgInfoEntity);

                }
                bodyImgEntity.setImgInfoEntityList(body_info_Img);
                detailBodyEntity.add(bodyImgEntity);
                scadeDetailEntity.setDetailBodyEntity(detailBodyEntity);
                /*封装推荐数据*/
                String foot_title = doc.select(".web980").select(".list-title").text();
                scadeDetailEntity.setFoot_recommnet_title(foot_title);
                List<LanScadeRecomentEntity> foot_data = new ArrayList<>();
                Elements foot_web_data = doc.select(".web980").select(".jingdian-line").select("ul").select("li");
                for (Element element : foot_web_data) {
                    String img_url = element.select(".wapleft").select("a").select(".lazy").attr("data-original");
                    String goto_url = element.select(".wapleft").select("a").attr("abs:href");
                    String title = element.select(".wapright").select(".waptitle").select("a").select("b").text();
                    String price = element.select(".wapright").select("p").select("b").select(".wapprice").text();
                    String other_desc = element.select(".wapright").select("p").text();
                    LanScadeRecomentEntity recomentEntity=new LanScadeRecomentEntity(img_url,sourece_url,goto_url,price, other_desc, title);
                    foot_data.add(recomentEntity);
                }
                scadeDetailEntity.setRecomentEntityList(foot_data);


            } catch (IOException e) {
                e.printStackTrace();
            }

            subscriber.onNext(scadeDetailEntity);
            subscriber.onCompleted();

        });
    }
}
