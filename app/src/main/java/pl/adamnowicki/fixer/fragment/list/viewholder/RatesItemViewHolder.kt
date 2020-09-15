package pl.adamnowicki.fixer.fragment.list.viewholder

import android.view.View
import kotlinx.android.synthetic.main.fixer_list_item.view.*
import pl.adamnowicki.fixer.components.FixerTextView
import pl.adamnowicki.fixer.data.FixerAdapterData
import pl.adamnowicki.fixer.data.ItemAdapterData


class RatesItemViewHolder(
    view: View
) :
    RatesViewHolder(view) {
    var currency: FixerTextView = view.currency
    var value: FixerTextView = view.value

    override fun bindData(item: FixerAdapterData, position: Int) {

        if (item is ItemAdapterData) {
            currency.text = item.currency
            value.text = item.value

        }
    }
}