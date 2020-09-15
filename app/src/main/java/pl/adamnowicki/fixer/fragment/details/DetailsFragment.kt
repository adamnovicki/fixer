package pl.adamnowicki.fixer.fragment.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.details_fragment.*
import pl.adamnowicki.fixer.R
import javax.inject.Inject


@AndroidEntryPoint
class DetailsFragment  @Inject constructor() : Fragment(R.layout.details_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        date.text = arguments?.getString("date")
        currency.text = arguments?.getString("currency")
        value.text = arguments?.getString("value")

    }

}