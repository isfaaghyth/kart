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
import app.isfa.kart.db.api.dao.KartMembershipDao
import app.isfa.kart.db.api.dao.KartMembershipDao_Impl
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
  private val _kartMembershipDao: Lazy<KartMembershipDao> = lazy {
    KartMembershipDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1, "42a54ad1094fc6a5000eb6c7e12fe35b", "36271411f7aa9dc8afeb4681db42e443") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `kart_members` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `accountId` TEXT NOT NULL, `cardType` TEXT NOT NULL, `merchantName` TEXT NOT NULL, `merchantCategory` TEXT NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '42a54ad1094fc6a5000eb6c7e12fe35b')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `kart_members`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
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
        _columnsKartMembers.put("merchantName", TableInfo.Column("merchantName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsKartMembers.put("merchantCategory", TableInfo.Column("merchantCategory", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysKartMembers: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesKartMembers: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoKartMembers: TableInfo = TableInfo("kart_members", _columnsKartMembers, _foreignKeysKartMembers, _indicesKartMembers)
        val _existingKartMembers: TableInfo = read(connection, "kart_members")
        if (!_infoKartMembers.equals(_existingKartMembers)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |kart_members(app.isfa.kart.db.api.dao.KartMembershipEntity).
              | Expected:
              |""".trimMargin() + _infoKartMembers + """
              |
              | Found:
              |""".trimMargin() + _existingKartMembers)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "kart_members")
  }

  public override fun clearAllTables() {
    super.performClear(false, "kart_members")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(KartMembershipDao::class, KartMembershipDao_Impl.getRequiredConverters())
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

  public override fun membershipDao(): KartMembershipDao = _kartMembershipDao.value
}
