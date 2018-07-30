
public class Principal {

	public static void main(String[] args) {

		Runnable a = new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println("a");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		};

		Runnable b = new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println("b");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		Thread ta = new Thread(a);
		Thread tb = new Thread(b);
		

		ta.start();
		tb.start();

	}

}
