package galaxysoftware.accountmanager.helper

import galaxysoftware.accountmanager.BaseFragment
import galaxysoftware.accountmanager.fragment.EditorFragment
import galaxysoftware.accountmanager.fragment.AccountViewFragment
import galaxysoftware.accountmanager.fragment.HomeFragment
import galaxysoftware.accountmanager.fragment.SearchFragment
import galaxysoftware.accountmanager.type.FragmentType

class FragmentMakeHelper {
    companion object {
        fun makeFragment(fragmentType: FragmentType, any: Any): BaseFragment =
            when (fragmentType) {
                FragmentType.HOME_TAB -> HomeFragment.newInstance()
                FragmentType.SEARCH_TAB -> SearchFragment.newInstance()
                FragmentType.VIEW -> AccountViewFragment.newInstance(any)
                FragmentType.EDIT -> EditorFragment.newInstance(any)
            }
    }
}