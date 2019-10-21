package com.traumahelper.app.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.traumahelper.app.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import dal.AppDatabase;
import dal.CategoryDao;
import dal.PhotoDao;
import models.CategoryContent;
import models.Photo;

public class PhotoActivity extends AppCompatActivity {

    public CategoryContent.CategoryItem category;
    public Photo photo;
    private AppDatabase db;
    private PhotoDao photoDao;
    private CategoryDao categoryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, photo.getName(), Snackbar.LENGTH_LONG)
                        .setAction("Continuer", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                category.photos.remove(photo);
                                Intent intent = new Intent(PhotoActivity.this, PhotoActivity.class);
                                intent.putExtra("category", category);
                                startActivity(intent);
                            }
                        }).show();
            }
        });


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent newIntent = new Intent(PhotoActivity.this, MainActivity.class);
                startActivity(newIntent);
            }
        });

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "traumahelper").allowMainThreadQueries().build();
        photoDao = db.photoDao();
        categoryDao = db.categoryDao();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        category = intent.getParcelableExtra("category");
        assert category != null;
        if (category.photos == null) {
            category.photos = photoDao.getAllByIdCategory(category.id);
        }
        if (category.photos.size() > 0) {
            Random rand = new Random();
            photo = category.photos.get(rand.nextInt(category.photos.size()));
            ImageView mImageView = findViewById(R.id.imageView);
            int id = getResources().getIdentifier(photo.getImage(), "drawable", getPackageName());
            mImageView.setImageResource(id);
        } else {
            category.isDone = true;
            categoryDao.update(category);
            Toast.makeText(this, "Bravo ! Tu as terminé cette catégorie !", Toast.LENGTH_SHORT).show();
            Intent newIntent = new Intent(this, MainActivity.class);
            startActivity(newIntent);
        }

    }

}
