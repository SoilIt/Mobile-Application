package com.github.user.soilitouraplication.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.github.user.soilitouraplication.api.History;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class HistoryDao_Impl implements HistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<History> __insertionAdapterOfHistory;

  private final EntityDeletionOrUpdateAdapter<History> __deletionAdapterOfHistory;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllHistory;

  public HistoryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHistory = new EntityInsertionAdapter<History>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `history` (`id`,`user_id`,`soil_type`,`soil_moisture`,`soil_temperature`,`soil_condition`,`created_at`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, History value) {
        stmt.bindLong(1, value.getId());
        if (value.getUser_id() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUser_id());
        }
        if (value.getSoil_type() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSoil_type());
        }
        stmt.bindLong(4, value.getSoil_moisture());
        stmt.bindLong(5, value.getSoil_temperature());
        if (value.getSoil_condition() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getSoil_condition());
        }
        if (value.getCreated_at() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getCreated_at());
        }
      }
    };
    this.__deletionAdapterOfHistory = new EntityDeletionOrUpdateAdapter<History>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `history` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, History value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAllHistory = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM history";
        return _query;
      }
    };
  }

  @Override
  public Object insertHistory(final List<History> historyList,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHistory.insert(historyList);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteHistory(final History history,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfHistory.handle(history);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteAllHistory(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllHistory.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAllHistory.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<History>> getAllHistory() {
    final String _sql = "SELECT * FROM history";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"history"}, new Callable<List<History>>() {
      @Override
      public List<History> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfSoilType = CursorUtil.getColumnIndexOrThrow(_cursor, "soil_type");
          final int _cursorIndexOfSoilMoisture = CursorUtil.getColumnIndexOrThrow(_cursor, "soil_moisture");
          final int _cursorIndexOfSoilTemperature = CursorUtil.getColumnIndexOrThrow(_cursor, "soil_temperature");
          final int _cursorIndexOfSoilCondition = CursorUtil.getColumnIndexOrThrow(_cursor, "soil_condition");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final List<History> _result = new ArrayList<History>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final History _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpUser_id;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUser_id = null;
            } else {
              _tmpUser_id = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpSoil_type;
            if (_cursor.isNull(_cursorIndexOfSoilType)) {
              _tmpSoil_type = null;
            } else {
              _tmpSoil_type = _cursor.getString(_cursorIndexOfSoilType);
            }
            final int _tmpSoil_moisture;
            _tmpSoil_moisture = _cursor.getInt(_cursorIndexOfSoilMoisture);
            final int _tmpSoil_temperature;
            _tmpSoil_temperature = _cursor.getInt(_cursorIndexOfSoilTemperature);
            final String _tmpSoil_condition;
            if (_cursor.isNull(_cursorIndexOfSoilCondition)) {
              _tmpSoil_condition = null;
            } else {
              _tmpSoil_condition = _cursor.getString(_cursorIndexOfSoilCondition);
            }
            final String _tmpCreated_at;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreated_at = null;
            } else {
              _tmpCreated_at = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            _item = new History(_tmpId,_tmpUser_id,_tmpSoil_type,_tmpSoil_moisture,_tmpSoil_temperature,_tmpSoil_condition,_tmpCreated_at);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getHistoryCount(final Continuation<? super Integer> continuation) {
    final String _sql = "SELECT COUNT(*) FROM history";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
