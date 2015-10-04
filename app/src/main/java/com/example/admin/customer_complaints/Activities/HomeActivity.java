package com.example.admin.customer_complaints.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.admin.customer_complaints.Adapters.HomeViewAdapter;

import com.example.admin.customer_complaints.R;


public class HomeActivity extends BaseActivity implements HomeViewAdapter.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private HomeViewAdapter mAdapter;
    private Toolbar toolbar;

    String complaint,track,language,about;

    String[] home_text;
    int[] image;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
         //   getSupportActionBar().setTitle("Jan Seva");
        complaint = getString(R.string.complaint);
        track = getString(R.string.track);
        language = getString(R.string.language);
        about = getString(R.string.about);

        home_text = new String[]{
                complaint, track,
                language , about
        } ;
        image = new int[]{
                R.mipmap.home_complaint, R.mipmap.home_track,
                R.mipmap.home_language, R.mipmap.home_about
        };
        setContentView(R.layout.final_activity_home);
        mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        mAdapter = new HomeViewAdapter(home_text,image);
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
            }
          else if(home_text[position].equals(language)) {
                {
                    Intent language = new Intent(HomeActivity.this, LanguageSelectorActivity.class);
                    startActivity(language);
                }
            }
            else if(home_text[position].equals(about))
            {
                Intent about  = new Intent(HomeActivity.this, ExIfActivity.class);
                startActivity(about);
            }

                else
                {
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

        return super.onOptionsItemSelected(item);
    }






}
























