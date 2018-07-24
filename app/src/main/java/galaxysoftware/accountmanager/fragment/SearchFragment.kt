package galaxysoftware.accountmanager.fragment

import android.support.v7.widget.LinearLayoutManager
import galaxysoftware.accountmanager.BaseFragment
import galaxysoftware.accountmanager.R
import galaxysoftware.accountmanager.adapter.ResultAdapter
import galaxysoftware.accountmanager.callback.WordSelectedListener
import galaxysoftware.accountmanager.type.FragmentType
import galaxysoftware.accountmanager.type.NavigationType
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment(), WordSelectedListener {
    var resultAdapter = ResultAdapter(this)

    override fun initialize() {
        list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = resultAdapter
        }
        setHasOptionsMenu(true)
    }

    override fun getLayoutId() = R.layout.fragment_search

    override fun updateFragment() {

    }

    override fun onResume() {
        super.onResume()
        updateToolbar(NavigationType.NONE, getString(R.string.search), R.menu.search)
    }

    override fun onClick(word: String) {
        requestChangeFragment(FragmentType.VIEW, word)
    }

    fun searchWord(query: String) {
        resultAdapter.search(query)
    }

    fun clear() {
        resultAdapter.clear()
    }

    companion object {

        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}
