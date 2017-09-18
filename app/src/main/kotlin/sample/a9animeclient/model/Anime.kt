package sample.a9animeclient.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by keita on 2017/09/17.
 */

data class Anime(val title_jp: String,
                 val url: String,
                 val season: String) : Parcelable {
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Anime> = object : Parcelable.Creator<Anime> {
            override fun createFromParcel(source: Parcel): Anime = source.run {
                Anime(readString(),readString(),readString())
            }
            override fun newArray(size: Int): Array<Anime?> = arrayOfNulls(size)
        }
    }
    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.run {
            writeString(title_jp)
            writeString(url)
            writeString(season)
            //writeParcelable(user, flags)
        }
    }
}