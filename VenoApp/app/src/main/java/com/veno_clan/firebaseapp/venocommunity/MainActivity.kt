package com.veno_clan.firebaseapp.venocommunity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()/*, NavigationView.OnNavigationItemSelectedListener*/ {

    val auth = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cops_button.setOnClickListener { cops_event() }
        cod_button.setOnClickListener { cod_event() }

    }

    fun cops_event(){
        startActivity(Intent(this, CopsActivity::class.java))
    }

    fun cod_event(){
        Log.e("","터치!")
    }


}







//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        val toggle = ActionBarDrawerToggle(
//            this, drawer_layout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//            drawer_layout.addDrawerListener(toggle)
//                toggle.syncState()
//        nav_view.setNavigationItemSelectedListener(this)
//    }
//    override fun onBackPressed() {
//        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
//            drawer_layout.closeDrawer(GravityCompat.START)
//        }else {
//            super.onBackPressed()
//        }
//    }
//
//      override fun onCreateOptionsMenu(menu: Menu): Boolean{
//          menuInflater.inflate(R.menu.main, menu)
//          return true
//      }
//
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when(item.itemId){
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.nav_document -> {
//                loadDocument(frag1 = documentFragment())
//            }
//            R.id.bug_report -> {
//                Toast.makeText(this, "신고 기능을 준비중입니다",Toast.LENGTH_SHORT).show()
//                  loadReport(frag2 = ReportFragment())
//            }
//            R.id.account -> {
//                loadAccounrt(frag3 = AccountFragment())
//            }
//            R.id.nav_notice -> {
//                loadNotice(frag4 = NoticeFragment())
//            }
//            R.id.about -> {
//                loadAbout(frag5 = AboutFragment())
//            }
//        }
//        drawer_layout.closeDrawer(GravityCompat.START)
//        return true
//    }
//    private fun loadDocument(frag1: documentFragment){
//        val fm = supportFragmentManager.beginTransaction()
//        fm.replace(R.id.frameLayout, frag1)
//        fm.commit()
//    }
//    private fun loadReport(frag2: ReportFragment){
//        val fm = supportFragmentManager.beginTransaction()
//        fm.replace(R.id.frameLayout, frag2)
//        fm.commit()
//    }
//    private fun loadAccounrt(frag3: AccountFragment){
//        val fm = supportFragmentManager.beginTransaction()
//        fm.replace(R.id.frameLayout, frag3)
//        fm.commit()
//    }
//    private fun loadNotice(frag4: NoticeFragment){
//        val fm = supportFragmentManager.beginTransaction()
//        fm.replace(R.id.frameLayout, frag4)
//        fm.commit()
//    }
//    private fun loadAbout(frag5: AboutFragment){
//        val fm = supportFragmentManager.beginTransaction()
//        fm.replace(R.id.frameLayout, frag5)
//        fm.commit()
//    }




