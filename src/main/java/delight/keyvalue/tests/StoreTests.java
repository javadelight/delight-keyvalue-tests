package delight.keyvalue.tests;

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
  public static List<StoreTest> all() {
    TestMultiDelete _testMultiDelete = new TestMultiDelete();
    TestMultiSelect _testMultiSelect = new TestMultiSelect();
    TestThatAsynchronousPutMapCanBeStopped _testThatAsynchronousPutMapCanBeStopped = new TestThatAsynchronousPutMapCanBeStopped();
    TestThatParellelWorkerProcessesPuts _testThatParellelWorkerProcessesPuts = new TestThatParellelWorkerProcessesPuts();
    return Collections.<StoreTest>unmodifiableList(CollectionLiterals.<StoreTest>newArrayList(_testMultiDelete, _testMultiSelect, _testThatAsynchronousPutMapCanBeStopped, _testThatParellelWorkerProcessesPuts));
  }
}
