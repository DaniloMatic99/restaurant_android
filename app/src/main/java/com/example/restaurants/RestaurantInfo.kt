package com.example.restaurants

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class RestaurantInfo : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
       val a = (activity as? AppCompatActivity)?.supportActionBar?.hide()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_restaurant_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var name : TextView = view.findViewById(R.id.restaurantName)
        var score: TextView = view.findViewById(R.id.score)
        var snippet: TextView = view.findViewById(R.id.snippet)
        var img: ImageView = view.findViewById(R.id.imageView)


        name.text = requireArguments().getString("name").toString()

        var scoreString = requireArguments().getString("score")
        var number3digits:Double = String.format("%.3f",scoreString?.toDouble()).toDouble()
        var number2digits:Double = String.format("%.2f", number3digits).toDouble()
        score.text = number2digits.toString()

        snippet.text = requireArguments().getString("snippet").toString()
        val imgUrl = requireArguments().getString("imgUrl").toString()
        context?.let {
            if(imgUrl == "")
                Glide.with(it).load(R.drawable.ic_baseline_image_24).centerCrop().error(R.drawable.ic_baseline_image_24).into(img)
            else
                Glide.with(it).load(imgUrl).centerCrop().error(R.drawable.ic_baseline_image_24).into(img)
        }
        val backArrow = view.findViewById<ImageView>(R.id.backArrow)
        backArrow.setOnClickListener{
                var lastId =findNavController().previousBackStackEntry?.destination?.id
                if (lastId == R.id.homeFragment) {
                    findNavController().navigate(R.id.action_restaurantInfo_to_homeFragment)
                }else if (lastId == R.id.favoritesFragment){
                    findNavController().navigate(R.id.action_restaurantInfo_to_favoritesFragment)
                }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RestaurantInfo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RestaurantInfo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}