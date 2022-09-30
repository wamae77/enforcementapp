package com.example.enforcmemntapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.enforcmemntapp.data.source.models.BusinessesDataModel
import com.example.enforcmemntapp.data.source.models.ParkingDataModel
import com.example.enforcmemntapp.ui.business.BusinessFragment
import com.example.enforcmemntapp.ui.main.ParkingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ParkingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking)
        val p = intent.getParcelableExtra<ParkingDataModel>("INFO")
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<ParkingFragment>(R.id.container, args = Bundle().apply { this.putParcelable("INFO",p) })
            }
        }
    }
}