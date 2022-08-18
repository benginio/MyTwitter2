package com.codepath.apps.CodePatherTweets;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.codepath.apps.CodePatherTweets.models.SampleModel;
import com.codepath.apps.CodePatherTweets.models.SampleModelDao;
import com.codepath.apps.CodePatherTweets.models.Tweet;
import com.codepath.apps.CodePatherTweets.models.TweetDao;
import com.codepath.apps.CodePatherTweets.models.User;

@Database(entities={SampleModel.class, Tweet.class, User.class}, version=2)
public abstract class MyDatabase extends RoomDatabase {
    public abstract SampleModelDao sampleModelDao();
    public abstract TweetDao tweetDao();

    // Database name to be used
    public static final String NAME = "MyDataBase";
}
