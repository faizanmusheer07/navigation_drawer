package com.example.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.navigationdrawer.databinding.ActivityMainBinding
import com.example.navigationdrawer.fragment.AboutAppFragment
import com.example.navigationdrawer.fragment.HomeFragment
import com.example.navigationdrawer.fragment.SettingFragment
import com.example.navigationdrawer.fragment.ShareFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolBar)


        binding.navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolBar,
            R.string.open,
            R.string.close)

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            binding.navigationView.setCheckedItem(R.id.nav_home)
        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> replaceFragment(HomeFragment())
            R.id.nav_setting -> replaceFragment(SettingFragment())
            R.id.nav_share -> replaceFragment(ShareFragment())
            R.id.nav_about -> replaceFragment(AboutAppFragment())
            R.id.nav_layout -> Toast.makeText(this, "LOG OUT", Toast.LENGTH_LONG).show()

        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true

    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else
            onBackPressedDispatcher.onBackPressed()

    }

}