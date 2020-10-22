package com.yl.threestonecoupon

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.Callback
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Progress
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.yl.threestonecoupon.ui.net.SignMD5Util
import java.util.*

class CouponMainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        OkGo.getInstance().init(application)
        setContentView(R.layout.activity_coupon_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val paraMap = TreeMap<String, String>()
            paraMap["pageId"] = "1"
            paraMap["pageSize"] = "20"
//        paraMap.put("keyWords", "男装");
            //        paraMap.put("keyWords", "男装");
            paraMap["nineCid"] = "1"
            paraMap["sign"] = SignMD5Util.getSignStr(paraMap)
            OkGo.get<String>("https://openapi.dataoke.com/api/goods/get-goods-list")
                    .params(paraMap)
                    .execute(object : StringCallback(){
                        override fun onSuccess(response: Response<String>?) {

                        }
                    })
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.coupon_main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}