package cl.camilolillo.kotlinviper.dataManagers.FireBase

import com.google.firebase.auth.FirebaseAuth

class AuthDataManager(private var managerOutput: Output?) {

    interface Output {
        fun onLoginSuccess() { }
        fun onLoginFailure(failureMessage: String) { }
        fun onRegisterSuccess() { }
        fun onRegisterFailure(failureMessage: String) { }
        fun onSendEmailVerificationSuccess() { }
        fun onSendEmailVerificationFailure(failureMessage: String) { }
        fun onSignOut() { }
    }

    fun Login(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            email,
            password
        ).addOnCompleteListener {
            task ->
            if(task.isSuccessful) {
                managerOutput?.onLoginSuccess()
            } else {
                task.exception?.message.let {
                    managerOutput?.onLoginFailure(task.exception?.message!!)
                }
            }
        }
    }

    fun Register(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            email,
            password
        ).addOnCompleteListener {
            task ->
            if(task.isSuccessful) {
                managerOutput?.onRegisterSuccess()
            } else {
                task.exception?.message.let {
                    managerOutput?.onRegisterFailure(task.exception?.message!!)
                }
            }
        }
    }

    fun sendEmailVerification() {
        FirebaseAuth.getInstance().currentUser?.sendEmailVerification()?.addOnCompleteListener {
            task ->
            if(task.isSuccessful) {
                managerOutput?.onSendEmailVerificationSuccess()
            } else {
                task.exception?.message.let {
                    managerOutput?.onSendEmailVerificationFailure(task.exception?.message!!)
                }
            }
        }
    }

    fun isEmailVerified() : Boolean? {
        return FirebaseAuth.getInstance().currentUser?.isEmailVerified
    }

    fun getUserEmail() : String? {
        return FirebaseAuth.getInstance().currentUser?.email.toString()
    }

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
        managerOutput?.onSignOut()
    }

}