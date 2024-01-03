import net.pyel.Panel;
import net.pyel.models.Game;
import net.pyel.models.Machine;
import net.pyel.models.TerminalElement;
import net.pyel.utils.CustomHashMap;
import net.pyel.utils.CustomList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

/**
 * JUnit - some useful tests for the app
 * It includes:
 * Test on Hashing:
 * Adding
 * Removing
 * Check for collision handling
 * Retrieving
 * Test on sorting algorithm
 * Test on Custom Linked List:
 * Adding
 * Removing by index & object
 * IndexOf
 * Machines & Games:
 * Adding Machines & Games to the panel
 * Check for uniqueness when adding
 * Check if the values where added to the custom linked list and the custom hashmap
 * Retrieving the values from both storage types (by list iteration & hash calculation)
 *
 * @author Zalán Tóth
 */
class MainTests {

	CustomHashMap<Integer, Integer> integers = new CustomHashMap<>();
	CustomList<Integer> list = new CustomList<>();

	private CustomHashMap<String, Machine> machinesHash = new CustomHashMap<>();

	@Test
	void addingValuesToHashMap() {
		Assertions.assertNotEquals((Integer) 232, integers.get(777));
		integers.put(777, 232);
		integers.put(923, 533);
		integers.put(123, 321);
		integers.put(124, 321);
		Assertions.assertEquals((Integer) 232, integers.get(777));
		Assertions.assertEquals((Integer) 533, integers.get(923));
		Assertions.assertEquals((Integer) 321, integers.get(123));
		Assertions.assertNull(integers.get(122));
		Assertions.assertEquals((Integer) 321, integers.get(124));
		integers.put(1024, 55);
		integers.put(2048, 56);
		integers.put(4096, 57);
		Assertions.assertEquals((Integer) 55, integers.get(1024));
		Assertions.assertEquals((Integer) 56, integers.get(2048));
		Assertions.assertEquals((Integer) 57, integers.get(4096));

		machinesHash.put("IAmAKey", new Machine("Hello Machine", "", "", "", "", 2009, 67.5, ""));
		System.out.println(machinesHash.get("IAmAKey").getLaunchYear());

	}

	public boolean isSortedAscending(CustomList<TerminalElement> machines) {
		for (int i = 0; i < machines.size() - 1; i++) {
			if (((Machine) machines.get(i).getInspectionElemenet()).getLaunchYear() > ((Machine) machines.get(i + 1).getInspectionElemenet()).getLaunchYear()) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void testSortingAlgorithm() {
		Machine machine = new Machine("Hello Machine", "microsoft", "123", "das", "qwe", 2009, 67.5, "");
		Machine machine1 = new Machine("Alpha", "BrandA", "001", "ModelA", "TypeA", 2010, 100.0, "DescriptionA");
		Machine machine2 = new Machine("Beta", "BrandB", "002", "ModelB", "TypeB", 2015, 150.5, "DescriptionB");
		Machine machine3 = new Machine("Gamma", "BrandC", "003", "ModelC", "TypeC", 2020, 200.0, "DescriptionC");
		Machine machine4 = new Machine("Delta", "BrandD", "004", "ModelD", "TypeD", 2005, 50.5, "DescriptionD");
		Machine machine5 = new Machine("Epsilon", "BrandE", "005", "ModelE", "TypeE", 2018, 120.0, "DescriptionE");
		Machine machine6 = new Machine("Zeta", "BrandF", "006", "ModelF", "TypeF", 2011, 110.0, "DescriptionF");
		Machine machine7 = new Machine("Eta", "BrandG", "007", "ModelG", "TypeG", 2012, 115.5, "DescriptionG");
		Machine machine8 = new Machine("Theta", "BrandH", "008", "ModelH", "TypeH", 2016, 130.0, "DescriptionH");
		Machine machine9 = new Machine("Iota", "BrandI", "009", "ModelI", "TypeI", 2017, 140.5, "DescriptionI");
		Machine machine10 = new Machine("Kappa", "BrandJ", "010", "ModelJ", "TypeJ", 2021, 210.0, "DescriptionJ");
		CustomList<Machine> unsortedArray = new CustomList<>();
		unsortedArray.add(machine);
		unsortedArray.add(machine1);
		unsortedArray.add(machine2);
		unsortedArray.add(machine3);
		unsortedArray.add(machine4);
		unsortedArray.add(machine5);
		unsortedArray.add(machine6);
		unsortedArray.add(machine7);
		unsortedArray.add(machine8);
		unsortedArray.add(machine9);
		unsortedArray.add(machine10);
		CustomList<TerminalElement> te = new CustomList<>();
		for (Machine m : unsortedArray) {
			te.add(new TerminalElement(m.getName(), m));
		}
		Panel panel = new Panel(false, unsortedArray, null, null, null);
		CustomList<TerminalElement> list = panel.sortByYear(te);
		assertTrue(isSortedAscending(list));

	}

	@Test
	public void testUniquenessWhenAddingAMachineToListAndCheckHashingImplementation() {
		Machine machine = new Machine("Alpha", "microsoft", "123", "das", "qwe", 2009, 67.5, "");
		Machine machine1 = new Machine("Alpha", "BrandA", "001", "ModelA", "TypeA", 2010, 100.0, "DescriptionA");
		Machine machine2 = new Machine("Alpha", "BrandB", "002", "ModelB", "TypeB", 2015, 150.5, "DescriptionB");
		Machine machine3 = new Machine("Gamma", "BrandC", "003", "ModelC", "TypeC", 2020, 200.0, "DescriptionC");
		Machine machine4 = new Machine("Delta", "BrandD", "004", "ModelD", "TypeD", 2005, 50.5, "DescriptionD");
		Machine machine5 = new Machine("Delta", "BrandE", "005", "ModelE", "TypeE", 2018, 120.0, "DescriptionE");
		Machine machine6 = new Machine("Delta", "BrandF", "006", "ModelF", "TypeF", 2011, 110.0, "DescriptionF");
		Machine machine7 = new Machine("Delta", "BrandG", "007", "ModelG", "TypeG", 2012, 115.5, "DescriptionG");
		Machine machine8 = new Machine("Delta", "BrandH", "008", "ModelH", "TypeH", 2016, 130.0, "DescriptionH");
		Machine machine9 = new Machine("Delta", "BrandI", "009", "ModelI", "TypeI", 2017, 140.5, "DescriptionI");
		Machine machine10 = new Machine("Kappa", "BrandJ", "010", "ModelJ", "TypeJ", 2021, 210.0, "DescriptionJ");
		Panel panel = new Panel(false, new CustomList<>(), new CustomHashMap<>(), null, null);
		panel.addMachine(machine);
		panel.addMachine(machine1);
		panel.addMachine(machine2);
		panel.addMachine(machine3);
		panel.addMachine(machine4);
		panel.addMachine(machine5);
		panel.addMachine(machine6);
		panel.addMachine(machine7);
		panel.addMachine(machine8);
		panel.addMachine(machine9);
		panel.addMachine(machine10);

		Assertions.assertEquals(machine, panel.getMachines().get(0)); //Alpha
		Assertions.assertEquals(machine3, panel.getMachines().get(1)); //Gamma
		Assertions.assertEquals(machine4, panel.getMachines().get(2)); //Delta
		Assertions.assertEquals(machine10, panel.getMachines().get(3)); //Kappa
		Assertions.assertNull(panel.getMachines().get(4));
		Assertions.assertEquals(machine, panel.getMachinesHash().get("Alpha")); //Alpha
		Assertions.assertEquals(machine3, panel.getMachinesHash().get("Gamma")); //Gamma
		Assertions.assertEquals(machine4, panel.getMachinesHash().get("Delta")); //Delta
		Assertions.assertEquals(machine10, panel.getMachinesHash().get("Kappa")); //Kappa
		Assertions.assertNull(panel.getMachinesHash().get("Beta"));
	}

	@Test
	public void testUniquenessWhenAddingGameToListAndCheckHashingImplementationForIt() {
		Game game1 = new Game(new Machine("Ex1", "", "2131", "", "", 2000, 50, "url"), "Alpha", "123", "das", "qwe", 2009, "URL", new CustomList<>());
		Game game2 = new Game(new Machine("Ex2", "", "asdasd", "", "", 2000, 50, "url"), "Beta", "123", "das", "qwe", 2009, "URL", new CustomList<>());
		Game game3 = new Game(new Machine("Ex3", "", "352", "", "", 2000, 50, "url"), "Beta", "123", "das", "qwe", 2009, "URL", new CustomList<>());
		Game game4 = new Game(new Machine("Ex4", "", "5325", "", "", 2000, 50, "url"), "Epsilon", "123", "das", "qwe", 2009, "URL", new CustomList<>());
		Panel panel = new Panel(false, new CustomList<>(), new CustomHashMap<>(), new CustomList<>(), new CustomHashMap<>());
		panel.addGame(game1);
		panel.addGame(game2);
		panel.addGame(game3);
		panel.addGame(game4);

		Assertions.assertEquals(game1, panel.getGames().get(0)); //Alpha
		Assertions.assertEquals(game2, panel.getGames().get(1)); //Beta
		Assertions.assertEquals(game4, panel.getGames().get(2)); //Epsilon
		Assertions.assertNull(panel.getMachines().get(3));
		Assertions.assertEquals(game1, panel.getGamesHash().get("Alpha")); //Alpha
		Assertions.assertEquals(game2, panel.getGamesHash().get("Beta")); //Beta
		Assertions.assertEquals(game4, panel.getGamesHash().get("Epsilon")); //Epsilon
		Assertions.assertNull(panel.getGamesHash().get("Gamma"));
	}

	@Test
	void removeEntriesFromHashing() {
		integers.put(777, 232);
		integers.put(923, 533);
		integers.put(123, 321);
		integers.put(124, 321);
		Assertions.assertEquals((Integer) 232, integers.get(777));
		Assertions.assertEquals((Integer) 533, integers.get(923));
		Assertions.assertEquals((Integer) 321, integers.get(123));
		Assertions.assertNull(integers.get(122));
		Assertions.assertEquals((Integer) 321, integers.get(124));
		integers.put(1024, 55);
		integers.put(2048, 56);
		integers.put(4096, 57);
		Assertions.assertEquals((Integer) 55, integers.get(1024));
		Assertions.assertEquals((Integer) 56, integers.get(2048));
		Assertions.assertEquals((Integer) 57, integers.get(4096));
		integers.remove(1024);
		Assertions.assertNull(integers.get(1024));
		Assertions.assertEquals((Integer) 56, integers.get(2048));
		Assertions.assertEquals((Integer) 57, integers.get(4096));
		integers.remove(4096);
		Assertions.assertNull(integers.get(4096));
		Assertions.assertEquals((Integer) 56, integers.get(2048));
		integers.remove(2048);
		Assertions.assertNull(integers.get(2048));
		integers.remove(123);
		Assertions.assertNull(integers.get(123));
		integers.put(124, 321);
	}

	@Test
	void addingValuesToCustomLinkedList() {
		list.add(777);
		list.add(124);
		list.add(546);
		list.add(987);
		list.add(4);
		list.add(9997);
		Assertions.assertEquals((Integer) 777, list.get(0));
		Assertions.assertEquals((Integer) 124, list.get(1));
		Assertions.assertEquals((Integer) 546, list.get(2));
		Assertions.assertEquals((Integer) 987, list.get(3));
		Assertions.assertEquals((Integer) 4, list.get(4));
		Assertions.assertEquals((Integer) 9997, list.get(5));
		Assertions.assertNull(integers.get(6));

	}


	@Test
	void removingValuesFromCustomLinkedList() {
		list.add(777);
		list.add(124);
		list.add(546);
		list.add(987);
		list.remove((Integer) 987);
		Assertions.assertEquals((Integer) 124, list.get(1));
		list.remove(1);
		list.add(4);
		list.add(9997);
		Assertions.assertEquals((Integer) 777, list.get(0));
		Assertions.assertEquals((Integer) 546, list.get(1));
		Assertions.assertEquals((Integer) 4, list.get(2));
		Assertions.assertEquals((Integer) 9997, list.get(3));
		Assertions.assertNull(integers.get(6));

	}


	@Test
	void checkIndexOfInLinkedList() {
		Assertions.assertTrue(list.isEmpty());
		list.add(777);
		list.add(124);
		list.add(546);
		list.add(987);
		list.add(4);
		list.add(9997);
		Assertions.assertEquals(0, list.indexOf((Integer) 777));
		Assertions.assertEquals(1, list.indexOf((Integer) 124));
		Assertions.assertEquals(2, list.indexOf((Integer) 546));
		Assertions.assertEquals(3, list.indexOf((Integer) 987));
		Assertions.assertEquals(4, list.indexOf((Integer) 4));
		Assertions.assertEquals(5, list.indexOf((Integer) 9997));
		Assertions.assertNull(integers.get(6));

	}
}