package com.example.figma_replicate.InputValidators

fun FullName_Validator(fullName: String):String?{
    val formErrorMessage = when {
        fullName.trim().length < 5 -> "Full name too short"
        !fullName.matches(Regex("^[A-Za-z ]+$")) -> "Invalid Name"
        else -> null
    }
    return formErrorMessage

}
fun UserName_Validator(username: String):String?{
    val formErrorMessage = when {
        username.length < 5 -> "Username too short"
        !username.matches(Regex("^[A-Za-z0-9_]+\$")) -> "Invalid Username"
        /// later on implement the username search for unique usernames
        else -> null
    }
    return formErrorMessage

}
fun Email_Validator(email: String):String?{
    val formErrorMessage = when {
        !email.matches(Regex("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$")) -> "Invalid Email"
        /// later on implement the email search for unique usernames
        else -> null
    }
    return formErrorMessage

}
fun Designation_Validation(designation: String):String?{
    val formErrorMessage = when {
        designation.length <= 3 -> "Designation too short"
        else -> null
    }
    return formErrorMessage

}
fun Password_Validator(password: String, confirmPassword:String):String?{
    val formErrorMessage = when {
        password.length <= 6 -> "Password too short"
        password != confirmPassword -> "Passwords do not match"
        else -> null
    }
    return formErrorMessage

}
