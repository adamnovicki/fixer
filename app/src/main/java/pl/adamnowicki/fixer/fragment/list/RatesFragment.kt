package pl.adamnowicki.fixer.fragment.list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.adamnowicki.fixer.R
import pl.adamnowicki.fixer.data.FixerAdapterData
import pl.adamnowicki.fixer.data.ItemAdapterData
import pl.adamnowicki.fixer.data.LoadingAdapterData
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class RatesFragment @Inject constructor() : Fragment(R.layout.list_fragment) {

    private val viewModel: RatesViewModel by viewModels()

    private var adapter: RatesAdapter? = null
    private var recyclerLayoutManager: RecyclerView.LayoutManager? = null

    var listSize = 0
    var isLoading = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.fixerLiveData.observe(viewLifecycleOwner, Observer(::onFixerDataReceived))
        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer(::onErrorReceived))
        recyclerLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = recyclerLayoutManager
        initScrollListener()

        adapter = RatesAdapter(mutableListOf()) {
            if (it is ItemAdapterData) {
                Timber.d("click ${it.currency}")

                val bundle = bundleOf(
                    "currency" to it.currency,
                    "value" to it.value,
                    "date" to it.date
                )
                view?.findNavController()?.navigate(R.id.detailsFragment, bundle)
            }
        }
        recyclerView.adapter = adapter

        if (listSize == 0) {
            viewModel.getNextDay()
        }
    }

    private fun onErrorReceived(error: Boolean) {
        if (error) {
            errorTv.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }

    private fun onFixerDataReceived(fixerData: MutableList<FixerAdapterData>) {
        Timber.d("onFixerDataReceived")

        errorTv.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE

        adapter?.listData = fixerData
        adapter?.notifyDataSetChanged()

        isLoading = false
        listSize = fixerData.size
    }

    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastVisibleItemPosition() == listSize - 1) {

                        Timber.d("add loading item")
                        adapter?.listData?.add(LoadingAdapterData(true))
                        listSize++

                        lifecycleScope.launch(Dispatchers.Main) {
                            adapter?.notifyItemInserted(listSize - 1);
                        }

                        Timber.d("load more data")
                        viewModel.getNextDay()
                        isLoading = true
                    }
                }
            }
        })
    }
}