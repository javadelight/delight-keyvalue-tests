package delight.keyvalue.tests;

import delight.keyvalue.Store;

@SuppressWarnings("all")
public interface StoreTest {
  public abstract void test(final Store<String, Object> store);
}
