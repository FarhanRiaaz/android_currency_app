package com.swipebox.converter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.swipebox.converter.R
import com.swipebox.converter.databinding.FragmentRateListBinding
import com.swipebox.converter.ui.adapter.CurrencyAdapter
import com.swipebox.converter.util.Constants
import com.swipebox.converter.util.MyExtension.convertMapToRateItems
import com.swipebox.converter.viewModel.CurrencyConverterViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RateListFragment : Fragment() {

    private val viewModel: CurrencyConverterViewModel by sharedViewModel()

    private lateinit var binding: FragmentRateListBinding

    private var listAdapter: CurrencyAdapter? = null

    private fun observeViewModel() {
        viewModel.exchangeRateData.observe(this, Observer { data ->
            binding.tvInternetConnection.visibility = View.INVISIBLE
            binding.mRecyclerView.visibility = View.VISIBLE
            listAdapter?.updateList(convertMapToRateItems(data.conversion_rates))
        })
        viewModel.errorLiveData.observe(this) {
            binding.tvInternetConnection.visibility = View.VISIBLE
            binding.mRecyclerView.visibility = View.INVISIBLE
        }
    }

    private fun initUI() {
        bindAdapter()
        binding.editText.doAfterTextChanged {

            if (it.isNullOrBlank() || (it.toString().toFloatOrNull()
                    ?: 0.0f) == 0.0f
            ) listAdapter?.updateMultiplyer(1)
            else listAdapter?.updateMultiplyer(Integer.parseInt(it.toString()))
        }
    }

    private fun bindAdapter() {
        listAdapter = CurrencyAdapter(arrayListOf())
        binding.mRecyclerView.adapter = listAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rate_list, container, false)
        initUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        observeViewModel()
        viewModel.fetchExchangeRates(Constants.API_KEY)

    }
}