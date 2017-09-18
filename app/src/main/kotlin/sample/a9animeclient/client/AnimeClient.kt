package sample.a9animeclient.client

/**
 * Created by keita on 2017/09/17.
 */

import retrofit2.http.GET
import retrofit2.http.QueryMap
import rx.Observable
import sample.a9animeclient.model.Anime

interface AnimeClient {
    @GET("/api/")
    fun search(@QueryMap queryMap: Map<String,String>): Observable<List<Anime>>
}
