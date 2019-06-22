package com.yl.myapp.bean;

import java.io.Serializable;
import java.util.List;

public class LoginSucessInfoBean extends UserInfoBean implements Serializable{
    private List<JobBean>jobs;

    public List<JobBean> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobBean> jobs) {
        this.jobs = jobs;
    }
}
