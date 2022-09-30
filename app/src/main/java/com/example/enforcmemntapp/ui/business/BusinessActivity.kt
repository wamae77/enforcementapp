package com.example.enforcmemntapp.ui.business

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.enforcmemntapp.R
import com.example.enforcmemntapp.data.source.models.BusinessesDataModel
import com.example.enforcmemntapp.databinding.ActivityBusinessBinding
import com.example.enforcmemntapp.databinding.ActivityMainBinding
import com.example.enforcmemntapp.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusinessActivity : AppCompatActivity() {

     private lateinit var binding: ActivityBusinessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusinessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val p = intent.getParcelableExtra<BusinessesDataModel>("INFO")
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<BusinessFragment>(R.id.fragment_container_view, args = Bundle().apply { this.putParcelable("INFO",p) })
            }
        }

    }
}