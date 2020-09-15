package pl.adamnowicki.fixer.fragment.list.viewholder

import android.view.View
import kotlinx.android.synthetic.main.fixer_list_header.view.*
import pl.adamnowicki.fixer.components.FixerTextView
import pl.adamnowicki.fixer.data.FixerAdapterData
import pl.adamnowicki.fixer.data.HeaderAdapterData


class RatesHeaderViewHolder(
    view: View
) :
    RatesViewHolder(view) {
    var date: FixerTextView = view.date

    override fun bindData(item: FixerAdapterData, position: Int) {

        if (item is HeaderAdapterData) {
            date.text = item.date
        }
    }
}