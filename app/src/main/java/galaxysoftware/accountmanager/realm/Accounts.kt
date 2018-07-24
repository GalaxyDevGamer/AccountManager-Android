package galaxysoftware.accountmanager.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Accounts : RealmObject() {
    @PrimaryKey
    var account: String? = null
    var id: String? = null
    var password: String? = null
    var url: String? = null
    var note: String? = null
}