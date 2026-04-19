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
import app.isfa.kart.db.api.MerchantCategory
import app.isfa.kart.db.api.converter.BrandTypeConverters
import app.isfa.kart.db.api.converter.CardTypeConverters
import app.isfa.kart.db.api.converter.MerchantCategoryConverters
import app.isfa.kart.db.api.entity.KartBrandInfoEntity
import app.isfa.kart.db.api.entity.KartMemberWithBrand
import app.isfa.kart.db.api.entity.KartMembershipEntity
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
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `kart_members` (`id`,`accountId`,`cardType`,`brand_slug`,`category`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: KartMembershipEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.accountId)
        val _tmp: String = CardTypeConverters.fromType(entity.cardType)
        statement.bindText(3, _tmp)
        statement.bindText(4, entity.brandSlug)
        val _tmp_1: String = MerchantCategoryConverters.fromCategory(entity.category)
        statement.bindText(5, _tmp_1)
      }
    }
    this.__updateAdapterOfKartMembershipEntity = object : EntityDeleteOrUpdateAdapter<KartMembershipEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `kart_members` SET `id` = ?,`accountId` = ?,`cardType` = ?,`brand_slug` = ?,`category` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: KartMembershipEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.accountId)
        val _tmp: String = CardTypeConverters.fromType(entity.cardType)
        statement.bindText(3, _tmp)
        statement.bindText(4, entity.brandSlug)
        val _tmp_1: String = MerchantCategoryConverters.fromCategory(entity.category)
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

  public override fun all(): Flow<List<KartMemberWithBrand>> {
    val _sql: String = "SELECT * FROM kart_members ORDER BY id DESC"
    return createFlow(__db, true, arrayOf("brand_infos", "kart_members")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAccountId: Int = getColumnIndexOrThrow(_stmt, "accountId")
        val _columnIndexOfCardType: Int = getColumnIndexOrThrow(_stmt, "cardType")
        val _columnIndexOfBrandSlug: Int = getColumnIndexOrThrow(_stmt, "brand_slug")
        val _columnIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _collectionBrand: ArrayMap<String, KartBrandInfoEntity?> = ArrayMap<String, KartBrandInfoEntity?>()
        while (_stmt.step()) {
          val _tmpKey: String
          _tmpKey = _stmt.getText(_columnIndexOfBrandSlug)
          _collectionBrand.put(_tmpKey, null)
        }
        _stmt.reset()
        __fetchRelationshipbrandInfosAsappIsfaKartDbApiEntityKartBrandInfoEntity(_connection, _collectionBrand)
        val _result: MutableList<KartMemberWithBrand> = mutableListOf()
        while (_stmt.step()) {
          val _item: KartMemberWithBrand
          val _tmpMember: KartMembershipEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpAccountId: String
          _tmpAccountId = _stmt.getText(_columnIndexOfAccountId)
          val _tmpCardType: CardType
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfCardType)
          _tmpCardType = CardTypeConverters.fromString(_tmp)
          val _tmpBrandSlug: String
          _tmpBrandSlug = _stmt.getText(_columnIndexOfBrandSlug)
          val _tmpCategory: MerchantCategory
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_columnIndexOfCategory)
          _tmpCategory = MerchantCategoryConverters.fromString(_tmp_1)
          _tmpMember = KartMembershipEntity(_tmpId,_tmpAccountId,_tmpCardType,_tmpBrandSlug,_tmpCategory)
          val _tmpBrand: KartBrandInfoEntity?
          val _tmpKey_1: String
          _tmpKey_1 = _stmt.getText(_columnIndexOfBrandSlug)
          _tmpBrand = _collectionBrand.get(_tmpKey_1)
          if (_tmpBrand == null) {
            error("Relationship item 'brand' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'brand_slug' and entityColumn named 'slug'.")
          }
          _item = KartMemberWithBrand(_tmpMember,_tmpBrand)
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
