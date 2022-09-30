package com.example.enforcmemntapp.ui.login.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.enforcmemntapp.MainActivity
import com.example.enforcmemntapp.R
import com.example.enforcmemntapp.data.source.remote.models.Parkingstreet
import com.example.enforcmemntapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(), View.OnClickListener, AdapterView.OnItemClickListener {

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!


    private val viewModel:LoginViewModel by viewModels()
    private var adapter:ArrayAdapter<String>?=null
    private var list:List<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogin.setOnClickListener(this)

        spinner()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect() {
                    it.list?.let { l->   showList(l) }
                    navigate(it.navigate)
                }
            }
        }
    }

    private fun showList(l: List<Parkingstreet>) {
        if (l.isNotEmpty()) {
            val myList:MutableList<String> = mutableListOf()
            for (i in l){
                myList.add(i.StreetName)
            }
            list = myList
            adapter = ArrayAdapter(requireContext(),R.layout.list_item, list)
            (binding.streetEd as? AutoCompleteTextView)?.setAdapter(adapter)
            adapter?.notifyDataSetChanged()
            binding.streetEd.onItemClickListener =this
        }
    }

    private fun spinner() {
        adapter = ArrayAdapter(requireContext(),R.layout.list_item, list)
        (binding.streetEd as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun navigate(navigate: Boolean) {
        if (navigate){
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            viewModel.hasNavigated()
        }
    }

    override fun onClick(p0: View?) {
        if (p0!!.id == R.id.button_login) {
            val email = binding.edUserName.text?.trim().toString()
            val password = binding.edPassword.text?.trim().toString()
            if (email.isEmpty()||password.isEmpty()){
                return
            }
            viewModel.login(email, password)
        }
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        viewModel.setStreet(list[p2])
    }


}