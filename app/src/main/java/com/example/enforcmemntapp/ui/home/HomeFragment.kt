package com.example.enforcmemntapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.enforcmemntapp.ParkingActivity
import com.example.enforcmemntapp.R
import com.example.enforcmemntapp.data.source.remote.models.CheckbusinessResponse
import com.example.enforcmemntapp.data.source.remote.models.ParkingResponse
import com.example.enforcmemntapp.data.source.toBusinessDataModel
import com.example.enforcmemntapp.data.source.toParkingDetailsModel
import com.example.enforcmemntapp.databinding.FragmentHomeBinding
import com.example.enforcmemntapp.ui.business.BusinessActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), View.OnClickListener {

    private val viewModel: HomeViewModel by viewModels()
    private val bottomSheetViewReceipt by lazy { binding.bottomSheet.bottomSheet }
    private val bottomSheetViewBusiness by lazy { binding.bottomSheetBusiness.bottomSheetBusiness }
    private val bottomSheetViewQueryPlate by lazy { binding.bottomSheetPlate.bottomSheetPlate }


    private lateinit var bottomSheetBehaviorReceipt: BottomSheetBehavior<ConstraintLayout>
    private lateinit var bottomSheetBehaviorBusiness: BottomSheetBehavior<ConstraintLayout>
    private lateinit var bottomSheetBehaviorPlate: BottomSheetBehavior<ConstraintLayout>

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.materialReceipt.setOnClickListener(this)
        binding.cardBusiness.setOnClickListener(this)
        binding.queryVehicle.setOnClickListener(this)
        binding.parking.setOnClickListener(this)

        binding.bottomSheet.btnSearch.setOnClickListener(this)
        binding.bottomSheetPlate.btnSearchPlate.setOnClickListener(this)
        binding.bottomSheetBusiness.btnSearchBusiness.setOnClickListener(this)

        bottomSheetBehaviorReceipt = BottomSheetBehavior.from(bottomSheetViewReceipt)
        bottomSheetBehaviorBusiness = BottomSheetBehavior.from(bottomSheetViewBusiness)
        bottomSheetBehaviorPlate = BottomSheetBehavior.from(bottomSheetViewQueryPlate)

        bottomSheetBehaviorBusiness.isDraggable=true
        bottomSheetBehaviorReceipt.isDraggable = true
        bottomSheetBehaviorPlate.isDraggable=true

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect() {
                    it.message?.let { msg -> showMessage(msg) }
                    it.parkingResponse?.let {p-> showParking(p) }
                    loading(it.isLoading)
                    it.checkbusinessResponse?.let {info->showInfo(info)  }
                }
            }
        }
    }

    private fun showParking(p: ParkingResponse) {
        val intent = Intent(requireContext(),ParkingActivity::class.java)
        intent.putExtra("INFO",p.response_data.parkingdata.toParkingDetailsModel())
        startActivity(intent)
        viewModel.hasNavigated()
    }

    private fun showInfo(info: CheckbusinessResponse) {
        val intent = Intent(requireContext(),BusinessActivity::class.java)
        intent.putExtra("INFO",info.toBusinessDataModel())
        startActivity(intent)
        viewModel.hasNavigated()

    }

    private fun loading(loading: Boolean) {

    }

    private fun showMessage(msg: Int) {

        binding.bottomSheet.status.visibility =View.VISIBLE
        binding.bottomSheet.status.text = getString(msg)

//        viewModel.messageShown()
    }

    override fun onClick(p0: View?) {
        if (p0!!.id == R.id.materialReceipt) {
            bottomSheetBehaviorReceipt.state = BottomSheetBehavior.STATE_EXPANDED
        }
        if (p0.id == R.id.btnSearch) {
            val string = binding.bottomSheet.edReceiptNummber.text.toString()
            if (string.isEmpty()) {
                Toast.makeText(context, "Empty Field", Toast.LENGTH_SHORT).show()
                return
            }
            viewModel.queryByReceiptNumber(string)
        }
        if (p0.id ==R.id.cardBusiness){
            bottomSheetBehaviorBusiness.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }
        if (p0.id == R.id.btnSearchBusiness){
            val string = binding.bottomSheetBusiness.edBusinessNumber.text.toString()
            if (string.isEmpty()) {
                Toast.makeText(context, "Empty Field", Toast.LENGTH_SHORT).show()
                return
            }
            viewModel.businessValidity(string)
        }
        if (p0.id == R.id.parking){
            bottomSheetBehaviorPlate.state= BottomSheetBehavior.STATE_HALF_EXPANDED
        }
        if (p0.id==R.id.btnSearchPlate){
            val string = binding.bottomSheetPlate.edPlate.text.toString()
            if (string.isEmpty()) {
                Toast.makeText(context, "Empty Field", Toast.LENGTH_SHORT).show()
                return
            }
            viewModel.queryParkingByPlate(string)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}