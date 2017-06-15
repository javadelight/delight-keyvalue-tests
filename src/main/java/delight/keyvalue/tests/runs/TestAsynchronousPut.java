package delight.keyvalue.tests.runs;

import delight.concurrency.jre.JreConcurrency;
import delight.functional.Function;
import delight.keyvalue.Store;
import delight.keyvalue.Stores;
import delight.keyvalue.tests.StoreTests;
import org.junit.Test;

@SuppressWarnings("all")
public class TestAsynchronousPut {
  @Test
  public void test() {
    final Function<Void, Store<String, Object>> _function = new Function<Void, Store<String, Object>>() {
      @Override
      public Store<String, Object> apply(final Void it) {
        JreConcurrency _jreConcurrency = new JreConcurrency();
        return Stores.<String, Object>enforceAsynchronousPut(10, _jreConcurrency, 
          Stores.<String, Object>hashMap());
      }
    };
    StoreTests.testAndStartAndStop(_function);
  }
}
