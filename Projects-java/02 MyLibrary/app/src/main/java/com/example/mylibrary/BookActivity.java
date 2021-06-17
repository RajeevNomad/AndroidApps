package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    private static final String TAG = "BookActivity";

    private TextView bookName, authorName, description, pageNumber;
    private ImageView bookImage;

    private Button btnAddCurReading, btnAddWantToRead, btnAddAlreadyRead;

    private Book incomingBook;

//    private Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        overridePendingTransition(R.anim.in, R.anim.out);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initWidgets();

        Intent intent = getIntent();
        int id = intent.getIntExtra("bookId", 0);
//        util = new Util();
        ArrayList<Book> books = Util.getInstance(this).getAllBooks();

        for (Book b: books) {
            if (b.getId() == id) {
                incomingBook = b;
                bookName.setText(b.getName());
                authorName.setText(b.getAuthor());
                description.setText(b.getDescription());
                pageNumber.setText("Pages: " + b.getPages());
                Glide.with(this).asBitmap().load(b.getImageUrl()).into(bookImage);
            }
        }

        btnAddCurReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAddCurReadingTapped();
            }
        });

        btnAddAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAddAlreadyReadTapped();
            }
        });

        btnAddWantToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAddWantToReadTapped();
            }
        });
    }

    private boolean checkContains(ArrayList<Book> books, Book book) {
        boolean check = false;
        for (Book b: books) {
            if (b.getId() == book.getId()) {
                check = true;
            }
        }
        return check;
    }

    private void btnAddWantToReadTapped() {
        Log.d(TAG, "btnAddWantToReadTapped: Started");

        boolean doesExist = false;

        ArrayList<Book> wantToReadBooks = Util.getInstance(this).getWantToReadBooks();

        if (checkContains(wantToReadBooks, incomingBook)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("You Already Added this Book to your Want To Read List");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setCancelable(true);

            builder.create().show();
        } else {
            ArrayList<Book> alreadyReadBooks = Util.getInstance(this).getAlreadyReadBooks();
            if (checkContains(alreadyReadBooks, incomingBook)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("You've Already Read This Book");
                builder.setTitle("Error");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.create().show();
            } else {
                ArrayList<Book> currentlyReadingBooks = Util.getInstance(this).getCurrentlyReadingBooks();
                if (checkContains(currentlyReadingBooks, incomingBook)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("You're Currently Reading This Book");
                    builder.setTitle("Error");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.create().show();
                } else {
                    Util.getInstance(this).addWantToReadBooks(incomingBook);
                    Toast.makeText(this, "The Book " + incomingBook.getName() + " Added To Want To Read List", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void btnAddAlreadyReadTapped() {
        Log.d(TAG, "btnAddAlreadyReadTapped: Started");

        boolean doesExist = false;

        ArrayList<Book> alreadyReadBooks = Util.getInstance(this).getAlreadyReadBooks();

        if (checkContains(alreadyReadBooks, incomingBook)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("You Already Added this Book to your Already Read List");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setCancelable(true);

            builder.create().show();
        } else {

            ArrayList<Book> currentlyReadingBooks = Util.getInstance(this).getCurrentlyReadingBooks();
            if (checkContains(currentlyReadingBooks, incomingBook)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Have You finished Reading This Book?");
                builder.setTitle("Error");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Util.getInstance(BookActivity.this).removeCurrentlyReadingBooks(incomingBook);
                        Util.getInstance(BookActivity.this).addAlreadyReadBooks(incomingBook);
                        Toast.makeText(BookActivity.this, "The Book " + incomingBook.getName() + " Added To Already Read List", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.create().show();
            } else {

                ArrayList<Book> wantToReadBooks = Util.getInstance(this).getWantToReadBooks();
                if (checkContains(wantToReadBooks, incomingBook)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Have You finished Reading This Book?");
                    builder.setTitle("Error");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Util.getInstance(BookActivity.this).removeWantToReadBooks(incomingBook);
                            Util.getInstance(BookActivity.this).addAlreadyReadBooks(incomingBook);
                            Toast.makeText(BookActivity.this, "The Book " + incomingBook.getName() + " Added To Already Read List", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.create().show();
                } else {
                    Util.getInstance(this).addAlreadyReadBooks(incomingBook);
                    Toast.makeText(this, "The Book " + incomingBook.getName() + " Added To Already Read List", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    private void btnAddCurReadingTapped() {
        Log.d(TAG, "btnAddCurReadingTapped: Started");

        ArrayList<Book> currentlyReadingBooks = Util.getInstance(this).getCurrentlyReadingBooks();
        if (checkContains(currentlyReadingBooks, incomingBook)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("You Already Added this Book to your Currently Reading List");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setCancelable(true);

            builder.create().show();
        } else {

            ArrayList<Book> wantToReadBooks = Util.getInstance(this).getWantToReadBooks();

            if (checkContains(wantToReadBooks, incomingBook)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are You Going To Start Reading This Book?");
                builder.setTitle("Error");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Util.getInstance(BookActivity.this).removeWantToReadBooks(incomingBook);
                        Util.getInstance(BookActivity.this).addCurrentlyReadingBooks(incomingBook);
                        Toast.makeText(BookActivity.this, "The Book " + incomingBook.getName() + " Added To currently Reading List", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.create().show();
            } else {
                    ArrayList<Book> alreadyReadBooks = Util.getInstance(this).getAlreadyReadBooks();
                    if (checkContains(alreadyReadBooks, incomingBook)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Do You Want to Read This Book Again?");
                        builder.setTitle("Error");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Util.getInstance(BookActivity.this).addCurrentlyReadingBooks(incomingBook);
                                Toast.makeText(BookActivity.this, "The Book " + incomingBook.getName() + " Added To currently Reading List", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                    } else {
                        Util.getInstance(this).addCurrentlyReadingBooks(incomingBook);
                        Toast.makeText(this, "The Book " + incomingBook.getName() + " Added To currently Reading List", Toast.LENGTH_SHORT).show();
                    }
            }

        }
    }

    private void initWidgets() {
        bookName = (TextView) findViewById(R.id.bookName);
        authorName = (TextView) findViewById(R.id.authorName);
        description = (TextView) findViewById(R.id.bookDesc);
        pageNumber = (TextView) findViewById(R.id.bookPages);

        bookImage = (ImageView) findViewById(R.id.bookImage);

        btnAddCurReading = (Button) findViewById(R.id.btnAddCurReading);
        btnAddAlreadyRead = (Button) findViewById(R.id.btnAddAlreadyRead);
        btnAddWantToRead = (Button) findViewById(R.id.btnAddWantToRead);
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