package com.yl.triplibrary.net.data.manager;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.standards.library.listview.manager.BaseGroupListManager;
import com.standards.library.model.ListData;
import com.yl.triplibrary.net.data.manager.entity.RankTripZone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import rx.Observable;

public class LandScadDataManager extends BaseGroupListManager<RankTripZone> {
    @Override
    protected Observable<ListData<RankTripZone>> getData(Context context) {
        return getData().flatMap(this::translateToListData);


    }

    private Observable<ListData<RankTripZone>> translateToListData(List<RankTripZone> eventheadBean) {
        ListData<RankTripZone> listData = new ListData();
        listData.list = eventheadBean;
        return Observable.just(listData);
    }


    private Observable<List<RankTripZone>> getData() {
        return Observable.create(subscriber -> {
            List<RankTripZone> da = new ArrayList<>();
            String url_head="http://m.dazijia.com/jingdian/taiguo.html";
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
                    RankTripZone rankTripZone = new RankTripZone(title, content, thumb_img, original_img, url,url_head);
                    da.add(rankTripZone);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            subscriber.onNext(da);
            subscriber.onCompleted();

        });
    }

}

