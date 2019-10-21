package com.traumahelper.app.activities;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.stetho.Stetho;
import com.traumahelper.app.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dal.AppDatabase;
import dal.CategoryDao;
import dal.PhotoDao;
import models.CategoryContent;
import models.Photo;

public class MainActivity extends AppCompatActivity implements CategoryFragment.OnListFragmentInteractionListener {

    private AppDatabase db;
    private CategoryDao categoryDao;
    private PhotoDao photoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Stetho.initializeWithDefaults(this);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "traumahelper").allowMainThreadQueries().build();
        categoryDao = db.categoryDao();
        photoDao = db.photoDao();
        populateDatabase();
        showCategories();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showCategories();
    }

    private void populateDatabase() {
        categoryDao.insertAll(
            new CategoryContent.CategoryItem(1, "Animaux", "Les trucs qui mangent et font du bruit"),
            new CategoryContent.CategoryItem(2, "Nourriture", "Pour redevenir gourmand"),
            new CategoryContent.CategoryItem(3, "Accidents", "Pour éviter un nouveau trauma"),
            new CategoryContent.CategoryItem(4, "Marques", "Vivre dans un monde capitaliste"),
            new CategoryContent.CategoryItem(5, "Personnes", "Reconnaître les gens dans la rue"),
            new CategoryContent.CategoryItem(6, "Poneys", "Les races de poneys de A à Z"),
            new CategoryContent.CategoryItem(7, "One Directions", "Reconnaîtres les membres du boys band")
        );
        photoDao.insertAll(
            new Photo("BigMac", "", "bigmac", 2),
            new Photo("Carottes", "", "carotte", 2),
            new Photo("Pain", "", "pain", 2)
        );
    }

    private void showCategories() {
        CategoryContent.ITEMS.clear();
        for (CategoryContent.CategoryItem category : categoryDao.getAll()) {
            CategoryContent.ITEMS.add(category);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(CategoryContent.CategoryItem item) {
        Toast.makeText(this, "Allons-y !", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PhotoActivity.class);
        intent.putExtra("category", item);
        startActivity(intent);
    }
}
