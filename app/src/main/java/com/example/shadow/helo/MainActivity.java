package com.example.shadow.helo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shadow.helo.Adapters.FragmentAdapter;
import com.example.shadow.helo.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

/*
    @Abhishek Goyal
    @Helo! A Chat Application
*/

public class MainActivity extends AppCompatActivity
{
    ActivityMainBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();

        binding.viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        binding.tablayout.setupWithViewPager(binding.viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                 break;
            case R.id.logout:
                 auth.signOut();
                 Intent intent=new Intent(MainActivity.this,SigninActivity.class);
                 startActivity(intent);
                 break;
            case R.id.groupChat:
                Intent intentt = new Intent(MainActivity.this , GroupChatActivity.class);
                startActivity(intentt);
                break;
        }
        return true;
    }
}