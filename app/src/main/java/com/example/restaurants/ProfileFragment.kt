package com.example.restaurants

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var _view : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_profile, container, false)

        var preferences : SharedPreferences = this.requireActivity().getSharedPreferences("MYPREFS", MODE_PRIVATE)
        var firstName = _view.findViewById<EditText>(R.id.firstName)
        var lastName = _view.findViewById<EditText>(R.id.lastName)
        var email = _view.findViewById<EditText>(R.id.email)
        var typeOfRestaurant= _view.findViewById<EditText>(R.id.typeOfRestaurant)


        var regFirstName : String? = preferences.getString("firstName","")
        var reglastName: String? = preferences.getString("lastName","")
        var regEmail : String? = preferences.getString("email","")
        var regtypeOfRestaurant: String? = preferences.getString("typeOfRestaurant","")

        firstName.setText(regFirstName.toString())
        lastName.setText(reglastName.toString())
        email.setText(regEmail.toString())
        typeOfRestaurant.setText(regtypeOfRestaurant.toString())

        var editFirstName = _view.findViewById<ImageView>(R.id.imageEditFirstName)
        var editLastName = _view.findViewById<ImageView>(R.id.imageEditLastName)
        var editTypeOfRestaurant = _view.findViewById<ImageView>(R.id.imageEditTypeOfRestaurant)

        editProfile(editFirstName, firstName,preferences,"firstName")
        editProfile(editLastName, lastName,preferences,"lastName")
        editProfile(editTypeOfRestaurant, typeOfRestaurant,preferences,"typeOfRestaurant")

        return _view
    }

    private fun editProfile(image: ImageView, field: EditText, preferences: SharedPreferences, key : String) {
        image.setOnClickListener {
            if(image.drawable.constantState== resources.getDrawable(R.drawable.ic_baseline_mode_edit_24).constantState){
                image.setImageResource(R.drawable.ic_baseline_save_24)
                field.isEnabled = true
            }else  if(image.drawable.constantState== resources.getDrawable(R.drawable.ic_baseline_save_24).constantState){
                image.setImageResource(R.drawable.ic_baseline_mode_edit_24)
                field.isEnabled = false
                with(preferences.edit()){
                    putString(key,field.text.toString())
                    apply()
                }
            }
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logoutButton = view.findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener{
            var intent = Intent(this.activity, LoginActivity::class.java)
            startActivity(intent)
            this.requireActivity().finish()
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}