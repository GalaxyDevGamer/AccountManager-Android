package galaxysoftware.accountmanager.callback

import galaxysoftware.accountmanager.type.FragmentType

interface MainActivityView {
    fun requestChangeFragment(fragmentType: FragmentType) {}

    fun updateToolbar()
}