package delight.keyvalue.tests;

import delight.async.AsyncCommon;
import delight.async.Operation;
import delight.async.callbacks.SimpleCallback;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
import delight.functional.Closure;
import delight.functional.Success;
import delight.keyvalue.Store;
import delight.keyvalue.operations.StoreOperation;
import delight.keyvalue.operations.StoreOperations;
import delight.keyvalue.tests.StoreTest;
import java.util.List;

@SuppressWarnings("all")
public class DefMultiSelect implements StoreTest {
  @Override
  public void test(final Store<String, Object> store) {
    final Operation<Success> _function = new Operation<Success>() {
      @Override
      public void apply(final ValueCallback<Success> callback) {
        SimpleCallback _asSimpleCallback = AsyncCommon.asSimpleCallback(callback);
        store.put("node/child1", "one", _asSimpleCallback);
      }
    };
    Async.<Success>waitFor(_function);
    final Operation<Success> _function_1 = new Operation<Success>() {
      @Override
      public void apply(final ValueCallback<Success> callback) {
        SimpleCallback _asSimpleCallback = AsyncCommon.asSimpleCallback(callback);
        store.put("node/child2", "two", _asSimpleCallback);
      }
    };
    Async.<Success>waitFor(_function_1);
    final Operation<Success> _function_2 = new Operation<Success>() {
      @Override
      public void apply(final ValueCallback<Success> callback) {
        SimpleCallback _asSimpleCallback = AsyncCommon.asSimpleCallback(callback);
        store.put("node/child3", "three", _asSimpleCallback);
      }
    };
    Async.<Success>waitFor(_function_2);
    final Operation<Object> _function_3 = new Operation<Object>() {
      @Override
      public void apply(final ValueCallback<Object> callback) {
        StoreOperation<String, Object> _all = StoreOperations.<String, Object>getAll("node/", 0, 100);
        final Closure<Object> _function = new Closure<Object>() {
          @Override
          public void apply(final Object res) {
            int _size = ((List<Object>) res).size();
            boolean _notEquals = (_size != 3);
            if (_notEquals) {
              Exception _exception = new Exception("Invalid number of results.");
              callback.onFailure(_exception);
              return;
            }
            callback.onSuccess(Success.INSTANCE);
          }
        };
        ValueCallback<Object> _embed = AsyncCommon.<Object>embed(callback, _function);
        store.performOperation(_all, _embed);
      }
    };
    Async.<Object>waitFor(_function_3);
  }
}
