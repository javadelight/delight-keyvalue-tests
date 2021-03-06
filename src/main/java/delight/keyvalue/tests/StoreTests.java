package delight.keyvalue.tests;

import delight.async.AsyncCommon;
import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
import delight.functional.Function;
import delight.keyvalue.Store;
import delight.keyvalue.tests.DefMultiDelete;
import delight.keyvalue.tests.DefMultiSelect;
import delight.keyvalue.tests.DefPut;
import delight.keyvalue.tests.StoreTest;
import java.util.Collections;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class StoreTests {
  public static void testAndStartAndStop(final Function<Void, Store<String, Object>> factory) {
    List<StoreTest> _all = StoreTests.all();
    for (final StoreTest test : _all) {
      {
        final Store<String, Object> store = factory.apply(null);
        final Operation<Object> _function = new Operation<Object>() {
          @Override
          public void apply(final ValueCallback<Object> callback) {
            store.start(AsyncCommon.<Object>asSimpleCallback(callback));
          }
        };
        Async.<Object>waitFor(_function);
        test.test(store);
        final Operation<Object> _function_1 = new Operation<Object>() {
          @Override
          public void apply(final ValueCallback<Object> callback) {
            store.stop(AsyncCommon.<Object>asSimpleCallback(callback));
          }
        };
        Async.<Object>waitFor(_function_1);
      }
    }
  }
  
  public static List<StoreTest> all() {
    DefPut _defPut = new DefPut();
    DefMultiDelete _defMultiDelete = new DefMultiDelete();
    DefMultiSelect _defMultiSelect = new DefMultiSelect();
    return Collections.<StoreTest>unmodifiableList(CollectionLiterals.<StoreTest>newArrayList(_defPut, _defMultiDelete, _defMultiSelect));
  }
}
