package org.sopt.sample.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityHomeBinding
import org.sopt.sample.fragment.GalleryFragment
import org.sopt.sample.fragment.HomeFragment
import org.sopt.sample.fragment.SearchFragment

class HomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override  fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayFragment()
    }

    private fun displayFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.home_container, HomeFragment())
            .commit()

        binding.bnvHome.setOnItemSelectedListener {
            changeFragment(
                when(it.itemId){
                    R.id.home_menu -> HomeFragment()
                    R.id.gallery_menu -> GalleryFragment()
                    else -> SearchFragment()
                }
            )
            true
        }
        binding.bnvHome.selectedItemId = R.id.home_menu
    }

    private fun changeFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.home_container,fragment)
            .commit()
    }

}