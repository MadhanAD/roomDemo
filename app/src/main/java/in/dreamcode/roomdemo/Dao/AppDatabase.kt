package `in`.dreamcode.roomdemo.Dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context
import android.arch.persistence.db.SupportSQLiteDatabase

@Database(entities = [NoteModel::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dbDao(): DbDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we didn't alter the table, there's nothing else to do here.
            }
        }
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "db_repo_data"
                ).allowMainThreadQueries()
                        .addMigrations(MIGRATION)
                        .build()
            }

            return INSTANCE as AppDatabase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}