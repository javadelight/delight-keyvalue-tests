package delight.keyvalue.tests;

import delight.async.AsyncCommon;
import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
import delight.keyvalue.Store;
import delight.keyvalue.tests.StoreTest;

@SuppressWarnings("all")
public class DefPut implements StoreTest {
  @Override
  public void test(final Store<String, Object> store) {
    final Store<String, Object> map = store;
    final Operation<Object> _function = new Operation<Object>() {
      @Override
      public void apply(final ValueCallback<Object> callback) {
        map.put("1", "one", AsyncCommon.<Object>asSimpleCallback(callback));
      }
    };
    Async.<Object>waitFor(_function);
    final Operation<Object> _function_1 = new Operation<Object>() {
      @Override
      public void apply(final ValueCallback<Object> callback) {
        map.put("2", "two", AsyncCommon.<Object>asSimpleCallback(callback));
      }
    };
    Async.<Object>waitFor(_function_1);
  }
}
