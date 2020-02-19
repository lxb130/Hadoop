public class MainClass {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		FirutFactory firutFactory = new FirutFactory();
		Firut apple = firutFactory.getFirut("Apple");
		apple.get();
		Firut banana = firutFactory.getFirut("Banana");
		banana.get();
	}
}
