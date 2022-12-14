package com.codepath.apps.CodePatherTweets.models;

import android.provider.ContactsContract;
import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.codepath.apps.CodePatherTweets.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId"))
public class Tweet {


    @ColumnInfo
    @PrimaryKey
    public long id;

    @ColumnInfo
    public String body;

    @ColumnInfo
    public String createdAt;

    @ColumnInfo
    public Long userId;

    @ColumnInfo
    public String firstPhotoUrl;

    @Ignore
    public  User user;



    private static JSONObject jsonObject;



    //Empty constructor needed by to Parceler Library
    public Tweet(){}
    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {

        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.id =jsonObject.getLong("id");
        User user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.user=user;
        tweet.userId=user.id;

        // Pulls embedded media, if it exists
        JSONObject entitiesObject = jsonObject.getJSONObject("entities");
        if (entitiesObject.has("media")) {
            JSONArray mediaArray = entitiesObject.getJSONArray("media");
            tweet.firstPhotoUrl = mediaArray.getJSONObject(0).getString("media_url_https");
            Log.i("Tweet", "Media: " + tweet.firstPhotoUrl);
        } else {
            Log.i("Tweet", "no media");
        }

        return tweet;

    }

    //for the date
    public String getFormattedTimestamp() {

        return TimeFormatter.getTimeDifference(createdAt);
    }
    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for(int i=0; i < jsonArray.length(); i++){
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return  tweets;
    }
}