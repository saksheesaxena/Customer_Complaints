package com.example.admin.customer_complaints.Activities;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin.customer_complaints.R;

/**
 * Created by Sakshee on 18-Aug-15.
 */

        public abstract class BaseActivity extends AppCompatActivity

            {
                private Toolbar toolbar;
                @Override
           protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_choice_selection);
                    setContentView(getLayoutResource());
                    toolbar = (Toolbar) findViewById(R.id.tool_bar);


                }

                   protected abstract int getLayoutResource();

                    protected void setActionBarIcon(int iconRes) {
                    toolbar.setNavigationIcon(iconRes);
                }

                    public void setActionBarTitle(int title) {
                    getSupportActionBar().setTitle(title);
               }

                           public void setActionBarTitle(String title) {
                    getSupportActionBar().setTitle(title);
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

