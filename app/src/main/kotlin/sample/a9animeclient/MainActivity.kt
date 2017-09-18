package sample.a9animeclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import sample.a9animeclient.client.AnimeClient
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import android.util.Log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById(R.id.anime_title) as ListView
        val progressBar = findViewById(R.id.progress_bar) as ProgressBar

        progressBar.visibility = View.VISIBLE

        // Adapterの設定
        val listAdapter = AnimeListAdapter(applicationContext)
        listView.adapter = listAdapter


        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://nineanimeapi.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        val animeClient = retrofit.create(AnimeClient::class.java)

        val queryMap = mapOf<String,String>("year" to "2017", "season" to "Winter")

        animeClient.search(queryMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers .mainThread())
                .doAfterTerminate {
                    progressBar.visibility = View.GONE
                }
                //.bindToLifecycle(this)
                .subscribe({
                    listAdapter.animes = it // itはArticleのリスト？
                    listAdapter.notifyDataSetChanged()
                }, {
                    toast("エラー: $it")
                    Log.v("search","$it")
                })
    }
}
