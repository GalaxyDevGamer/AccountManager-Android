package galaxysoftware.accountmanager.callback

import galaxysoftware.accountmanager.type.FragmentType

interface ChangeFragmentListener {
    fun onChangeFragment(fragmentType: FragmentType, any: Any)
}