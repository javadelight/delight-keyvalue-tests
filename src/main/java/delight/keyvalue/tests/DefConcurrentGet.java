package delight.keyvalue.tests;

import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.async.helper.Aggregator;
import delight.async.jre.Async;
import delight.functional.Success;
import delight.keyvalue.Store;
import delight.keyvalue.tests.StoreTest;
import java.util.List;
import org.eclipse.xtext.xbase.lib.IntegerRange;

@SuppressWarnings("all")
public class DefConcurrentGet implements StoreTest {
  @Override
  public void test(final Store<String, Object> store) {
    store.putSync("1", "one");
    store.putSync("2", "two");
    store.putSync("3", "three");
    final Operation<List<Success>> _function = new Operation<List<Success>>() {
      @Override
      public void apply(final ValueCallback<List<Success>> cb) {
        final Aggregator<Success> col = Async.<Success>collect(30, cb);
        IntegerRange _upTo = new IntegerRange(1, 30);
        for (final Integer i : _upTo) {
          new Thread() {
            @Override
            public void run() {
              store.getSync("1");
              ValueCallback<Success> _createCallback = col.createCallback();
              _createCallback.onSuccess(Success.INSTANCE);
            }
          }.start();
        }
      }
    };
    Async.<List<Success>>waitFor(_function);
  }
}
