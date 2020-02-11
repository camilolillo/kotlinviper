package cl.camilolillo.kotlinviper.utils

class Constants {

    companion object alertDialog {
        const val successTitle: String = "SUCCESS"
        const val errorTitle: String = "ERROR"
        const val genericErrorMessage: String = "We got a problem with your request. Please try again."
        const val emptyFields: String = "All fields must be completed."
        const val registerSuccessMessage: String = "You have registered successfully. We sent a verification link to your email."
        const val emailNotVerified: String = "Your email is not verified. Please check your inbox or press the 'SEND VERIFICATION' button."
        const val fieldsNoMatch = "The password and the verification field must have the same content."
    }

}