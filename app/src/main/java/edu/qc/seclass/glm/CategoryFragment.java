package edu.qc.seclass.glm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import edu.qc.seclass.glm.database.DBSource;
import edu.qc.seclass.glm.model.StoreCategory;

public class CategoryFragment extends Fragment {
    ListView categoryListView;
    ArrayAdapter<StoreCategory> mAdapter;
    DBSource mDataSource;

    //we need an array list and array adapter
    /**
     * Every time we create a new instance of this class we are calling
     * the onCreateView method
     * From my understanding, the returned inflater populates the layout (fragment_category) with
     * the objects we created for it, somewhat of an onCreate method for the fragment
     * This is needed because we are not creating a new activity
     * Creating new activities makes your app slower
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View categoryView = inflater.inflate(R.layout.fragment_category,
                container,false);
        categoryListView = categoryView.findViewById(R.id.user_list_view);

        mDataSource = new DBSource(this.getContext());
        mDataSource.open();
        mDataSource.seedDatabase();
        List<StoreCategory> categoriesFromDB =mDataSource.getAllCategories();

        if(mAdapter==null){
            mAdapter = new ArrayAdapter<>(this.getContext(),R.layout.fragment_category,R.id.tvCategoryName,categoriesFromDB);
            categoryListView.setAdapter(mAdapter);
        }




        return categoryView;
    }




    @Override
    public void onPause() {
        super.onPause();
        mDataSource.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        mDataSource.open();
    }
}

