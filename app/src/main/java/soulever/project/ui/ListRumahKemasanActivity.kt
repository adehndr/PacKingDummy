package soulever.project.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import soulever.project.adapter.RumahKemasanAdapter
import soulever.project.databinding.ActivityListRumahKemasanBinding
import soulever.project.entity.RumahKemasan
import soulever.project.ui.ViewModel.RumahKemasanTerdekatViewModel
import soulever.project.ui.ViewModel.RumahKemasanViewModel

class ListRumahKemasanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListRumahKemasanBinding
    private lateinit var rumahKemasanViewModel : RumahKemasanViewModel
    private lateinit var rumahKemasanTerdekatViewModel: RumahKemasanTerdekatViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListRumahKemasanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showRecyclerViewRumahKemasan()
        showRecyclerViewRumahKemasanTerdekat()
    }

    private fun showRecyclerViewRumahKemasan()
    {
        val rumahKemasanAdapter = RumahKemasanAdapter()
        rumahKemasanViewModel = ViewModelProvider(this).get(RumahKemasanViewModel::class.java)
        val listRumahKemasan = rumahKemasanViewModel.getRumahKemasan()
        rumahKemasanAdapter.setRumahKemasan(listRumahKemasan)
        with(binding.rvListRumahKemasan)
        {
            layoutManager = LinearLayoutManager(this@ListRumahKemasanActivity)
            setHasFixedSize(true)
            adapter = rumahKemasanAdapter

        }
    }

    private fun showRecyclerViewRumahKemasanTerdekat()
    {
        val rumahKemasanTerdekatAdapter = RumahKemasanAdapter()
        rumahKemasanTerdekatViewModel = ViewModelProvider(this).get(RumahKemasanTerdekatViewModel::class.java)
        val listRumahKemasanTerdekat = rumahKemasanTerdekatViewModel.getRumahKemasanTerdekat()
        rumahKemasanTerdekatAdapter.setRumahKemasan(listRumahKemasanTerdekat)
        with(binding.rvListRumahKemasanTerdekat)
        {
            layoutManager = LinearLayoutManager(this@ListRumahKemasanActivity)
            setHasFixedSize(true)
            adapter = rumahKemasanTerdekatAdapter
        }
    }
}