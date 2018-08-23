package galaxysoftware.accountmanager.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import galaxysoftware.accountmanager.BaseFragment
import galaxysoftware.accountmanager.R
import galaxysoftware.accountmanager.adapter.HomeAdapter
import galaxysoftware.accountmanager.callback.SwipeCallback
import galaxysoftware.accountmanager.callback.WordSelectedListener
import galaxysoftware.accountmanager.type.FragmentType
import galaxysoftware.accountmanager.type.NavigationType
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), WordSelectedListener, SwipeCallback {
    private lateinit var homeAdapter: HomeAdapter

    override fun initialize() {
        homeAdapter = HomeAdapter(this, this)
        wordList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }
        swipeRefresh.setOnRefreshListener {
            homeAdapter.update()
        }
    }

    override fun getLayoutId() = R.layout.fragment_home


    override fun updateFragment() {

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.add -> {
                updateToolbar(FragmentType.EDIT, NavigationType.BACK, "", R.menu.editer)
                requestChangeFragment(FragmentType.EDIT, "")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(word: String) {
        updateToolbar(FragmentType.VIEW, NavigationType.BACK, word, R.menu.view)
        requestChangeFragment(FragmentType.VIEW, word)
    }

    override fun onRefreshComplete() {
        swipeRefresh.isRefreshing = false
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
