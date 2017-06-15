package delight.keyvalue.tests.runs;

import delight.functional.Function;
import delight.keyvalue.Store;
import delight.keyvalue.Stores;
import delight.keyvalue.jre.StoresJre;
import delight.keyvalue.tests.StoreTests;
import org.junit.Test;

@SuppressWarnings("all")
public class TestDivideWork {
  @Test
  public void test() {
    final Function<Void, Store<String, Object>> _function = new Function<Void, Store<String, Object>>() {
      @Override
      public Store<String, Object> apply(final Void it) {
        return StoresJre.<String, Object>divideWork(4, Stores.<String, Object>hashMap());
      }
    };
    StoreTests.testAndStartAndStop(_function);
  }
}
