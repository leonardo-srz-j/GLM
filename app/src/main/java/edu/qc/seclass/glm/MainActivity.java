package edu.qc.seclass.glm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import edu.qc.seclass.glm.data.StoreCategoryDataProvider;
import edu.qc.seclass.glm.database.DBSource;
import edu.qc.seclass.glm.model.StoreCategory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    ArrayAdapter<String> mAdapter;
    ListView categoryListView;
    DBSource mDataSource;
    List<StoreCategory> StoreCategoryList = StoreCategoryDataProvider.StoreCategoryList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        mDataSource = new DBSource(this);
//        mDataSource.open();
//
//
//        long numItems = mDataSource.getDataItemsCount(CategoryTable.TABLE_CATEGORY);
//        for (StoreCategory category:
//                StoreCategoryList) {
//            try {
//                mDataSource.createStoreCategory(category);
//            } catch (SQLiteException e) {
//                e.printStackTrace();
//            }
//
//        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //the drawer and toggle are the two views that need to be synchronized
        //the following toggle is a drawerListener
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        //this takes care of the hamburger icon to get to the drawer itself
        toggle.syncState();

        //we start the app with the list manager
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ListManagerFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_listManager_item);
        }
    }







    /**
     * We make use of this method to handle the fragments
     * we use the item ID to know which fragment we are calling
     * <p>
     * Then we replace the fragment_container (refer to activity_main)
     * with the corresponding inflater
     * The inflater is returned by the fragment's class
     * which is why we commit a new instance of the class
     *
     * @param menuItem Selected item from the navigation menu
     * @return "true" value for the selected item
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_listManager_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ListManagerFragment()).commit();

               //not implemented yet should be in the ListManager fragment
                // loadUserList();


                break;//when this case is handled we will leave the switch segment

            case R.id.nav_category_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CategoryFragment()).commit();
                break;

            case R.id.nav_search_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SearchFragment()).commit();
                break;

            case R.id.nav_about_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutFragment()).commit();
                break;
        }

        //added next two lines to test if drawer will close after click
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadUserList() {
      //  ArrayList<String> userlist = db.Helper
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
