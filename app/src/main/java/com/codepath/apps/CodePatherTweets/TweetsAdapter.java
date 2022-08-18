package com.codepath.apps.CodePatherTweets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.CodePatherTweets.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.viewHolder> {
    @NonNull
    Context context; //create context
    List<Tweet> tweets; //inialized list
    // Pass in the context and the list of tweets

    //the Constructor using context and list for parameter
    public TweetsAdapter(Context context,List<Tweet> tweets){
        this.context=context;
        this.tweets=tweets;
    }
    //for each a row, inflate the layout
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View View = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new viewHolder(View);
    }
    //Bind  values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
//Get data at position
        Tweet tweet = tweets.get(position);
        // Bind the tweet with view holder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    } //count list

    //for refresh the page
    //Clean all element of recycler view
    public void clear(){
        tweets.clear();
        notifyDataSetChanged();
    }
    //Add a list of item
    public void addAll(List<Tweet> tweetsList){
        tweets.addAll(tweetsList);
        notifyDataSetChanged();

    }
    // Define  a viewHolder
    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView tvTimeStamp;
        ImageView ivImage;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage =itemView.findViewById(R.id.ivProfileImage);
            tvBody =itemView.findViewById(R.id.tvBody);
            tvScreenName =itemView.findViewById(R.id.tvScreenName);
            tvTimeStamp=itemView.findViewById(R.id.tvTimeStamp);
            ivImage=itemView.findViewById(R.id.ivImage);
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            tvTimeStamp.setText(tweet.getFormattedTimestamp());
            Glide.with(context).load(tweet.user.profileImageUrl)
                    .centerCrop() // scale image to fill the entire ImageView
                    .transform(new CenterCrop(), new RoundedCorners(80))
                    .into(ivProfileImage);
            // Adds image to tweet view if available
            int radius = 10;
            if (tweet.firstPhotoUrl != null) {
                ivImage.setVisibility(View.VISIBLE);
                Glide.with(context).load(tweet.firstPhotoUrl).transform(new CenterCrop(),
                                new RoundedCorners(radius))
                        .into(ivImage);
            } else {
                ivImage.setVisibility(View.GONE);
            }
        }
    }
}
