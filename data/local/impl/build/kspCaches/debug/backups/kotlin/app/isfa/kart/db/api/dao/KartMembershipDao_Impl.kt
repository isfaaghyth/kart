package app.isfa.kart.db.api.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import app.isfa.kart.db.api.CardType
import app.isfa.kart.db.api.MerchantCategory
import app.isfa.kart.db.api.converter.CardTypeConverters
import app.isfa.kart.db.api.converter.MerchantCategoryConverters
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class KartMembershipDao_Impl(
  __db: RoomDatabase,
) : KartMembershipDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfKartMembershipEntity: EntityInsertAdapter<KartMembershipEntity>

  private val __updateAdapterOfKartMembershipEntity:
      EntityDeleteOrUpdateAdapter<KartMembershipEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfKartMembershipEntity = object : EntityInsertAdapter<KartMembershipEntity>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `kart_members` (`id`,`accountId`,`cardType`,`merchantName`,`merchantCategory`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: KartMembershipEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.accountId)
        val _tmp: String = CardTypeConverters.fromType(entity.cardType)
        statement.bindText(3, _tmp)
        statement.bindText(4, entity.merchantName)
        val _tmp_1: String = MerchantCategoryConverters.fromCategory(entity.merchantCategory)
        statement.bindText(5, _tmp_1)
      }
    }
    this.__updateAdapterOfKartMembershipEntity = object : EntityDeleteOrUpdateAdapter<KartMembershipEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `kart_members` SET `id` = ?,`accountId` = ?,`cardType` = ?,`merchantName` = ?,`merchantCategory` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: KartMembershipEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.accountId)
        val _tmp: String = CardTypeConverters.fromType(entity.cardType)
        statement.bindText(3, _tmp)
        statement.bindText(4, entity.merchantName)
        val _tmp_1: String = MerchantCategoryConverters.fromCategory(entity.merchantCategory)
        statement.bindText(5, _tmp_1)
        statement.bindLong(6, entity.id.toLong())
      }
    }
  }

  public override suspend fun insert(entity: KartMembershipEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfKartMembershipEntity.insertAndReturnId(_connection, entity)
    _result
  }

  public override suspend fun update(entity: KartMembershipEntity): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __updateAdapterOfKartMembershipEntity.handle(_connection, entity)
    _result
  }

  public override fun all(): Flow<List<KartMembershipEntity>> {
    val _sql: String = "SELECT * FROM kart_members ORDER BY id DESC"
    return createFlow(__db, false, arrayOf("kart_members")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfCardType: Int = getColumnIndexOrThrow(_stmt, "cardType")
        val _columnIndexOfMerchantName: Int = getColumnIndexOrThrow(_stmt, "merchantName")
        val _columnIndexOfMerchantCategory: Int = getColumnIndexOrThrow(_stmt, "merchantCategory")
        val _result: MutableList<KartMembershipEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: KartMembershipEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpAccountId: String
          _tmpAccountId = _stmt.getText(_columnIndexOfAccountId)
          val _tmpCardType: CardType
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfCardType)
          _tmpCardType = CardTypeConverters.fromString(_tmp)
          val _tmpMerchantName: String
          _tmpMerchantName = _stmt.getText(_columnIndexOfMerchantName)
          val _tmpMerchantCategory: MerchantCategory
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_columnIndexOfMerchantCategory)
          _tmpMerchantCategory = MerchantCategoryConverters.fromString(_tmp_1)
          _item = KartMembershipEntity(_tmpId,_tmpAccountId,_tmpCardType,_tmpMerchantName,_tmpMerchantCategory)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(cardId: Int): Int {
    val _sql: String = "DELETE FROM kart_members WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, cardId.toLong())
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
