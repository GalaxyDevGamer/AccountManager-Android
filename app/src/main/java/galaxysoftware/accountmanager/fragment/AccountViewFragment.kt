package galaxysoftware.accountmanager.fragment

import android.os.Bundle
import android.view.MenuItem
import galaxysoftware.accountmanager.BaseFragment
import galaxysoftware.accountmanager.R
import galaxysoftware.accountmanager.realm.Accounts
import galaxysoftware.accountmanager.type.FragmentType
import galaxysoftware.accountmanager.type.NavigationType
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_accountview.*

/**
 * Use the [AccountViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountViewFragment : BaseFragment() {

    override fun initialize() {
        arguments?.let {
            arg = it.getString(BUNDLE_KEY_OBJECT)
        }
        val data = Realm.getDefaultInstance().where(Accounts::class.java).equalTo("account", arg).findFirst()
        accountField.text = data?.account
        idField.text = data?.id
        passwordField.text = data?.password
        urlField.text = data?.url
        noteFiled.text = data?.note
    }

    override fun getLayoutId() = R.layout.fragment_accountview

    override fun updateFragment() {

    }

    private var arg: String? = null

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        updateToolbar(FragmentType.EDIT, NavigationType.BACK, "", R.menu.editer)
        requestChangeFragment(FragmentType.EDIT, accountField.text)
        return super.onOptionsItemSelected(item)
    }

    companion object {
        var BUNDLE_KEY_OBJECT = "bundle_key_object"

        @JvmStatic
        fun newInstance(any: Any) =
                AccountViewFragment().apply {
                    arguments = Bundle().apply {
                        putString(BUNDLE_KEY_OBJECT, any as String)
                    }
                }
    }
}