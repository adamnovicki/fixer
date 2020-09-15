package pl.adamnowicki.fixer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import pl.adamnowicki.fixer.fragment.list.RatesFragment
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var listFragment: RatesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

//        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as ListFragment?
//        if (fragment == null) {
//            fragment = listFragment
//
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.add(R.id.contentFrame, fragment)
//            transaction.commit()
//        }
    }
}