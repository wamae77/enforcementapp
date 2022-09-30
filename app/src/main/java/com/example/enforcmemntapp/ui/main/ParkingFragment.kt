package com.example.enforcmemntapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.enforcmemntapp.data.source.models.BusinessesDataModel
import com.example.enforcmemntapp.data.source.models.ParkingDataModel
import com.example.enforcmemntapp.databinding.FragmentParkingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ParkingFragment : Fragment() {

    private var _binding: FragmentParkingBinding? = null
    private val binding
        get() = _binding!!

    companion object {
        fun newInstance() = ParkingFragment()
    }

    private val viewModel: ParkingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val arg = this.arguments
        val data = arg?.getParcelable<ParkingDataModel>("INFO")
            viewModel.setArguments(data)

        _binding = FragmentParkingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect(){
                    it.parkingDataModel?.let { p->showINfo(p) }
                }
            }
        }
    }

    private fun showINfo(p: ParkingDataModel) {
        binding.plateNumber.setText(p.VehicleRegistration)
        binding.parkingDuration.setText(p.Duration)
        binding.vCate.setText(p.Category)
        binding.startDate.setText(p.StartDate)
        binding.edAmount.setText(p.AmountPaid.toString())
        binding.endDate.setText(p.EndDate)
        binding.edPrk.setText(p.ParkingFee.toString())
        binding.edPenalty.setText(p.BillAmount.toString())
        binding.edPayDate.setText(p.PaidDate)

    }

}