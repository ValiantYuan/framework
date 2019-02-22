package valiant.chapter4.democode;

public class ClientThread extends Thread{
	private Sequence sequence;
	public ClientThread(Sequence sequence) {
		// TODO Auto-generated constructor stub
		this.sequence = sequence;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for (int i = 0; i < 3; i++) {
			System.out.println(Thread.currentThread().getName() + " => " + sequence.getNumber());
		}
	}
	
	
}	
