package galaxysoftware.accountmanager.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import galaxysoftware.accountmanager.R
import galaxysoftware.accountmanager.callback.WordSelectedListener
import galaxysoftware.accountmanager.realm.Accounts

import io.realm.Realm
import io.realm.RealmResults

import kotlinx.android.synthetic.main.fragment_account.view.*

class ResultAdapter(private val listener: WordSelectedListener) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    private var results: RealmResults<Accounts> = Realm.getDefaultInstance().where(Accounts::class.java).equalTo("word", "").findAll()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_account, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = results[position]
        holder.accountView.text = item?.account
        holder.itemView.setOnClickListener {
            listener.onClick(item?.account!!)
        }
    }

    override fun getItemCount() = results.size

    fun search(word: String) {
        results = Realm.getDefaultInstance().where(Accounts::class.java).beginsWith("account", word).findAll()
        notifyDataSetChanged()
    }

    fun clear() {
        results = Realm.getDefaultInstance().where(Accounts::class.java).equalTo("account", "").findAll()
        notifyDataSetChanged()
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val accountView: TextView = mView.accountView
    }
}