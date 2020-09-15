package pl.adamnowicki.fixer.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.list_fragment.*
import pl.adamnowicki.fixer.R
import pl.adamnowicki.fixer.data.FixerAdapterData
import pl.adamnowicki.fixer.data.ItemAdapterData
import pl.adamnowicki.fixer.data.LoadingAdapterData
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class ListFragment  @Inject constructor() : Fragment(R.layout.list_fragment) {

    private val viewModel: ListViewModel by viewModels()

    private var adapter: FixerAdapter? = null
    private var recyclerLayoutManager: RecyclerView.LayoutManager? = null

    var listSize = 0
    var isLoading = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fixerLiveData.observe(viewLifecycleOwner, Observer(::onFixerDataReceived))
        recyclerLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = recyclerLayoutManager
        initScrollListener()

        viewModel.getNextDay()
    }

    private fun onFixerDataReceived(fixerData: MutableList<FixerAdapterData>) {
        Timber.d("onFixerDataReceived")

        if (adapter == null) {
            adapter = FixerAdapter(fixerData) {
                if (it is ItemAdapterData) {
                    Timber.d("click ${it.currency}")


                }
            }
            recyclerView.adapter = adapter
        } else {
            adapter?.listData = fixerData
            adapter?.notifyDataSetChanged()
        }
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
                        adapter?.notifyItemInserted(listSize - 1);

                        Timber.d("load more data")
                        viewModel.getNextDay()
                        isLoading = true
                    }
                }
            }
        })
    }
}