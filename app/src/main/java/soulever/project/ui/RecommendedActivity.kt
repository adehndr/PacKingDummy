package soulever.project.ui

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import me.relex.circleindicator.CircleIndicator2
import soulever.project.adapter.RecommendedAdapter
import soulever.project.databinding.ActivityRecommendedBinding
import soulever.project.entity.Recommended
import soulever.project.ui.ViewModel.RekomendasiViewModel


class RecommendedActivity : AppCompatActivity() {
    companion object{
        const val RECOMMENDED_ID = "id"
    }
    private lateinit var recommendedActvityViewModel : RekomendasiViewModel
    lateinit var context: Context
    private lateinit var binding : ActivityRecommendedBinding

    private var orderList : List<Recommended> = ArrayList()
    private lateinit var adapter: RecommendedAdapter
    private var n = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecommendedAdapter()
        adapter.notifyDataSetChanged()
        var idRekomendasi : String?= intent.getStringExtra(RECOMMENDED_ID)

        binding.rvRecommended.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.rvRecommended.adapter = adapter
        val pagerSnapHelper = PagerSnapHelper()

        recommendedActvityViewModel = ViewModelProvider(this).get(RekomendasiViewModel::class.java)
        if (idRekomendasi != null) {
            recommendedActvityViewModel.getUser(idRekomendasi)?.observe(this, {
                orderList = it
                adapter.setRecommendedList(it)
                pagerSnapHelper.attachToRecyclerView(binding.rvRecommended)
                binding.indicator.attachToRecyclerView(binding.rvRecommended, pagerSnapHelper)
                adapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver)
            })
        }

    }

    override fun onBackPressed() {
        val intent = Intent(this@RecommendedActivity, MainActivity::class.java)
        startActivity(intent)
    }

}