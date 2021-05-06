package com.example.starwarsuniverse

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsuniverse.starwarsapi.Result
import com.squareup.picasso.Picasso

private const val TAG = "StarWarsFragment"
class StarWarsFragment: Fragment() {

    private lateinit var starwarsViewModel: StarWarsViewModel
    private lateinit var starwarsRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        starwarsViewModel =
            ViewModelProviders.of(this).get(StarWarsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_startwars_people, container, false)

        starwarsRecyclerView = view.findViewById(R.id.starwars_recycler_view)
        starwarsRecyclerView.layoutManager = GridLayoutManager(context, 1)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        starwarsViewModel.peopleLiveData.observe(
            viewLifecycleOwner,
            Observer { starwarsPeople ->
                starwarsRecyclerView.adapter = StarWarsPeopleAdapter(starwarsPeople)
            })
    }

    private inner class StarWarsHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView){

        private lateinit var starwarsResult: Result


        fun bindGalleryItem(item: Result) {
            starwarsResult = item
        }

        private val imageView: ImageView = itemView.findViewById(R.id.starwars_char_image)
        private val peopleName: TextView = itemView.findViewById(R.id.starwars_char_name)
        private val peopleDOB: TextView = itemView.findViewById(R.id.starwars_char_byear)
        fun bindImageUrl(url:String){
            Picasso.get().load(url).resize(500,500).centerCrop().into(imageView);
        }


        fun bindPeopleName(name: String){ peopleName.text = name}
        fun bindDOB(year: String){
            peopleDOB.text = year
        }


    }

    private inner class  StarWarsPeopleAdapter(private val galleryItems: List<Result>)
        : RecyclerView.Adapter<StarWarsHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): StarWarsHolder {
            val view = layoutInflater.inflate(R.layout.starwars_people_list, parent,false)
            return StarWarsHolder(view)
        }

        override fun getItemCount(): Int = galleryItems.size

        override fun onBindViewHolder(holder: StarWarsHolder, position: Int) {
            val galleryItem = galleryItems[position]
            val charname = galleryItem.name.replace("-", "_")
            holder.bindGalleryItem(galleryItem)
            holder.bindImageUrl(galleryItem.image)
            holder.bindDOB(galleryItem.birthYear)
            holder.bindPeopleName(galleryItem.name)
        }
    }

    companion object {
        fun newInstance() = StarWarsFragment()
    }
}