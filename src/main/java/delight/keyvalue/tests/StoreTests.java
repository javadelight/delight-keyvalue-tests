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
import delight.keyvalue.tests.TestMultiDelete;
import delight.keyvalue.tests.TestMultiSelect;
import delight.keyvalue.tests.TestThatAsynchronousPutMapCanBeStopped;
import delight.keyvalue.tests.TestThatParellelWorkerProcessesPuts;
import java.util.Collections;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class StoreTests {
  public static void testAll(final Function<Void, Store<String, Object>> factory) {
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
    TestMultiDelete _testMultiDelete = new TestMultiDelete();
    TestMultiSelect _testMultiSelect = new TestMultiSelect();
    TestThatAsynchronousPutMapCanBeStopped _testThatAsynchronousPutMapCanBeStopped = new TestThatAsynchronousPutMapCanBeStopped();
    TestThatParellelWorkerProcessesPuts _testThatParellelWorkerProcessesPuts = new TestThatParellelWorkerProcessesPuts();
    return Collections.<StoreTest>unmodifiableList(CollectionLiterals.<StoreTest>newArrayList(_testMultiDelete, _testMultiSelect, _testThatAsynchronousPutMapCanBeStopped, _testThatParellelWorkerProcessesPuts));
  }
}
