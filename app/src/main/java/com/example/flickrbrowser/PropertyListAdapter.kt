package com.example.flickrbrowser

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.flickrbrowser.databinding.ItemViewBinding
//import com.squareup.picasso.Picasso


class PropertyListAdapter :
    ListAdapter<Photo, PropertyListAdapter.PropertyViewHolder>(RowItemDiffCallback()) {

    fun setData(data: List<Photo>) {
        submitList(data)
    }

    class PropertyViewHolder private constructor(val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): PropertyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ItemViewBinding = DataBindingUtil.inflate(
                    layoutInflater,
                    R.layout.item_view, parent, false
                )
                //val binding = ItemViewBinding.inflate(layoutInflater, parent, false)
                return PropertyViewHolder(binding)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {

        return PropertyViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        holder.binding.image = getItem(position) as Photo
        holder.binding.executePendingBindings()
    }

}

class RowItemDiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        Log.v("callback areItemsTheSame", Thread.currentThread().name)
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        Log.v("callback areContentsTheSame", Thread.currentThread().name)
        return oldItem.toString() == newItem.toString()
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        //val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        var imageReady = false
        imgView.setOnClickListener {
            if (imageReady) {
                val action = SearchFragmentDirections.actionSearchFragment3ToDetailFragment(imgUrl);
                imgView.findNavController().navigate(action)
            }
            else {
                val toast = Toast.makeText(imgView.context, "Failed to load",Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        Glide.with(imgView.context)
            .load(imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            ).listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageReady = true
                    return false
                }
            })
            .into(imgView)
    }
}
//
//@BindingAdapter("imageUrl2")
//fun bindImage2(imgView: ImageView, imgUrl: String?) {
//    imgUrl?.let {
//        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
//        Picasso.get()
//            .load(imgUri)
//            .resize(200, 200)
//            .placeholder(R.drawable.loading_animation)
//            .error(R.drawable.ic_broken_image)
//            .into(imgView)
//    }
//}
