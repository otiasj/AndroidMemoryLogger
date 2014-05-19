package com.otiasj.memoryLogger.test;

import java.util.ArrayList;
import java.util.List;

public class MyVeryLeakySingleton {
		private static MyVeryLeakySingleton instance = new MyVeryLeakySingleton();

		private List<OnSomeEventListener> mListeners = new ArrayList<OnSomeEventListener>();
		
		private MyVeryLeakySingleton() {
		}

		public static MyVeryLeakySingleton getInstance() {
			return instance;
		}
		
		public void addListener(OnSomeEventListener listener) {
			mListeners.add(listener);
		}
		
		/**
		 * Often we forget to call this,
		 * use List<weakreference<OnSomeEventListener listener>> instead.
		 * @param listener
		 */
		public void removeListener(OnSomeEventListener listener) {
			mListeners.remove(listener);
		}
}
