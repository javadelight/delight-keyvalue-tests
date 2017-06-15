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

@SuppressWarnings("all")
public class DefMultiDelete implements StoreTest {
  @Override
  public void test(final Store<String, Object> store) {
    final Operation<Object> _function = new Operation<Object>() {
      @Override
      public void apply(final ValueCallback<Object> callback) {
        store.put("https://localhost:12012/node/child1", "one", AsyncCommon.<Object>asSimpleCallback(callback));
      }
    };
    Async.<Object>waitFor(_function);
    final Operation<Object> _function_1 = new Operation<Object>() {
      @Override
      public void apply(final ValueCallback<Object> callback) {
        store.put("https://localhost:12012/node/child2", "two", AsyncCommon.<Object>asSimpleCallback(callback));
      }
    };
    Async.<Object>waitFor(_function_1);
    final Operation<Object> _function_2 = new Operation<Object>() {
      @Override
      public void apply(final ValueCallback<Object> callback) {
        store.put("https://localhost:12012/node/child3", "three", AsyncCommon.<Object>asSimpleCallback(callback));
      }
    };
    Async.<Object>waitFor(_function_2);
    final Operation<Object> _function_3 = new Operation<Object>() {
      @Override
      public void apply(final ValueCallback<Object> callback) {
        store.commit(AsyncCommon.<Object>asSimpleCallback(callback));
      }
    };
    Async.<Object>waitFor(_function_3);
    final Operation<Object> _function_4 = new Operation<Object>() {
      @Override
      public void apply(final ValueCallback<Object> callback) {
        final Closure<Object> _function = new Closure<Object>() {
          @Override
          public void apply(final Object count) {
            if (((((Integer) count)).intValue() != 3)) {
              Exception _exception = new Exception("Not created correctly");
              callback.onFailure(_exception);
              return;
            }
            callback.onSuccess(Success.INSTANCE);
          }
        };
        store.performOperation(StoreOperations.<String, Object>count("https://localhost:12012/"), AsyncCommon.<Object>embed(callback, _function));
      }
    };
    Async.<Object>waitFor(_function_4);
    final Operation<Object> _function_5 = new Operation<Object>() {
      @Override
      public void apply(final ValueCallback<Object> callback) {
        final Closure<Object> _function = new Closure<Object>() {
          @Override
          public void apply(final Object it) {
            callback.onSuccess(Success.INSTANCE);
          }
        };
        store.performOperation(StoreOperations.<String, Object>removeAll("https://localhost:12012/node/"), AsyncCommon.<Object>embed(callback, _function));
      }
    };
    Async.<Object>waitFor(_function_5);
    final Operation<Object> _function_6 = new Operation<Object>() {
      @Override
      public void apply(final ValueCallback<Object> callback) {
        final Closure<Object> _function = new Closure<Object>() {
          @Override
          public void apply(final Object count) {
            if (((((Integer) count)).intValue() > 0)) {
              Exception _exception = new Exception("Not deleted correctly");
              callback.onFailure(_exception);
              return;
            }
            callback.onSuccess(Success.INSTANCE);
          }
        };
        store.performOperation(StoreOperations.<String, Object>count("https://localhost:12012/"), AsyncCommon.<Object>embed(callback, _function));
      }
    };
    Async.<Object>waitFor(_function_6);
  }
}
