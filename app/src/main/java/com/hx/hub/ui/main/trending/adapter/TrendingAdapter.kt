package com.hx.hub.ui.main.trending.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hx.hub.R
import com.hx.hub.di.BASE_URL
import com.hx.hub.entity.TrendingRepo
import de.hdodenhof.circleimageview.CircleImageView

class TrendingAdapter: ListAdapter<TrendingRepo, TrendingAdapter.TrendingViewHolder>(TrendingDiffCallBack()) {

    private val liveData: MutableLiveData<String> = MutableLiveData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rtendings_repo, parent, false))
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.binds(getItem(position), liveData)
    }

    fun getItemClickEvent(): LiveData<String> {
        return liveData
    }


    class TrendingViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        private val ivAvatar: ImageView = view.findViewById(R.id.ivAvatar)
        private val btnAvatar: ConstraintLayout = view.findViewById(R.id.btnAvatar)

        private val tvOwnerName: TextView = view.findViewById(R.id.tvOwnerName)
        private val ivLanguageColor: CircleImageView = view.findViewById(R.id.ivLanguageColor)

        private val tvLanguage: TextView = view.findViewById(R.id.tvLanguage)
        private val tvRepoName: TextView = view.findViewById(R.id.tvRepoName)
        private val tvRepoDesc: TextView = view.findViewById(R.id.tvRepoDesc)
        private val tvStar: TextView = view.findViewById(R.id.tvStar)
        private val tvIssue: TextView = view.findViewById(R.id.tvIssue)
        private val tvFork: TextView = view.findViewById(R.id.tvFork)
        fun binds(item: TrendingRepo,  observer: MutableLiveData<String>){
            view.setOnClickListener {
                observer.postValue(BASE_URL + "repos/" +  item.author + "/"+item.name)
            }
            ivAvatar.load(item.avatar)
            tvOwnerName.text = item.author
            tvLanguage.text = item.language
            tvRepoName.text = item.name
            tvRepoDesc.text = item.description
            tvStar.text = item.stars.toString()
            tvFork.text = item.forks.toString()
        }
    }

    private class TrendingDiffCallBack: DiffUtil.ItemCallback<TrendingRepo>(){

        override fun areItemsTheSame(oldItem: TrendingRepo, newItem: TrendingRepo): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: TrendingRepo, newItem: TrendingRepo): Boolean {
            return oldItem.url == newItem.url
        }

    }



}