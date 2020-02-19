public class FirutFactory {
	public static Firut getFirut(String type) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {

		if (type.equalsIgnoreCase("apple")) {

			return new Apple();
		} else if (type.equalsIgnoreCase("banana")) {
			return new Banana();
		} else {
			System.out.println("没有对应的水果类");
			return null;
		}

		// Class firut = Class.forName(type);
		// return (Firut) firut.newInstance();
	}
}
