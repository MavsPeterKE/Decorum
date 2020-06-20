package com.example.decorum.views.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.decorum.R;
import com.example.decorum.models.CategoryModel;
import com.example.decorum.utils.GridSpacingItemDecoration;
import com.example.decorum.views.adapters.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentDesignCategories extends Fragment {

    private FragmentDesignCategoriesViewModel mViewModel;

    @BindView(R.id.categories_recycler)
    RecyclerView categoriesRecycler;

    public static FragmentDesignCategories newInstance() {
        return new FragmentDesignCategories();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_design_categories_fragment, container, false);
        categoriesRecycler = view.findViewById(R.id.categories_recycler);
        //ButterKnife.bind(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentDesignCategoriesViewModel.class);
        // TODO: Use the ViewModel
        setCategoriesList();
    }

    private void setCategoriesList() {
        CategoryModel model = new CategoryModel();
        model.designName = "Home Deco";
        CategoryModel model2 = new CategoryModel();
        model2.designName = "Kitchen";
        CategoryModel mode3 = new CategoryModel();
        mode3.designName = "Study";
        CategoryModel model4 = new CategoryModel();
        model4.designName = "Waiting Lounge";
        CategoryModel mode6 = new CategoryModel();
        mode3.designName = "Music Room";
        CategoryModel model7 = new CategoryModel();
        model4.designName = "Bath rooom";

        List<CategoryModel> modelList = new ArrayList<>();
        modelList.add(mode3);
        modelList.add(model2);
        modelList.add(model);
        modelList.add(model4);
        modelList.add(mode6);
        modelList.add(model7);

        CategoryAdapter categoryAdapter = new CategoryAdapter(modelList, getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(2, dpToPx(3), false);
        categoriesRecycler.setLayoutManager(gridLayoutManager);
        categoriesRecycler.addItemDecoration(gridSpacingItemDecoration);
        categoriesRecycler.setItemAnimator(new DefaultItemAnimator());
        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setItemViewCacheSize(modelList.size());
        categoriesRecycler.setNestedScrollingEnabled(false);
        categoriesRecycler.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

    }


    // Convert dp to px
    public int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r
                .getDisplayMetrics()));
    }

}