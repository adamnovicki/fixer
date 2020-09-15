package pl.adamnowicki.fixer.list.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fixer_list_item.view.*
import kotlinx.android.synthetic.main.fixer_list_loading.view.*
import pl.adamnowicki.fixer.components.FixerTextView
import pl.adamnowicki.fixer.data.FixerAdapterData
import pl.adamnowicki.fixer.data.FixerItem
import pl.adamnowicki.fixer.data.HeaderAdapterData
import pl.adamnowicki.fixer.data.ItemAdapterData


class FixerLoadingViewHolder(
    view: View
) :
    FixerViewHolder(view) {
    var progressBar: ProgressBar = view.progressBar

    override fun bindData(item: FixerAdapterData, position: Int) {
    }
}