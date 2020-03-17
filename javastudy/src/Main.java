import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<>();
		for (int i = 0; i < 100; i++) {
			map.put(i, String.valueOf(i));
			map.remove(i - 1);
		}

		System.out.println(map.size());
		// for (short i = 0; i < 100; i++) {
		// System.out.println(map.get(i));
		//
		// }
	}

}
