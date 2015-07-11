package delight.keyvalue.tests;

import delight.async.AsyncCommon;
import delight.async.Operation;
import delight.async.callbacks.SimpleCallback;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
import delight.concurrency.jre.JreConcurrency;
import delight.functional.Success;
import delight.keyvalue.Store;
import delight.keyvalue.Stores;
import delight.keyvalue.tests.StoreTest;

@SuppressWarnings("all")
public class DefPut implements StoreTest {
  @Override
  public void test(final Store<String, Object> store) {
    JreConcurrency _jreConcurrency = new JreConcurrency();
    final Store<String, Object> map = Stores.<String, Object>enforceAsynchronousPut(10, _jreConcurrency, store);
    final Operation<Success> _function = new Operation<Success>() {
      @Override
      public void apply(final ValueCallback<Success> callback) {
        SimpleCallback _asSimpleCallback = AsyncCommon.asSimpleCallback(callback);
        map.start(_asSimpleCallback);
      }
    };
    Async.<Success>waitFor(_function);
    final Operation<Success> _function_1 = new Operation<Success>() {
      @Override
      public void apply(final ValueCallback<Success> callback) {
        SimpleCallback _asSimpleCallback = AsyncCommon.asSimpleCallback(callback);
        map.put("1", "one", _asSimpleCallback);
      }
    };
    Async.<Success>waitFor(_function_1);
    final Operation<Success> _function_2 = new Operation<Success>() {
      @Override
      public void apply(final ValueCallback<Success> callback) {
        SimpleCallback _asSimpleCallback = AsyncCommon.asSimpleCallback(callback);
        map.put("2", "two", _asSimpleCallback);
      }
    };
    Async.<Success>waitFor(_function_2);
    final Operation<Success> _function_3 = new Operation<Success>() {
      @Override
      public void apply(final ValueCallback<Success> callback) {
        SimpleCallback _asSimpleCallback = AsyncCommon.asSimpleCallback(callback);
        map.stop(_asSimpleCallback);
      }
    };
    Async.<Success>waitFor(_function_3);
  }
}
