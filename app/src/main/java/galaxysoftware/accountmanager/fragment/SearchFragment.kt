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
    }

    override fun getLayoutId() = R.layout.fragment_search

    override fun updateFragment() {

    }

    override fun onClick(word: String) {
        updateToolbar(FragmentType.VIEW, NavigationType.BACK, word, R.menu.view)
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
