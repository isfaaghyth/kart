package app.isfa.kart.db

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import app.isfa.kart.db.api.dao.KartBrandInfoDao
import app.isfa.kart.db.api.dao.KartBrandInfoDao_Impl
import app.isfa.kart.db.api.dao.KartMembershipDao
import app.isfa.kart.db.api.dao.KartMembershipDao_Impl
import app.isfa.kart.db.api.dao.KartSubscriptionDao
import app.isfa.kart.db.api.dao.KartSubscriptionDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class KartDatabase_Impl : KartDatabase() {
  private val _kartBrandInfoDao: Lazy<KartBrandInfoDao> = lazy {
    KartBrandInfoDao_Impl(this)
  }

  private val _kartMembershipDao: Lazy<KartMembershipDao> = lazy {
    KartMembershipDao_Impl(this)
  }

  private val _kartSubscriptionDao: Lazy<KartSubscriptionDao> = lazy {
    KartSubscriptionDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(2, "600cdd99161e06d9ce3dc1b7a5f7d80b", "1cd689df0b001f8569fd2aa2985898ce") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `kart_members` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `accountId` TEXT NOT NULL, `cardType` TEXT NOT NULL, `brand_slug` TEXT NOT NULL, `category` TEXT NOT NULL, FOREIGN KEY(`brand_slug`) REFERENCES `brand_infos`(`slug`) ON UPDATE NO ACTION ON DELETE RESTRICT )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_kart_members_brand_slug` ON `kart_members` (`brand_slug`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `brand_infos` (`slug` TEXT NOT NULL, `name` TEXT NOT NULL, `type` TEXT NOT NULL, `faviconUrl` TEXT NOT NULL, `url` TEXT NOT NULL, `colors` TEXT NOT NULL, PRIMARY KEY(`slug`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `kart_subscriptions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `accountId` TEXT NOT NULL, `cardType` TEXT NOT NULL, `subscriptionType` TEXT NOT NULL, `brand_slug` TEXT NOT NULL, FOREIGN KEY(`brand_slug`) REFERENCES `brand_infos`(`slug`) ON UPDATE NO ACTION ON DELETE RESTRICT )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_kart_subscriptions_brand_slug` ON `kart_subscriptions` (`brand_slug`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '600cdd99161e06d9ce3dc1b7a5f7d80b')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `kart_members`")
        connection.execSQL("DROP TABLE IF EXISTS `brand_infos`")
        connection.execSQL("DROP TABLE IF EXISTS `kart_subscriptions`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        connection.execSQL("PRAGMA foreign_keys = ON")
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsKartMembers: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsKartMembers.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKartMembers.put("accountId", TableInfo.Column("accountId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKartMembers.put("cardType", TableInfo.Column("cardType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKartMembers.put("brand_slug", TableInfo.Column("brand_slug", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKartMembers.put("category", TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysKartMembers: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysKartMembers.add(TableInfo.ForeignKey("brand_infos", "RESTRICT", "NO ACTION", listOf("brand_slug"), listOf("slug")))
        val _indicesKartMembers: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesKartMembers.add(TableInfo.Index("index_kart_members_brand_slug", false, listOf("brand_slug"), listOf("ASC")))
        val _infoKartMembers: TableInfo = TableInfo("kart_members", _columnsKartMembers, _foreignKeysKartMembers, _indicesKartMembers)
        val _existingKartMembers: TableInfo = read(connection, "kart_members")
        if (!_infoKartMembers.equals(_existingKartMembers)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |kart_members(app.isfa.kart.db.api.entity.KartMembershipEntity).
              | Expected:
              |""".trimMargin() + _infoKartMembers + """
              |
              | Found:
              |""".trimMargin() + _existingKartMembers)
        }
        val _columnsBrandInfos: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsBrandInfos.put("slug", TableInfo.Column("slug", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBrandInfos.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBrandInfos.put("type", TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBrandInfos.put("faviconUrl", TableInfo.Column("faviconUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBrandInfos.put("url", TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBrandInfos.put("colors", TableInfo.Column("colors", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysBrandInfos: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesBrandInfos: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoBrandInfos: TableInfo = TableInfo("brand_infos", _columnsBrandInfos, _foreignKeysBrandInfos, _indicesBrandInfos)
        val _existingBrandInfos: TableInfo = read(connection, "brand_infos")
        if (!_infoBrandInfos.equals(_existingBrandInfos)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |brand_infos(app.isfa.kart.db.api.entity.KartBrandInfoEntity).
              | Expected:
              |""".trimMargin() + _infoBrandInfos + """
              |
              | Found:
              |""".trimMargin() + _existingBrandInfos)
        }
        val _columnsKartSubscriptions: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsKartSubscriptions.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKartSubscriptions.put("accountId", TableInfo.Column("accountId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKartSubscriptions.put("cardType", TableInfo.Column("cardType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKartSubscriptions.put("subscriptionType", TableInfo.Column("subscriptionType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKartSubscriptions.put("brand_slug", TableInfo.Column("brand_slug", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysKartSubscriptions: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysKartSubscriptions.add(TableInfo.ForeignKey("brand_infos", "RESTRICT", "NO ACTION", listOf("brand_slug"), listOf("slug")))
        val _indicesKartSubscriptions: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesKartSubscriptions.add(TableInfo.Index("index_kart_subscriptions_brand_slug", false, listOf("brand_slug"), listOf("ASC")))
        val _infoKartSubscriptions: TableInfo = TableInfo("kart_subscriptions", _columnsKartSubscriptions, _foreignKeysKartSubscriptions, _indicesKartSubscriptions)
        val _existingKartSubscriptions: TableInfo = read(connection, "kart_subscriptions")
        if (!_infoKartSubscriptions.equals(_existingKartSubscriptions)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |kart_subscriptions(app.isfa.kart.db.api.entity.KartSubscriptionEntity).
              | Expected:
              |""".trimMargin() + _infoKartSubscriptions + """
              |
              | Found:
              |""".trimMargin() + _existingKartSubscriptions)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "kart_members", "brand_infos", "kart_subscriptions")
  }

  public override fun clearAllTables() {
    super.performClear(true, "kart_members", "kart_subscriptions", "brand_infos")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(KartBrandInfoDao::class, KartBrandInfoDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(KartMembershipDao::class, KartMembershipDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(KartSubscriptionDao::class, KartSubscriptionDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun brandInfoDao(): KartBrandInfoDao = _kartBrandInfoDao.value

  public override fun membershipDao(): KartMembershipDao = _kartMembershipDao.value

  public override fun subscriptionDao(): KartSubscriptionDao = _kartSubscriptionDao.value
}
