package com.example.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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



class HomeFragment : Fragment()  {


    private lateinit var recyclerView : RecyclerView
    private var restaurantList: MutableList<Data> = arrayListOf()
    private lateinit var _view : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _view = inflater.inflate(R.layout.fragment_home, container, false)

        GlobalScope.launch {
            val instance = RetrofitClient.getInstance()
            val api = instance.create(RestaurantApi::class.java)


            val response = api.getRestaurants()

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    val lista = response.body()?.results

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
                                findNavController().navigate(R.id.action_homeFragment_to_restaurantInfo,Bundle().apply {
                                    putString("name", restaurantList[position].name)
                                    putString("score", restaurantList[position].score.toString())
                                    putString("snippet", restaurantList[position].snippet)
                                    putString("imgUrl",imgUrl)
                                })
                            }


                        })
                    }


                }
            }
        }
        // Inflate the layout for this fragment
        return _view
    }

    override fun onStop() {
        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}



