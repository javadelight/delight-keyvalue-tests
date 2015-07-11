package delight.keyvalue.tests;

import delight.async.AsyncCommon;
import delight.async.Operation;
import delight.async.callbacks.SimpleCallback;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
import delight.functional.Success;
import delight.keyvalue.Store;
import delight.keyvalue.tests.StoreTest;

@SuppressWarnings("all")
public class DefPut implements StoreTest {
  @Override
  public void test(final Store<String, Object> store) {
    final Store<String, Object> map = store;
    final Operation<Success> _function = new Operation<Success>() {
      @Override
      public void apply(final ValueCallback<Success> callback) {
        SimpleCallback _asSimpleCallback = AsyncCommon.asSimpleCallback(callback);
        map.put("1", "one", _asSimpleCallback);
      }
    };
    Async.<Success>waitFor(_function);
    final Operation<Success> _function_1 = new Operation<Success>() {
      @Override
      public void apply(final ValueCallback<Success> callback) {
        SimpleCallback _asSimpleCallback = AsyncCommon.asSimpleCallback(callback);
        map.put("2", "two", _asSimpleCallback);
      }
    };
    Async.<Success>waitFor(_function_1);
  }
}
