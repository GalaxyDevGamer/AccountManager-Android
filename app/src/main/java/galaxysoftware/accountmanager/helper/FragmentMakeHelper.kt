package galaxysoftware.accountmanager.helper

import galaxysoftware.accountmanager.BaseFragment
import galaxysoftware.accountmanager.fragment.EditorFragment
import galaxysoftware.accountmanager.fragment.AccountViewFragment
import galaxysoftware.accountmanager.type.FragmentType

class FragmentMakeHelper {
    companion object {
        fun makeFragment(fragmentType: FragmentType, any: Any): BaseFragment =
            when (fragmentType) {
                FragmentType.VIEW -> AccountViewFragment.newInstance(any)
                FragmentType.EDIT -> EditorFragment.newInstance(any)
            }
    }
}