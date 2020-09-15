package pl.adamnowicki.fixer.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.list_fragment.*
import pl.adamnowicki.fixer.R
import pl.adamnowicki.fixer.data.FixerAdapterData
import pl.adamnowicki.fixer.data.ItemAdapterData
import pl.adamnowicki.fixer.data.LoadingAdapterData
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class DetailsFragment  @Inject constructor() : Fragment(R.layout.details_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}