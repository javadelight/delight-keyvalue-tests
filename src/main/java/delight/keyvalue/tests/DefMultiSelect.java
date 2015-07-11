package delight.keyvalue.tests;

import delight.async.AsyncCommon;
import delight.async.Operation;
import delight.async.Value;
import delight.async.callbacks.SimpleCallback;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
import delight.functional.Closure;
import delight.functional.Success;
import delight.keyvalue.Store;
import delight.keyvalue.StoreEntry;
import delight.keyvalue.operations.StoreOperation;
import delight.keyvalue.operations.StoreOperations;
import delight.keyvalue.tests.StoreTest;
import org.eclipse.xtext.xbase.lib.InputOutput;

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
    final Operation<Success> _function_3 = new Operation<Success>() {
      @Override
      public void apply(final ValueCallback<Success> callback) {
        final Value<Integer> count = new Value<Integer>(Integer.valueOf(0));
        InputOutput.<String>println("try");
        final Closure<StoreEntry<String, Object>> _function = new Closure<StoreEntry<String, Object>>() {
          @Override
          public void apply(final StoreEntry<String, Object> e) {
            Integer _get = count.get();
            int _plus = ((_get).intValue() + 1);
            count.set(Integer.valueOf(_plus));
            InputOutput.<Value<Integer>>println(count);
            Integer _get_1 = count.get();
            boolean _equals = ((_get_1).intValue() == 3);
            if (_equals) {
              callback.onSuccess(Success.INSTANCE);
            }
          }
        };
        StoreOperation<String, Object> _all = StoreOperations.<String, Object>getAll("node/", _function);
        final Closure<Object> _function_1 = new Closure<Object>() {
          @Override
          public void apply(final Object it) {
            InputOutput.<String>println("done");
          }
        };
        ValueCallback<Object> _embed = AsyncCommon.<Object>embed(callback, _function_1);
        store.performOperation(_all, _embed);
      }
    };
    Async.<Success>waitFor(_function_3);
  }
}
