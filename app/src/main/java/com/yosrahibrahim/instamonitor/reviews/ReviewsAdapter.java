package com.yosrahibrahim.instamonitor.reviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yosrahibrahim.instamonitor.R;
import com.yosrahibrahim.instamonitor.reviews.model.Review;

import java.util.List;

/**
 * Created by Yosrah Ibrahim on 6/10/2016.
 */
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private Context context;
    private List<Review> reviews;

    public ReviewsAdapter(Context context, List<Review> reviews){
        this.context = context;
        this.reviews = reviews;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_review, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.review.setText(reviews.get(position).getReview());
        holder.reviewer.setText(reviews.get(position).getReviewer());

    }



    @Override
    public int getItemCount() {
        return (reviews == null) ? 0: reviews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView review, reviewer;

        public ViewHolder(View v) {
            super(v);

            review = (TextView) v.findViewById(R.id.review);
            reviewer = (TextView) v.findViewById(R.id.reviewer);

        }

    }
}


