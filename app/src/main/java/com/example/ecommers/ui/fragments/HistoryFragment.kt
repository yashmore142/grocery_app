package com.example.ecommers.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommers.databinding.FragmentProfileBinding
import com.example.ecommers.data.historymodel.HistoryRoomData
import com.example.ecommers.ui.adapter.HistoryAdapter
import com.example.ecommers.ui.viewmodel.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.util.UUID
import kotlin.random.Random

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var mBinding: FragmentProfileBinding
    private val mhistoryViewModel by viewModels<HistoryViewModel>()
    private lateinit var historyAdapter: HistoryAdapter
    var layoutManager: LinearLayoutManager? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentProfileBinding.inflate(layoutInflater)
        return mBinding.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        mBinding.rvHistory.layoutManager = layoutManager
        setObserver()
        mBinding.llTitle.setOnClickListener {
            mhistoryViewModel.addHistory(generateRandomHistoryRoomData(generateRandomNumber(1 , 100)))

        }


        mhistoryViewModel.getHistory()

    }
    fun generateRandomNumber(min: Int, max: Int): Int {
        return Random.nextInt(min, max + 1)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateRandomHistoryRoomData(id: Int): HistoryRoomData {

        val randomDate = LocalDate.now().minusDays(Random.nextLong(0, 365)).toString()
        val randomCompleted = Random.nextBoolean()
        val randomOrderId = UUID.randomUUID().toString()

        return HistoryRoomData(
            id = id,
            date = randomDate,
            status = randomCompleted,
            orderId = generateOrderId()
        )
    }

    fun generateOrderId(): String {

        val randomDigits = Random.nextInt(10000000, 99999999)
        return "#W$randomDigits"
    }

    private fun setObserver() {
        mhistoryViewModel.responseGetHistory.observe(viewLifecycleOwner){
            if (!it.isEmpty()){

                historyAdapter = HistoryAdapter(requireContext(),it)
                mBinding.rvHistory.adapter = historyAdapter
            }

        }
    }
}