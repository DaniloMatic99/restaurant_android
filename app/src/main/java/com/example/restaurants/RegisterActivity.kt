package com.example.restaurants

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_restaurant_info.*

class RegisterActivity : AppCompatActivity() {

    /*private lateinit var imgView: ImageView
    companion object{
        val IMAGE_REQUEST_CODE = 100
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var firstName: String
        var lastName: String
        var email: String
        var password: String
        var repeatPassword: String
        var typeOfRestaurant: String
        //var img : Int
        //imgView = findViewById(R.id.imageViewLogin)

        /* imgView.setOnClickListener{
            pickImageGallery()

        }*/

        val sharedPred = getSharedPreferences("MYPREFS", Context.MODE_PRIVATE)
        // var edit : SharedPreferences.Editor = sharedPred.edit()

        buttonRegister.setOnClickListener {
            firstName = editFirstName.text.trim().toString()
            lastName = editLastName.text.trim().toString()
            email = editEmail.text.trim().toString()
            password = editPassword.text.trim().toString()
            repeatPassword = editRepeatPassword.text.trim().toString()
            typeOfRestaurant = editTypeOfRestaurant.text.trim().toString()
            //img = imgView.toString().toInt()
            if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty()
                && password.isNotEmpty() && repeatPassword.isNotEmpty() && typeOfRestaurant.isNotEmpty()
            ) {

                if (password != repeatPassword) {
                    Toast.makeText(this, "Passwords don't match", Toast.LENGTH_LONG).show()
                } else {
                    println(email)
                    with(sharedPred.edit()) {
                        putString("firstName", firstName)
                        putString("lastName", lastName)
                        putString("email", email)
                        putString("password", password)
                        putString("repeatPassword", repeatPassword)
                        putString("typeOfRestaurant", typeOfRestaurant)
                        // putInt("image", img)
                        apply()
                    }

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(this, "Input required", Toast.LENGTH_LONG).show()

            }

        }

        tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
    }
}
/*    private fun pickImageGallery() {
    val intent = Intent(Intent.ACTION_PICK)
    intent.type = "image/*"
    startActivityForResult(intent, IMAGE_REQUEST_CODE)
}

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
        imgView.setImageURI(data?.data)
    }
}*/