package app.isfa.kart.db.api.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import app.isfa.kart.db.api.BrandTypeOf
import app.isfa.kart.db.api.converter.BrandTypeConverters
import app.isfa.kart.db.api.entity.KartBrandInfoEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class KartBrandInfoDao_Impl(
  __db: RoomDatabase,
) : KartBrandInfoDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfKartBrandInfoEntity: EntityInsertAdapter<KartBrandInfoEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfKartBrandInfoEntity = object : EntityInsertAdapter<KartBrandInfoEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `brand_infos` (`slug`,`name`,`type`,`faviconUrl`,`url`,`colors`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: KartBrandInfoEntity) {
        statement.bindText(1, entity.slug)
        statement.bindText(2, entity.name)
        val _tmp: String = BrandTypeConverters.fromType(entity.type)
        statement.bindText(3, _tmp)
        statement.bindText(4, entity.faviconUrl)
        statement.bindText(5, entity.url)
        statement.bindText(6, entity.colors)
      }
    }
  }

  public override suspend fun upsert(entity: KartBrandInfoEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfKartBrandInfoEntity.insert(_connection, entity)
  }

  public override suspend fun upsertAll(entities: List<KartBrandInfoEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfKartBrandInfoEntity.insert(_connection, entities)
  }

  public override fun all(): Flow<List<KartBrandInfoEntity>> {
    val _sql: String = "SELECT * FROM brand_infos ORDER BY name ASC"
    return createFlow(__db, false, arrayOf("brand_infos")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfSlug: Int = getColumnIndexOrThrow(_stmt, "slug")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfFaviconUrl: Int = getColumnIndexOrThrow(_stmt, "faviconUrl")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfColors: Int = getColumnIndexOrThrow(_stmt, "colors")
        val _result: MutableList<KartBrandInfoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: KartBrandInfoEntity
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
          _item = KartBrandInfoEntity(_tmpSlug,_tmpName,_tmpType,_tmpFaviconUrl,_tmpUrl,_tmpColors)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun allByType(type: BrandTypeOf): Flow<List<KartBrandInfoEntity>> {
    val _sql: String = "SELECT * FROM brand_infos WHERE type = ? ORDER BY name ASC"
    return createFlow(__db, false, arrayOf("brand_infos")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: String = BrandTypeConverters.fromType(type)
        _stmt.bindText(_argIndex, _tmp)
        val _columnIndexOfSlug: Int = getColumnIndexOrThrow(_stmt, "slug")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfFaviconUrl: Int = getColumnIndexOrThrow(_stmt, "faviconUrl")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfColors: Int = getColumnIndexOrThrow(_stmt, "colors")
        val _result: MutableList<KartBrandInfoEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: KartBrandInfoEntity
          val _tmpSlug: String
          _tmpSlug = _stmt.getText(_columnIndexOfSlug)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpType: BrandTypeOf
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_columnIndexOfType)
          _tmpType = BrandTypeConverters.fromString(_tmp_1)
          val _tmpFaviconUrl: String
          _tmpFaviconUrl = _stmt.getText(_columnIndexOfFaviconUrl)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          val _tmpColors: String
          _tmpColors = _stmt.getText(_columnIndexOfColors)
          _item = KartBrandInfoEntity(_tmpSlug,_tmpName,_tmpType,_tmpFaviconUrl,_tmpUrl,_tmpColors)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun findBySlug(slug: String): KartBrandInfoEntity? {
    val _sql: String = "SELECT * FROM brand_infos WHERE slug = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, slug)
        val _columnIndexOfSlug: Int = getColumnIndexOrThrow(_stmt, "slug")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfFaviconUrl: Int = getColumnIndexOrThrow(_stmt, "faviconUrl")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfColors: Int = getColumnIndexOrThrow(_stmt, "colors")
        val _result: KartBrandInfoEntity?
        if (_stmt.step()) {
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
          _result = KartBrandInfoEntity(_tmpSlug,_tmpName,_tmpType,_tmpFaviconUrl,_tmpUrl,_tmpColors)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
