package com.example.enforcmemntapp.ui.business

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
import com.example.enforcmemntapp.databinding.FragmentBusinessBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BusinessFragment : Fragment() {

    private var _binding: FragmentBusinessBinding? = null
    private val binding
        get() = _binding!!

    companion object {
        fun newInstance() = BusinessFragment()
    }

    private val viewModel: BusinessViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val arg = this.arguments
        val data = arg?.getParcelable<BusinessesDataModel>("INFO")
        viewModel.setArguments(data)

        _binding = FragmentBusinessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect() {
                    it.businessesDataModel?.let { item -> updateUi(item) }
                }

            }
        }
    }

    private fun updateUi(item: BusinessesDataModel) {
        binding.businessId.setText(item.BusinessID)
        binding.businessName.setText(item.BusinessName)
        binding.owner.setText(item.ContactPersonName)
        binding.businessActCode.setText(item.BusinessActivityDescription)
        binding.detailDescription.setText(item.ActivityDescription)
        binding.startDate.setText(item.StartDate)
        binding.pobox.setText(item.POBox)
        binding.krapin.setText(item.PINNumber)
        binding.edAddress.setText(item.PhysicalAddress)
        binding.endDate.setText(item.EndDate)
        binding.edAmount.setText(item.AmountPaid)
        binding.edPlotNummber.setText(item.PlotNumber)
        binding.btnStatus.setText(item.Status)

    }


}