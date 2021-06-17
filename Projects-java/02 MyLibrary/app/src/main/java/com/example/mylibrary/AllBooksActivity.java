   
package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private static final String TAG = "AllBooksActivity";

    private RecyclerView booksRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        overridePendingTransition(R.anim.in, R.anim.out);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d(TAG, "onCreate: started");

        booksRecView = (RecyclerView) findViewById(R.id.booksRecView);

        BooksRecViewAdapter adapter = new BooksRecViewAdapter(this);
        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new GridLayoutManager(this, 2));

//        books.add(new Book("Into the Galaxies", "Noauthor",345,"https://d1jqu7g1y74ds1.cloudfront.net/wp-content/uploads/2011/07/symmetry-lg.jpg", "into the galaxies asjfdief eajfafeqa"));
//        books.add(new Book("Into the Wild", "Noauthor",786,"https://d1jqu7g1y74ds1.cloudfront.net/wp-content/uploads/2011/07/symmetry-lg.jpg", "into the wild asjfdief eajfafeqa"));

        // Using memory address of static var
//        Util util = new Util();
//        ArrayList<Book> books = new ArrayList<>();
//        books = util.getAllBooks();
//        adapter.setBooks(books);

        adapter.setBooks(Util.getInstance(this).getAllBooks());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.close_in, R.anim.close_out);
    }
}