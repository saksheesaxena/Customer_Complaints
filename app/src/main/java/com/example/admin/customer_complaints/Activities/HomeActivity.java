package com.example.admin.customer_complaints.Activities;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.customer_complaints.Adapters.HomeViewAdapter;

import com.example.admin.customer_complaints.R;
import com.example.admin.customer_complaints.library.LoginDataBaseAdapter;


public class HomeActivity extends BaseActivity implements HomeViewAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private HomeViewAdapter mAdapter;


    String complaint, track, language, about;

    String[] home_text;
    int[] image;
    public String selected_ulb;






    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            selected_ulb = extras.getString("SELECTED_ULB");

        }
        // create a instance of SQLite Database



        //   getSupportActionBar().setTitle("Jan Seva");
        complaint = getString(R.string.complaint);
        track = getString(R.string.track);
        language = getString(R.string.language);
        about = getString(R.string.about);

        home_text = new String[]{
                complaint, track,
                language, about
        };
        image = new int[]{
                R.mipmap.home_complaint, R.mipmap.home_track,
                R.mipmap.home_language, R.mipmap.home_about
        };
        setContentView(R.layout.final_activity_home);

        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mAdapter = new HomeViewAdapter(home_text, image);
        mAdapter.SetOnItemClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.homeGrid);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    protected int getLayoutResource() {
        return R.layout.final_activity_home;
    }


    @Override
    public void onItemClick(View view, int position) {

        if (home_text[position].equals(complaint)) {
            Intent complaint = new Intent(HomeActivity.this, ChoiceSelectionActivity.class);
            startActivity(complaint);
        } else if (home_text[position].equals(language)) {
            {
                Intent language = new Intent(HomeActivity.this, LanguageSelectorActivity.class);
                startActivity(language);
            }
        } else if (home_text[position].equals(about)) {
            Intent about = new Intent(HomeActivity.this, ExIfActivity.class);
            startActivity(about);
        } else {
            Toast.makeText(this, "Module under construction", Toast.LENGTH_SHORT).show();
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
            return true;
        }
       if (id == R.id.action_home)
        {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(R.string.change_ulb);
            alertDialogBuilder
                    .setMessage("Your current ULB is  " + selected_ulb)
                    .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new MaterialDialog.Builder(HomeActivity.this)
                                    .dividerColorRes(R.color.colorPrimary)
                                    .title(R.string.select_ulb)
                                    .items(R.array.ulb_names)
                                    .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallback() {
                                        @Override
                                        public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            Intent intent =  new Intent(HomeActivity.this, HomeActivity.class);
                                            intent.putExtra("SELECTED_ULB",text);
                                            startActivity(intent);

                                        }
                                    })
                                    .positiveText("Ok")
                                    .show();

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog= alertDialogBuilder.create();
            alertDialog.show();

        }

        return super.onOptionsItemSelected(item);
    }


}






















