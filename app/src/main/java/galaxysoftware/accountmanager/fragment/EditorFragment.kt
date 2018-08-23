package galaxysoftware.accountmanager.fragment

import android.os.Bundle
import android.view.MenuItem
import com.google.firebase.firestore.FirebaseFirestore
import galaxysoftware.accountmanager.BaseFragment
import galaxysoftware.accountmanager.R
import galaxysoftware.accountmanager.realm.Accounts
import galaxysoftware.accountmanager.type.NavigationType
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_editer.*
import java.util.*

/**
 * Use the [EditorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditorFragment : BaseFragment() {

    private var word = ""

    override fun initialize() {
        if (arguments != null) {
            word = arguments?.getString(BUNDLE_KEY_OBJECT)!!
            val data = Realm.getDefaultInstance().where(Accounts::class.java).equalTo("account", word).findFirst()
            accountField.setText(data?.account)
            idField.setText(data?.id)
            passwordField.setText(data?.password)
            urlField.setText(data?.url)
            noteFiled.setText(data?.note)
        }
    }

    override fun getLayoutId() = R.layout.fragment_editer

    override fun updateFragment() {

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val db = FirebaseFirestore.getInstance()
        val data = HashMap<String, Any>()
        data["account"] = accountField.text.toString()
        data["id"] = idField.text.toString()
        data["password"] = passwordField.text.toString()
        data["url"] = urlField.text.toString().toInt()
        data["note"] = noteFiled.text.toString()
        db.collection("accounts").document(accountField.text.toString()).set(data).addOnCompleteListener {
            if (it.isSuccessful) {
                val account = Accounts()
                account.account = accountField.text.toString()
                account.id = idField.text.toString()
                account.password = passwordField.text.toString()
                account.url = urlField.text.toString()
                account.note = noteFiled.text.toString()
                Realm.getDefaultInstance().executeTransaction {
                    it.insertOrUpdate(account)
                }
            }
            backFragment()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val BUNDLE_KEY_OBJECT = "bundle_key_object"

        @JvmStatic
        fun newInstance(any: Any) = EditorFragment().apply {
            arguments = Bundle().apply {
                putString(BUNDLE_KEY_OBJECT, any as String)
            }
        }
    }
}
