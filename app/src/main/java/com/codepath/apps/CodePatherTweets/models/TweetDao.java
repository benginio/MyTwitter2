package com.codepath.apps.CodePatherTweets.models;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TweetDao {

    @Query("SELECT Tweet.body AS tweet_body, Tweet.createdAt AS tweet_createdAt, Tweet.id AS tweet_id, Tweet.firstPhotoUrl AS tweet_firstPhotoUrl, User.* "+
            "FROM Tweet INNER JOIN User ON Tweet.userId=user.id ORDER BY createdAt DESC LIMIT 7")
    List<TweetWithUser> recentItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(Tweet... tweets);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(User... users);
}
