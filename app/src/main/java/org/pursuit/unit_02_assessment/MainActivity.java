package org.pursuit.unit_02_assessment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private EditText inputEditText;
    private Button submitButton;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView textView = findViewById(R.id.info_textview);
        fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("send to", "mail.pursuit.org", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email from Pursuit");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "This is my text!");
                startActivity(Intent.createChooser(emailIntent, "send to"));

            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final RandomGame randomGame = new RandomGame();
        final int random = randomGame.getRandomNumber();

        Button submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.number_edittext);
                if (editText.getText().equals(randomGame.getStringResult(true))) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("result", "You have guessed correctly!");
                } else {


                    Intent textViewIntent = new Intent(textView.getText().toString());
                    textViewIntent.putExtra("lost", "Wrong guess - please try again!");

                }


            }


        });



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.getString("saveTextView", textView);
//        String saveTextView
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_toast) {

            Toast toast = Toast.makeText(getApplicationContext(), "Hello, Pursuit!", Toast.LENGTH_SHORT);
            toast.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_phone:
                Uri phoneUri = Uri.parse("tel:2125551212");
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, phoneUri);
                startActivity(dialIntent);
                break;
            case R.id.nav_sms:
                Uri smsUri = Uri.parse("sms:2125551212");
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, smsUri);
                startActivity(smsIntent);
                break;
            case R.id.nav_map_location:
                Uri navUri = Uri.parse("0,0?q=40.7429595,-73.94192149999998(Pursuit Android HQ)");
                Intent mapsIntent = new Intent(Intent.ACTION_VIEW, navUri);
                startActivity(mapsIntent);
                break;
            default:
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
