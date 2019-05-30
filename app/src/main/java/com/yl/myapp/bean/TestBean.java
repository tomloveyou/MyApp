package com.yl.myapp.bean;

import java.io.Serializable;
import java.util.List;

public class TestBean  implements Serializable {

    /**
     * pic_s210 : http://business.cdn.qianqian.com/qianqian/pic/bos_client_dea655f4be544132fb0b5899f063d82e.jpg
     * bg_pic : http://business0.qianqian.com/qianqian/file/5bfe4ebfac057_381.png
     * web_url :
     * color : 0x5B9400
     * pic_s444 : http://hiphotos.qianqian.com/ting/pic/item/78310a55b319ebc4845c84eb8026cffc1e17169f.jpg
     * name : 新歌榜
     * count : 4
     * comment : 该榜单是根据千千音乐平台歌曲每日播放量自动生成的数据榜单，统计范围为近期发行的歌曲，每日更新一次
     * type : 1
     * pic_s192 : http://business.cdn.qianqian.com/qianqian/pic/bos_client_9a4fbbbfa50203aaa9e69bf189c6a45b.jpg
     * content : [{"all_rate":"flac,320,128,224,96","song_id":"610243977","rank_change":"0","biaoshi":"first,lossless,vip,perm-1","author":"林志玲","album_id":"610243975","pic_small":"http://qukufile2.qianqian.com/data2/pic/60aade65b18d9769379f3a64b0f1fe85/610246816/610246816.jpg@s_1,w_90,h_90","title":"You and me","album_title":"You and me"},{"all_rate":"flac,320,128,224,96","song_id":"608296304","rank_change":"0","biaoshi":"first,lossless,perm-1","author":"许嵩","album_id":"608296302","pic_small":"http://qukufile2.qianqian.com/data2/pic/b447319eb0064f88f6d15f278cdc4df1/608316740/608316740.jpg@s_1,w_90,h_90","title":"《绝代风华》（天下3十周年主题曲）","album_title":"绝代风华（天下3十周年主题曲）"},{"all_rate":"flac,320,128,224,96","song_id":"610370676","rank_change":"1","biaoshi":"lossless,vip,perm-1","author":"樊桐舟","album_id":"610370673","pic_small":"http://qukufile2.qianqian.com/data2/pic/45463a960747fd1d0227ee7dd70e9e96/610370690/610370690.jpg@s_1,w_90,h_90","title":"又是一年寒风起","album_title":"又是一年寒风起"},{"all_rate":"flac,320,128,224,96","song_id":"610271235","rank_change":"-1","biaoshi":"first,lossless,vip,perm-1","author":"南征北战NZBZ","album_id":"610271233","pic_small":"http://qukufile2.qianqian.com/data2/pic/8e5daa1fd710cf706abab59959572c85/610271242/610271242.jpg@s_1,w_90,h_90","title":"沉默的誓言","album_title":"沉默的誓言"}]
     * pic_s260 : http://hiphotos.qianqian.com/ting/pic/item/e850352ac65c1038cb0f3cb0b0119313b07e894b.jpg
     */

    private String pic_s210;
    private String bg_pic;
    private String web_url;
    private String color;
    private String pic_s444;
    private String name;
    private int count;
    private String comment;
    private int type;
    private String pic_s192;
    private String pic_s260;
    private List<ContentBean> content;

    public String getPic_s210() {
        return pic_s210;
    }

    public void setPic_s210(String pic_s210) {
        this.pic_s210 = pic_s210;
    }

    public String getBg_pic() {
        return bg_pic;
    }

    public void setBg_pic(String bg_pic) {
        this.bg_pic = bg_pic;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPic_s444() {
        return pic_s444;
    }

    public void setPic_s444(String pic_s444) {
        this.pic_s444 = pic_s444;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPic_s192() {
        return pic_s192;
    }

    public void setPic_s192(String pic_s192) {
        this.pic_s192 = pic_s192;
    }

    public String getPic_s260() {
        return pic_s260;
    }

    public void setPic_s260(String pic_s260) {
        this.pic_s260 = pic_s260;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }


}
