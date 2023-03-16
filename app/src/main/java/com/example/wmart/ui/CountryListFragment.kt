package com.example.wmart.ui

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wmart.R
import com.example.wmart.repository.CountryListRepository
import com.example.wmart.ui.adapter.CountryListAdapter
import com.example.wmart.util.Response
import com.example.wmart.viewmodel.CountryListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class CountryListFragment : Fragment() {
    companion object {
        val TAG = "CountryListFragment"
    }
    lateinit var viewModel : CountryListViewModel
    lateinit var rvAdapter: CountryListAdapter
    lateinit var progressBar : ProgressBar
   // lateinit var parcelableState : Parcelable
    lateinit var recyclerView : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpProgressBar()

        val repository = CountryListRepository()
        val factory = CountryListViewModel.CountryListFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(CountryListViewModel::class.java)
        viewModel.getCountries()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel._countryStateFlow.collect() {
                    when(it) {
                        is Response.Error -> {
                            hideLoader()
                            //implment later error view
                            Log.e(TAG, it.msg)
                        }
                        is Response.Loaded -> {
                            hideLoader()
                            rvAdapter.submitList(it.list)
                        }
                        is Response.Loading -> {
                            showLoader()
                        }
                        else -> {}
                    }
                }
            }
        }

    }

    private fun setUpProgressBar() {
        progressBar = requireView().findViewById(R.id.progressbar)
    }

    private fun hideLoader() {
        progressBar.visibility = View.GONE

    }

    private fun showLoader() {
        progressBar.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView() {
        rvAdapter = CountryListAdapter()
        rvAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        recyclerView =  requireView().findViewById<RecyclerView>(R.id.rvCountries)
        recyclerView.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

}