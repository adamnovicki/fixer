package pl.adamnowicki.fixer.list.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import pl.adamnowicki.fixer.data.FixerAdapterData
import pl.adamnowicki.fixer.data.ItemAdapterData

/**
 * Created by adamnowicki on 15/09/2020.
 */
abstract class FixerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bindData(item: FixerAdapterData, position: Int)
}