package com.example.restaurants

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurants.model.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView : RecyclerView
    private var restaurantList: MutableList<Data> = arrayListOf()
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
        _view = inflater.inflate(R.layout.fragment_favorites, container, false)

        var preferences : SharedPreferences = this.requireActivity().getSharedPreferences("MYPREFS", MODE_PRIVATE)
        var regtypeOfRestaurant: String? = preferences.getString("typeOfRestaurant","")


        GlobalScope.launch {
            val instance = RetrofitClient.getInstance()
            val api = instance.create(RestaurantApi::class.java)
            println(regtypeOfRestaurant.toString())
            val cit : String = "Paris".toString()
            val tag : String = "cuisine-" + regtypeOfRestaurant.toString()

            val response = api.getRestaurants(cit, tag)

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    val lista = response.body()?.results
                    if(lista == null || lista!!.isEmpty()) {
                        findNavController().navigate(R.id.action_favoritesFragment_to_emptyFavorite)
                        super.onDestroy()
                    }else{
                        restaurantList = lista!!
                        val layoutManager = LinearLayoutManager(context)
                        recyclerView = _view.findViewById(R.id.myRecyclerView)
                        recyclerView.layoutManager = layoutManager
                        recyclerView.setHasFixedSize(true)
                        var adapter = context?.let { MyAdapter(restaurantList, it) }
                        recyclerView.adapter = adapter
                        if (adapter != null) {
                            adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener{
                                override fun onItemClick(position: Int) {
                                    var images = restaurantList[position].images
                                    var imgUrl : String

                                    if(images!!.isEmpty())
                                            imgUrl = ""
                                    else
                                            imgUrl =  restaurantList[position].images?.get(0)?.sizes?.medium?.url.toString()
                                    findNavController().navigate(R.id.action_favoritesFragment_to_restaurantInfo,Bundle().apply {
                                            putString("name", restaurantList[position].name)
                                            putString("score", restaurantList[position].score.toString())
                                            putString("snippet", restaurantList[position].snippet)
                                            putString("imgUrl",imgUrl)
                                        })
                                    }


                                })
                            } else {

                        }

                    }
                }
            }
        }
        // Inflate the layout for this fragment
        return _view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()


    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoritesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}