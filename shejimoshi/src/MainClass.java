public  class MainClass {

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {

		FirutFactory ff1 = new AppleFactory();
		Firut apple=ff1.getFirut();
		apple.get();
		//abstract
	}
}
