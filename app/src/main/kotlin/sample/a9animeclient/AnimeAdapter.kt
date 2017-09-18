package sample.a9animeclient

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import sample.a9animeclient.model.Anime
import sample.a9animeclient.view.AnimeView

/**
 * Created by keita on 2017/09/18.
 */

class AnimeListAdapter(private val context: Context) : BaseAdapter() {
    var animes: List<Anime> = emptyList()

    override fun getCount(): Int = animes.size

    override fun getItem(position: Int): Any? = animes[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int,
                         convertView: View?,
                         parent: ViewGroup?): View =
            ((convertView as? AnimeView) ?: AnimeView(context)).apply {
                setAnime(animes[position])
            }

}