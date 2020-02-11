package cl.camilolillo.kotlinviper.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User (
    @PrimaryKey
    var email: String? = null
) : RealmObject()