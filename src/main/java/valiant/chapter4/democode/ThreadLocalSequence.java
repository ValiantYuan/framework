package valiant.chapter4.democode;

public class ThreadLocalSequence implements Sequence {
	private static MyThreadLocal<Integer> numberContainer = new MyThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};
	
	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		numberContainer.set(numberContainer.get() + 1);
		return numberContainer.get();
	}
	
	public static void main(String[] args) {
		ThreadLocalSequence threadLocalSequence = new ThreadLocalSequence();
		ClientThread clientThread1 = new ClientThread(threadLocalSequence);
		ClientThread clientThread2 = new ClientThread(threadLocalSequence);
		ClientThread clientThread3 = new ClientThread(threadLocalSequence);
		
		clientThread1.start();
		clientThread2.start();
		clientThread3.start();
	}

}
