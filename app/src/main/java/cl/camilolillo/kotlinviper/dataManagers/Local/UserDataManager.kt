package cl.camilolillo.kotlinviper.dataManagers.Local

import cl.camilolillo.kotlinviper.model.User
import io.realm.Realm

class UserDataManager(private var managerOutput: Output?) {

    interface Output {
        fun onStoreUserSuccess() { }
        fun onStoreUserFailure() { }
        fun onRemoveUserSuccess() { }
        fun onRemoveUserFailure() { }
    }

    val realm by lazy {
        Realm.getDefaultInstance()
    }

    fun storeUser(email: String) {
        try {
            this.realm.executeTransaction {
                this.realm.createObject(User::class.java, email)
            }
        } catch (e : Exception) {
            managerOutput?.onStoreUserFailure()
        }
        managerOutput?.onStoreUserSuccess()
    }

    fun getStoredUser() : User? {
        return this.realm.where(User::class.java).findFirst()
    }

    fun removeUser() {
        try {
            this.realm.executeTransaction {
                this.realm.deleteAll()
            }
        } catch (e : Exception) {
            managerOutput?.onRemoveUserFailure()
        }
        managerOutput?.onRemoveUserSuccess()
    }

}