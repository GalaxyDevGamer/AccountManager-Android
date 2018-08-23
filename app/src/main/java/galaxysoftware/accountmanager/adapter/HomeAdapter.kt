package galaxysoftware.accountmanager.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import galaxysoftware.accountmanager.R
import galaxysoftware.accountmanager.callback.SwipeCallback
import galaxysoftware.accountmanager.callback.WordSelectedListener
import galaxysoftware.accountmanager.realm.Accounts
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_account.view.*

class HomeAdapter(private val listener: WordSelectedListener, private val callback: SwipeCallback) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var data: RealmResults<Accounts> = Realm.getDefaultInstance().where(Accounts::class.java).findAll()

    init {
        if (data.size == 0) {
            update()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_account, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.accountView.text = item?.account
        holder.itemView.setOnClickListener {
            listener.onClick(item!!.account!!)
        }
    }

    fun update() {
        FirebaseFirestore.getInstance().collection("accounts").get().addOnCompleteListener {
            if (it.isSuccessful) {
                for (document in it.result) {
                    val local = Accounts()
                    local.account = document.data["account"].toString()
                    local.id = document.data["id"].toString()
                    local.password = document.data["password"].toString()
                    local.url = document.data["url"].toString()
                    local.note = document.data["note"].toString()
                    Realm.getDefaultInstance().executeTransaction {
                        it.insertOrUpdate(local)
                    }
                }
                data = Realm.getDefaultInstance().where(Accounts::class.java).findAll()
                notifyDataSetChanged()
            }
            callback.onRefreshComplete()
        }
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val accountView: TextView = mView.accountView
    }
}