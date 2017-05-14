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
public class DefConcurrentGetDifferentQuery implements StoreTest {
  @Override
  public void test(final Store<String, Object> store) {
    IntegerRange _upTo = new IntegerRange(1, 50);
    for (final Integer j : _upTo) {
      store.putSync(("" + j), ("e" + j));
    }
    final Operation<List<Success>> _function = new Operation<List<Success>>() {
      @Override
      public void apply(final ValueCallback<List<Success>> cb) {
        final Aggregator<Success> col = Async.<Success>collect(50, cb);
        IntegerRange _upTo = new IntegerRange(1, 50);
        for (final Integer i : _upTo) {
          new Thread() {
            @Override
            public void run() {
              store.getSync(("" + i));
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
