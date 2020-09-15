package pl.adamnowicki.fixer.fragment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.adamnowicki.fixer.R
import pl.adamnowicki.fixer.data.FixerAdapterData
import pl.adamnowicki.fixer.data.HeaderAdapterData
import pl.adamnowicki.fixer.data.ItemAdapterData
import pl.adamnowicki.fixer.fragment.list.viewholder.RatesHeaderViewHolder
import pl.adamnowicki.fixer.fragment.list.viewholder.RatesItemViewHolder
import pl.adamnowicki.fixer.fragment.list.viewholder.RatesLoadingViewHolder
import pl.adamnowicki.fixer.fragment.list.viewholder.RatesViewHolder

class RatesAdapter(
    var listData: MutableList<FixerAdapterData>,
    private val rateClickListener: (FixerAdapterData) -> Unit
) : RecyclerView.Adapter<RatesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false) as ViewGroup
        val viewHolder: RatesViewHolder

        viewHolder = when (viewType) {
            R.layout.fixer_list_item -> RatesItemViewHolder(
                view
            )
            R.layout.fixer_list_loading -> RatesLoadingViewHolder(view)

            else -> RatesHeaderViewHolder(
                view
            )
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        val item = listData[position]
        holder.bindData(item, position)
        holder.itemView.setOnClickListener { rateClickListener(item) }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (listData[position] is ItemAdapterData) {
            R.layout.fixer_list_item
        } else if (listData[position] is HeaderAdapterData) {
            R.layout.fixer_list_header
        } else {
            R.layout.fixer_list_loading
        }
    }

}

