/**
 * Final Project: This is a basic eCommerce app. It allows the user to add a listing through
 * a dialog fragment, set the type and imageview using radio buttons, and shows the image with
 * a toString description of the Product object. This is the adapter that gets everything from storage
 * and binds it to each card view within the recycler view.
 *
 *
 * @author  Charles Brown
 * @version May 3, 2024
 */
package com.example.musicmarket;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ListItemHolder> {
    private List<Product> mItemList;
    private MainActivity mMainActivity;

    //Constructor for the Adapter
    public ListingAdapter(MainActivity mainActivity, List<Product> itemList) {
        mMainActivity = mainActivity;
        mItemList = itemList;
    }
    //Nested ListItemHolder class that holds references to the controls inside the cardview.
    public static class ListItemHolder extends RecyclerView.ViewHolder {
        TextView listingView;
        ImageView imageView;

        //Constructor for ListItemHolder class
        public ListItemHolder(View itemView) {
            super(itemView);
            listingView = itemView.findViewById(R.id.listingView);
            imageView = itemView.findViewById(R.id.cardImageView);
        }
    }

    //Create the ViewHolder that instantiates a View, inflates the recycler view layout to the view,
    //and returns that View as an argument of the ListItemHolder class created above.
    @NonNull
    @Override
    public ListingAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);
        return new ListingAdapter.ListItemHolder(itemView);
    }

    //Method that binds our data to our controls within the cardview.
    @Override
    public void onBindViewHolder(@NonNull ListingAdapter.ListItemHolder holder, int position) {
        Product item = mItemList.get(position);
        holder.listingView.setText(item.toString());
        if (item.getType().equalsIgnoreCase("guitar")) {
            holder.imageView.setImageResource(R.drawable.guitar);
        }
        else if (item.getType().equalsIgnoreCase("drum")) {
            holder.imageView.setImageResource(R.drawable.drums);
        }
        else if (item.getType().equalsIgnoreCase("mic")) {
            holder.imageView.setImageResource(R.drawable.mic);
        }

    }

    //Returns the size of the Product list instantiated at top of Adapter class.
    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
