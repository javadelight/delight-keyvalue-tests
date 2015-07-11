package delight.keyvalue.tests;

import delight.async.AsyncCommon;
import delight.async.Operation;
import delight.async.callbacks.SimpleCallback;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
import delight.functional.Function;
import delight.functional.Success;
import delight.keyvalue.Store;
import delight.keyvalue.tests.StoreTest;
import java.util.List;

@SuppressWarnings("all")
public class StoreTests {
  public static void testAndStartAndStop(final Function<Void, Store<String, Object>> factory) {
    List<StoreTest> _all = StoreTests.all();
    for (final StoreTest test : _all) {
      {
        final Store<String, Object> store = factory.apply(null);
        final Operation<Success> _function = new Operation<Success>() {
          @Override
          public void apply(final ValueCallback<Success> callback) {
            SimpleCallback _asSimpleCallback = AsyncCommon.asSimpleCallback(callback);
            store.start(_asSimpleCallback);
          }
        };
        Async.<Success>waitFor(_function);
        test.test(store);
        final Operation<Success> _function_1 = new Operation<Success>() {
          @Override
          public void apply(final ValueCallback<Success> callback) {
            SimpleCallback _asSimpleCallback = AsyncCommon.asSimpleCallback(callback);
            store.stop(_asSimpleCallback);
          }
        };
        Async.<Success>waitFor(_function_1);
      }
    }
  }
  
  public static List<StoreTest> all() {
    throw new Error("Unresolved compilation problems:"
      + "\nno viable alternative at input \']\'");
  }
}
