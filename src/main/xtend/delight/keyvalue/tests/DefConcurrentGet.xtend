package delight.keyvalue.tests

import delight.async.jre.Async
import delight.keyvalue.Store
import delight.functional.Success

class DefConcurrentGet implements StoreTest {

	override test(Store<String, Object> store) {

		store.putSync("1", "one");
		store.putSync("2", "two");
		store.putSync("3", "three");
		Async.waitFor [ cb |
			
			val col = Async.collect(30, cb);
			
			for (i : 1 .. 30) {

				new Thread() {
					override run() {
						store.getSync("1");
						col.createCallback.onSuccess(Success.INSTANCE)
					}
				}.start

			}
		]

	}

}