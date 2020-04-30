package com.yl.markremember.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yl.markremember.R
import com.yl.markremember.ui.activity.ChangeAppearanceActivity
import com.yl.markremember.ui.activity.TabControlActivity
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        civ_fuction_model.setOnClickListener {
            startActivity(Intent(activity,TabControlActivity::class.java))
        }
        civ_change_appearance.setOnClickListener {
            startActivity(Intent(activity,ChangeAppearanceActivity::class.java))
        }
    }
}