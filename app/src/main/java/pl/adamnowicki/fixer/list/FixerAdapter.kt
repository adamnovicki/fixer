package pl.adamnowicki.fixer.list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.adamnowicki.fixer.R
import pl.adamnowicki.fixer.data.FixerAdapterData
import pl.adamnowicki.fixer.data.HeaderAdapterData
import pl.adamnowicki.fixer.data.ItemAdapterData
import pl.adamnowicki.fixer.list.viewholder.FixerHeaderViewHolder
import pl.adamnowicki.fixer.list.viewholder.FixerItemViewHolder
import pl.adamnowicki.fixer.list.viewholder.FixerLoadingViewHolder
import pl.adamnowicki.fixer.list.viewholder.FixerViewHolder

class FixerAdapter(
    var listData: MutableList<FixerAdapterData>,
    private val rateClickListener: (FixerAdapterData) -> Unit
) : RecyclerView.Adapter<FixerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false) as ViewGroup
        val viewHolder: FixerViewHolder

        viewHolder = when (viewType) {
            R.layout.fixer_list_item -> FixerItemViewHolder(
                view
            )
            R.layout.fixer_list_loading -> FixerLoadingViewHolder(view)

            else -> FixerHeaderViewHolder(
                view
            )
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: FixerViewHolder, position: Int) {
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

