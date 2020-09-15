package pl.adamnowicki.fixer.fragment.list.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pl.adamnowicki.fixer.data.FixerAdapterData

/**
 * Created by adamnowicki on 15/09/2020.
 */
abstract class RatesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bindData(item: FixerAdapterData, position: Int)
}