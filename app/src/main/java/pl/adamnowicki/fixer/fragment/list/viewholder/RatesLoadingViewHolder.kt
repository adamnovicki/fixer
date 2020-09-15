package pl.adamnowicki.fixer.fragment.list.viewholder

import android.view.View
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.fixer_list_loading.view.*
import pl.adamnowicki.fixer.data.FixerAdapterData


class RatesLoadingViewHolder(
    view: View
) :
    RatesViewHolder(view) {
    var progressBar: ProgressBar = view.progressBar

    override fun bindData(item: FixerAdapterData, position: Int) {
    }
}