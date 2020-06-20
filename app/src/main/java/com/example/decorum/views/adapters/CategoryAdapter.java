package com.example.decorum.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.decorum.R;
import com.example.decorum.models.CategoryModel;

import java.util.List;

// import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.RecyclerViewHolder> {

    private List<CategoryModel> categories;
    Context context;

    public CategoryAdapter(List<CategoryModel> designList, Context context) {
        this.categories = designList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cat_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        CategoryModel categoryModel = categories.get(position);
        holder.tvCategoryName.setText(categoryModel.designName);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategoryName;
        private ImageView imageViewDesign;

        RecyclerViewHolder(View view) {
            super(view);
            tvCategoryName = view.findViewById(R.id.tvCategoryName);
            imageViewDesign = view.findViewById(R.id.imDesign);
        }
    }
}








