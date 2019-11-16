package com.veno_clan.firebaseapp.venocommunity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class SplashActivity : AppCompatActivity() {

    private lateinit var remoteConfig: FirebaseRemoteConfig
    private val FIREBASE_FATCH_TIME: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initRemoteConfig()
    }

    fun initRemoteConfig(){
        remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setDeveloperModeEnabled(BuildConfig.DEBUG)
            .build()
        remoteConfig.setConfigSettings(configSettings)
        remoteConfig.setDefaults(R.xml.server_check)
//        remoteConfig.setDefaults(R.xml.app_update_versions)
        fetchAction()
    }
    fun fetchAction() {
        remoteConfig.fetch(FIREBASE_FATCH_TIME)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    remoteConfig.activateFetched()
                } else {

                }
                displayWelcomeMessage()

            }
    }

    fun displayWelcomeMessage() {
        val caps: Boolean = remoteConfig.getBoolean("welcome_message_caps")
        val splashMesseage: String = remoteConfig.getString("welcome_message")

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        if (caps) {
            builder.setMessage(splashMesseage)
                .setCancelable(false)
                .setPositiveButton("확인") { dialog, which -> finish() }
            builder.create().show()
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

}
