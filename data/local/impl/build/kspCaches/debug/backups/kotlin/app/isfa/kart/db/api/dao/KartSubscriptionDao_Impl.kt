package app.isfa.kart.db.api.dao

import androidx.collection.ArrayMap
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndex
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performSuspending
import androidx.room.util.recursiveFetchArrayMap
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.SQLiteStatement
import app.isfa.kart.db.api.BrandTypeOf
import app.isfa.kart.db.api.CardType
import app.isfa.kart.db.api.SubscriptionType
import app.isfa.kart.db.api.converter.BrandTypeConverters
import app.isfa.kart.db.api.converter.CardTypeConverters
import app.isfa.kart.db.api.converter.SubscriptionTypeConverters
import app.isfa.kart.db.api.entity.KartBrandInfoEntity
import app.isfa.kart.db.api.entity.KartSubscriptionEntity
import app.isfa.kart.db.api.entity.KartSubscriptionWithBrand
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.text.StringBuilder
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class KartSubscriptionDao_Impl(
  __db: RoomDatabase,
) : KartSubscriptionDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfKartSubscriptionEntity: EntityInsertAdapter<KartSubscriptionEntity>

  private val __updateAdapterOfKartSubscriptionEntity:
      EntityDeleteOrUpdateAdapter<KartSubscriptionEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfKartSubscriptionEntity = object : EntityInsertAdapter<KartSubscriptionEntity>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `kart_subscriptions` (`id`,`accountId`,`cardType`,`subscriptionType`,`brand_slug`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: KartSubscriptionEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.accountId)
        val _tmp: String = CardTypeConverters.fromType(entity.cardType)
        statement.bindText(3, _tmp)
        val _tmp_1: String = SubscriptionTypeConverters.fromType(entity.subscriptionType)
        statement.bindText(4, _tmp_1)
        statement.bindText(5, entity.brandSlug)
      }
    }
    this.__updateAdapterOfKartSubscriptionEntity = object : EntityDeleteOrUpdateAdapter<KartSubscriptionEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `kart_subscriptions` SET `id` = ?,`accountId` = ?,`cardType` = ?,`subscriptionType` = ?,`brand_slug` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: KartSubscriptionEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.accountId)
        val _tmp: String = CardTypeConverters.fromType(entity.cardType)
        statement.bindText(3, _tmp)
        val _tmp_1: String = SubscriptionTypeConverters.fromType(entity.subscriptionType)
        statement.bindText(4, _tmp_1)
        statement.bindText(5, entity.brandSlug)
        statement.bindLong(6, entity.id.toLong())
      }
    }
  }

  public override suspend fun insert(entity: KartSubscriptionEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfKartSubscriptionEntity.insertAndReturnId(_connection, entity)
    _result
  }

  public override suspend fun update(entity: KartSubscriptionEntity): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __updateAdapterOfKartSubscriptionEntity.handle(_connection, entity)
    _result
  }

  public override fun all(): Flow<List<KartSubscriptionWithBrand>> {
    val _sql: String = "SELECT * FROM kart_subscriptions ORDER BY id DESC"
    return createFlow(__db, true, arrayOf("brand_infos", "kart_subscriptions")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfCardType: Int = getColumnIndexOrThrow(_stmt, "cardType")
        val _columnIndexOfSubscriptionType: Int = getColumnIndexOrThrow(_stmt, "subscriptionType")
        val _columnIndexOfBrandSlug: Int = getColumnIndexOrThrow(_stmt, "brand_slug")
        val _collectionBrand: ArrayMap<String, KartBrandInfoEntity?> = ArrayMap<String, KartBrandInfoEntity?>()
        while (_stmt.step()) {
          val _tmpKey: String
          _tmpKey = _stmt.getText(_columnIndexOfBrandSlug)
          _collectionBrand.put(_tmpKey, null)
        }
        _stmt.reset()
        __fetchRelationshipbrandInfosAsappIsfaKartDbApiEntityKartBrandInfoEntity(_connection, _collectionBrand)
        val _result: MutableList<KartSubscriptionWithBrand> = mutableListOf()
        while (_stmt.step()) {
          val _item: KartSubscriptionWithBrand
          val _tmpSubscription: KartSubscriptionEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpAccountId: String
          _tmpAccountId = _stmt.getText(_columnIndexOfAccountId)
          val _tmpCardType: CardType
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfCardType)
          _tmpCardType = CardTypeConverters.fromString(_tmp)
          val _tmpSubscriptionType: SubscriptionType
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_columnIndexOfSubscriptionType)
          _tmpSubscriptionType = SubscriptionTypeConverters.fromString(_tmp_1)
          val _tmpBrandSlug: String
          _tmpBrandSlug = _stmt.getText(_columnIndexOfBrandSlug)
          _tmpSubscription = KartSubscriptionEntity(_tmpId,_tmpAccountId,_tmpCardType,_tmpSubscriptionType,_tmpBrandSlug)
          val _tmpBrand: KartBrandInfoEntity?
          val _tmpKey_1: String
          _tmpKey_1 = _stmt.getText(_columnIndexOfBrandSlug)
          _tmpBrand = _collectionBrand.get(_tmpKey_1)
          if (_tmpBrand == null) {
            error("Relationship item 'brand' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'brand_slug' and entityColumn named 'slug'.")
          }
          _item = KartSubscriptionWithBrand(_tmpSubscription,_tmpBrand)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(subscriptionId: Int): Int {
    val _sql: String = "DELETE FROM kart_subscriptions WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, subscriptionId.toLong())
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  private fun __fetchRelationshipbrandInfosAsappIsfaKartDbApiEntityKartBrandInfoEntity(_connection: SQLiteConnection, _map: ArrayMap<String, KartBrandInfoEntity?>) {
    val __mapKeySet: Set<String> = _map.keys
    if (__mapKeySet.isEmpty()) {
      return
    }
    if (_map.size > 999) {
      recursiveFetchArrayMap(_map, false) { _tmpMap ->
        __fetchRelationshipbrandInfosAsappIsfaKartDbApiEntityKartBrandInfoEntity(_connection, _tmpMap)
      }
      return
    }
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT `slug`,`name`,`type`,`faviconUrl`,`url`,`colors` FROM `brand_infos` WHERE `slug` IN (")
    val _inputSize: Int = __mapKeySet.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    val _stmt: SQLiteStatement = _connection.prepare(_sql)
    var _argIndex: Int = 1
    for (_item: String in __mapKeySet) {
      _stmt.bindText(_argIndex, _item)
      _argIndex++
    }
    try {
      val _itemKeyIndex: Int = getColumnIndex(_stmt, "slug")
      if (_itemKeyIndex == -1) {
        return
      }
      val _columnIndexOfSlug: Int = 0
      val _columnIndexOfName: Int = 1
      val _columnIndexOfType: Int = 2
      val _columnIndexOfFaviconUrl: Int = 3
      val _columnIndexOfUrl: Int = 4
      val _columnIndexOfColors: Int = 5
      while (_stmt.step()) {
        val _tmpKey: String
        _tmpKey = _stmt.getText(_itemKeyIndex)
        if (_map.containsKey(_tmpKey)) {
          val _item_1: KartBrandInfoEntity
          val _tmpSlug: String
          _tmpSlug = _stmt.getText(_columnIndexOfSlug)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpType: BrandTypeOf
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfType)
          _tmpType = BrandTypeConverters.fromString(_tmp)
          val _tmpFaviconUrl: String
          _tmpFaviconUrl = _stmt.getText(_columnIndexOfFaviconUrl)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          val _tmpColors: String
          _tmpColors = _stmt.getText(_columnIndexOfColors)
          _item_1 = KartBrandInfoEntity(_tmpSlug,_tmpName,_tmpType,_tmpFaviconUrl,_tmpUrl,_tmpColors)
          _map.put(_tmpKey, _item_1)
        }
      }
    } finally {
      _stmt.close()
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
