public class AppleFactory implements FirutFactory {

	@Override
	public Firut getFirut() {

		return new Apple();
	}

}
