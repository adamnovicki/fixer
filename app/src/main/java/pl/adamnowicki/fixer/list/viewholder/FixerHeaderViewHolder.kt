package pl.adamnowicki.fixer.list.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fixer_list_header.view.*
import kotlinx.android.synthetic.main.fixer_list_item.view.*
import pl.adamnowicki.fixer.components.FixerTextView
import pl.adamnowicki.fixer.data.FixerAdapterData
import pl.adamnowicki.fixer.data.FixerItem
import pl.adamnowicki.fixer.data.HeaderAdapterData
import pl.adamnowicki.fixer.data.ItemAdapterData


class FixerHeaderViewHolder(
    view: View
) :
    FixerViewHolder(view) {
    var date: FixerTextView = view.date

    override fun bindData(item: FixerAdapterData, position: Int) {

        if (item is HeaderAdapterData) {
            date.text = item.date
        }
    }
}