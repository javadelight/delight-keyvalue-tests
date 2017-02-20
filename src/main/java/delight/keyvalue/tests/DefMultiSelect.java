package delight.keyvalue.tests;

import delight.async.AsyncCommon;
import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
import delight.functional.Closure;
import delight.functional.Success;
import delight.keyvalue.Store;
import delight.keyvalue.operations.StoreOperations;
import delight.keyvalue.tests.StoreTest;
import java.util.List;

@SuppressWarnings("all")
public class DefMultiSelect implements StoreTest {
  @Override
  public void test(final Store<String, Object> store) {
    final Operation<Object> _function = new Operation<Object>() {
      @Override
      public void apply(final ValueCallback<Object> callback) {
        store.put("node/child1", "one", AsyncCommon.<Object>asSimpleCallback(callback));
      }
    };
    Async.<Object>waitFor(_function);
    final Operation<Object> _function_1 = new Operation<Object>() {
      @Override
      public void apply(final ValueCallback<Object> callback) {
        store.put("node/child2", "two", AsyncCommon.<Object>asSimpleCallback(callback));
      }
    };
    Async.<Object>waitFor(_function_1);
    final Operation<Object> _function_2 = new Operation<Object>() {
      @Override
      public void apply(final ValueCallback<Object> callback) {
        store.put("node/child3", "three", AsyncCommon.<Object>asSimpleCallback(callback));
      }
    };
    Async.<Object>waitFor(_function_2);
    final Operation<Object> _function_3 = new Operation<Object>() {
      @Override
      public void apply(final ValueCallback<Object> callback) {
        final Closure<Object> _function = new Closure<Object>() {
          @Override
          public void apply(final Object res) {
            int _size = ((List<Object>) res).size();
            boolean _notEquals = (_size != 3);
            if (_notEquals) {
              int _size_1 = ((List<Object>) res).size();
              String _plus = ("Invalid number of results. Got " + Integer.valueOf(_size_1));
              Exception _exception = new Exception(_plus);
              callback.onFailure(_exception);
              return;
            }
            callback.onSuccess(Success.INSTANCE);
          }
        };
        store.performOperation(StoreOperations.<String, Object>getAll("node/", 0, 100), 
          AsyncCommon.<Object>embed(callback, _function));
      }
    };
    Async.<Object>waitFor(_function_3);
  }
}
