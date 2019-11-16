package com.veno_clan.firebaseapp.venocommunity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.veno_clan.firebaseapp.venocommunity.ui.Cops_Write_Fragment

class CopsActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cops)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.join_write -> {
                val copsWriteJoin = Cops_Write_Fragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, copsWriteJoin)
                    .commit()
                return true
            }
            R.id.join_write -> {
                val copsWriteJoin = Cops_Write_Fragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, copsWriteJoin)
                    .commit()
                return true
            }
            R.id.join_write -> {
                val copsWriteJoin = Cops_Write_Fragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, copsWriteJoin)
                    .commit()
                return true
            }
            else -> return false
        }
    }
}
