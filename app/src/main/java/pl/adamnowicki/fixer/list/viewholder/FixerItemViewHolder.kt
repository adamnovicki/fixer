package pl.adamnowicki.fixer.list.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fixer_list_item.view.*
import pl.adamnowicki.fixer.components.FixerTextView
import pl.adamnowicki.fixer.data.FixerAdapterData
import pl.adamnowicki.fixer.data.FixerItem
import pl.adamnowicki.fixer.data.HeaderAdapterData
import pl.adamnowicki.fixer.data.ItemAdapterData


class FixerItemViewHolder(
    view: View
) :
    FixerViewHolder(view) {
    var currency: FixerTextView = view.currency
    var value: FixerTextView = view.value

    override fun bindData(item: FixerAdapterData, position: Int) {

        if (item is ItemAdapterData) {
            currency.text = item.currency
            value.text = item.value

        }
    }
}