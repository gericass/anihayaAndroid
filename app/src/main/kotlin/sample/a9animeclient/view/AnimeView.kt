package sample.a9animeclient.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import sample.a9animeclient.model.Anime
import sample.a9animeclient.bindView
import sample.a9animeclient.R

/**
 * Created by keita on 2017/09/18.
 */

class AnimeView : FrameLayout {
    constructor(context: Context?) : super(context)

    constructor(context: Context?,
                attrs: AttributeSet?) : super(context,attrs)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    val profileImageView: ImageView by bindView<ImageView>(R.id.profile_image_view)

    val titleTextView: TextView by bindView<TextView>(R.id.title_text_view)

    val userNameTextView: TextView by bindView<TextView>(R.id.user_name_text_view)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_anime, this)
    }

    fun setAnime(anime: Anime) {
        titleTextView.text = anime.title_jp
        userNameTextView.text = anime.url
        Glide.with(context).load(anime.url).into(profileImageView)
    }
}