package cl.camilolillo.kotlinviper.utils

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class KotlinViperApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(getRealmCongifuration())
    }

    fun getRealmCongifuration() : RealmConfiguration {
        return RealmConfiguration.Builder()
            .name("KotlinViper.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()
    }

}