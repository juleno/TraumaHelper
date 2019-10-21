package com.traumahelper.app.activities;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.traumahelper.app.R;
import com.traumahelper.app.activities.CategoryFragment.OnListFragmentInteractionListener;

import models.CategoryContent;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link models.CategoryContent.CategoryItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {

    private final List<CategoryContent.CategoryItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public CategoryRecyclerViewAdapter(List<CategoryContent.CategoryItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).name);
        holder.mDescView.setText(mValues.get(position).details);
        if (!mValues.get(position).isDone) {
            holder.mDoneView.setVisibility(View.INVISIBLE);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mDescView;
        public final ImageView mDoneView;
        public CategoryContent.CategoryItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.name);
            mDescView = (TextView) view.findViewById(R.id.description);
            mDoneView = (ImageView) view.findViewById(R.id.done);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
