import net.pyel.Panel;
import net.pyel.models.Machine;
import net.pyel.models.TerminalElement;
import net.pyel.utils.CustomHashMap;
import net.pyel.utils.CustomList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

/**
 * JUnit - some useful tests for the app
 *
 * @author Zalán Tóth
 */
class MainTests {

	CustomHashMap<Integer, Integer> integers = new CustomHashMap<>();

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
		CustomList<Machine> unsortedArray = new CustomList<Machine>();
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
		CustomList<TerminalElement> te = new CustomList<TerminalElement>();
		for (Machine m : unsortedArray){
			te.add(new TerminalElement(m.getName(), m));
		}
		Panel panel = new Panel(false,unsortedArray,null,null,null);
		CustomList<TerminalElement> list = panel.sortByYear(te);
		assertTrue(isSortedAscending(list));

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

	/*Port port1, port2, port3, port4, port5, port6;
	ContainerShip ship1, ship2;
	Container container1, container2, container3, container4;
	Pallet pallet1, pallet2, pallet3, pallet4, pallet5, pallet6, pallet7, pallet8, pallet9, pallet10, pallet11, pallet12;*//*
	Cargo cargo = new Cargo(new CustomList<>(), new CustomList<>(), new CustomList<>());


	Port port1 = new Port("Budapest", 2133, "HU", new CustomList<>(), new CustomList<>());
	Port port2 = new Port("Dunaújváros", 2133, "HU", new CustomList<>(), new CustomList<>());
	Port port3 = new Port("Budapest", 4633, "HU", new CustomList<>(), new CustomList<>());
	Port port4 = new Port("Siófok", 2222, "HU", new CustomList<>(), new CustomList<>());
	Port port5 = new Port("Győr", 3333, "HU", new CustomList<>(), new CustomList<>());
	Port port6 = new Port("Waterford", 0, "IRELAND", new CustomList<>(), new CustomList<>());


	ContainerShip ship1 = new ContainerShip("Shippo", "112317", "HU", "http", new CustomList<>());
	ContainerShip ship2 = new ContainerShip("Shuppi", "141314", "HU", "http", new CustomList<>());

	Container container1 = new Container(1221, 10, new CustomList<>());
	Container container2 = new Container(5221, 20, new CustomList<>());
	Container container3 = new Container(7721, 20, new CustomList<>());
	Container container4 = new Container(8897, 20, new CustomList<>());

	Pallet pallet1 = new Pallet("Electronics", "ES-FC0023", 5, (float) 17.99, 500, 50);
	Pallet pallet2 = new Pallet("Electronics", "ES-FC0028", 5, (float) 17.99, 500, 300);
	Pallet pallet3 = new Pallet("Electronics", "ES-FC0029", 5, (float) 17.99, 500, 300);
	Pallet pallet4 = new Pallet("Electronics", "ES-FC0040", 5, (float) 17.99, 500, 300);
	Pallet pallet5 = new Pallet("Electronics", "ES-FC0041", 5, (float) 17.99, 500, 300);
	Pallet pallet6 = new Pallet("Clothes", "IE-FC0029", 100, (float) 3.99, 500, 400);
	Pallet pallet7 = new Pallet("Clothes", "IE-FC0040", 100, (float) 3.99, 500, 400);
	Pallet pallet8 = new Pallet("Clothes", "IE-FC0041", 100, (float) 3.99, 500, 400);
	Pallet pallet9 = new Pallet("Hot sandwich grater", "HU-FC0001", 100, (float) 40, 1000, 500);
	Pallet pallet10 = new Pallet("Hot sandwich grater", "HU-FC0002", 100, (float) 40, 1000, 500);
	Pallet pallet11 = new Pallet("Indicator buoy sharpener", "CH-FC2323", 2, (float) 800, 2000, 640);
	Pallet pallet12 = new Pallet("Indicator buoy sharpener", "CH-FC6543", 2, (float) 800, 2000, 640);
	Pallet pallet13 = new Pallet("Electronics", "GB-FC0001", 5, (float) 17.99, 500, 50);

	/*
		@Before
		public void setUp() throws Exception {

		}

		@After
		public void tearDown() throws Exception {
		}
	*/
	/*
	@BeforeEach
	void before() {
		BackgroundController.setCargo(new Cargo(new CustomList<>(), new CustomList<>(), new CustomList<>()));
		cargo = BackgroundController.getCargo();
	}

	@Test
	void addingPortToSystemTestingForUniqueCode() {
		cargo.addPort(port1); //Should add
		cargo.addPort(port2); //Should not add
		assertNull(cargo.getPorts().get(1));
		cargo.addPort(port3); //Should add
		cargo.addPort(port4); //Should add
		cargo.addPort(port5); //Should add
		cargo.addPort(port6); //Should not add
		assertNull(cargo.getPorts().get(4));
		cargo.addPort(port4); //Should not add
		assertNull(cargo.getPorts().get(4));
		assertEquals(2133, cargo.getPorts().get(0).getCode());
		assertEquals(4633, cargo.getPorts().get(1).getCode());
		assertEquals(2222, cargo.getPorts().get(2).getCode());
		assertEquals(3333, cargo.getPorts().get(3).getCode());
	}

	@Test
	void updatingPortTestingForUniqueCode() {
		cargo.addPort(port1);
		cargo.addPort(port3);
		assertEquals(2133, cargo.getPorts().get(0).getCode());
		assertEquals(4633, cargo.getPorts().get(1).getCode());
		cargo.updatePort(cargo.getPorts().get(1), "Triest", 22, "IT");
		assertEquals(22, cargo.getPorts().get(1).getCode());
		cargo.updatePort(cargo.getPorts().get(1), "Triest", 2133, "IT");
		assertEquals(22, cargo.getPorts().get(1).getCode());
	}

	@Test
	void addingShipToPortTestingForUniqueID() {
		cargo.addPort(port4);

		cargo.getPorts().get(0).addContainerShip(ship1); //Should add
		cargo.getPorts().get(0).addContainerShip(ship1); //Should not add
		assertNull(port4.getShips().get(1));

		cargo.getPorts().get(0).addContainerShip(ship2); //Should add
		assertEquals("112317", port4.getShips().get(0).getID());
		assertEquals("141314", port4.getShips().get(1).getID());
	}

	@Test
	void addingShipToSeaTestingForUniqueID() {
		cargo.addShipToSea(ship1); //Should add
		cargo.addShipToSea(ship1); //Should not add
		assertNull(cargo.getShipsOnSea().get(1));

		cargo.addShipToSea(ship2); //Should add
		assertEquals("112317", cargo.getShipsOnSea().get(0).getID());
		assertEquals("141314", cargo.getShipsOnSea().get(1).getID());
	}

	@Test
	void addingContainerToShipTestingForUniqueID() {
		ship1.addContainer(container1); //Should add
		ship1.addContainer(container1); //Should not add
		assertNull(ship1.getContainers().get(1));

		ship1.addContainer(container2); //Should add
		assertEquals(1221, ship1.getContainers().get(0).getID());
		assertEquals(5221, ship1.getContainers().get(1).getID());
	}

	@Test
	void addingPalletToContainerTestingForUniqueIM() {
		container1.addPallet(pallet1); //Should add
		container1.addPallet(pallet1); //Should not add
		assertNull(container1.getPallets().get(1));

		container1.addPallet(pallet2); //Should add
		assertEquals("ES-FC0023", container1.getPallets().get(0).getInternationalMark());
		assertEquals("ES-FC0028", container1.getPallets().get(1).getInternationalMark());
	}


	@Test
	void addingPalletToContainerTestingForSizeValidation() {
		container1.addPallet(pallet9); //Should add (+500=500 <640)
		container1.addPallet(pallet10); //Should not add (+500=1000 !<640)
		assertNull(container1.getPallets().get(1));

		assertEquals(500, container1.getPallets().get(0).getSize());
		assertEquals(640, container1.getTotalSize());
		assertEquals(500, container1.getCurrentTakenSize());


		container1.addPallet(pallet1); //Should add (+50=520 <640)
		assertEquals(550, container1.getCurrentTakenSize());

	}


	@Test
	void palletAddAndRemoveChangesSizeProperlyInContainer() {
		//CHECKING THE LIST ITSELF
		container1.addPallet(pallet9); //Should add (+500=500 <640)
		container1.addPallet(pallet10); //Should not add (+500=1000 !<640)
		assertEquals(1, container1.getPallets().getSize());
		container1.addPallet(pallet1); //Should add
		container1.addPallet(pallet13); //Should add
		assertEquals(3, container1.getPallets().getSize());
		container1.removePallet(pallet1); //Should remove
		assertEquals(2, container1.getPallets().getSize());
		container1.removePallet(pallet9); //Should remove
		assertEquals(1, container1.getPallets().getSize());
		container1.removePallet(pallet13); //Should remove
		assertEquals(0, container1.getPallets().getSize());


		//CHECKING THE CONTAINER SIZE ITSELF container1 has the capacity of 640 (10*8*8)
		container1.addPallet(pallet9); //Should add (+500=500 <640)
		assertEquals(500, container1.getCurrentTakenSize());
		container1.addPallet(pallet10); //Should not add (+500=1000 !<640)
		assertEquals(500, container1.getCurrentTakenSize());
		container1.addPallet(pallet1); //Should add
		assertEquals(550, container1.getCurrentTakenSize());
		container1.addPallet(pallet13); //Should add
		assertEquals(600, container1.getCurrentTakenSize());
		container1.removePallet(pallet1); //Should remove
		assertEquals(550, container1.getCurrentTakenSize());
		container1.removePallet(pallet9); //Should remove
		assertEquals(50, container1.getCurrentTakenSize());
		container1.removePallet(pallet13); //Should remove
		assertEquals(0, container1.getCurrentTakenSize());
	}

	@Test
	void removingAndAddingContainerWithSizeCheck() {
		ship2.addContainer(container3);
		assertEquals(1, ship2.getContainers().getSize());
		ship2.addContainer(container4);
		assertEquals(2, ship2.getContainers().getSize());
		ship2.addContainer(container1);
		assertEquals(3, ship2.getContainers().getSize());
		ship2.addContainer(container1); //SHOULD NOT ADD, SAME ID
		assertEquals(3, ship2.getContainers().getSize());
		ship2.removeContainer(container4);
		assertEquals(2, ship2.getContainers().getSize());
		ship2.removeContainerByIndex(0);
		ship2.removeContainerByIndex(0);
		assertEquals(0, ship2.getContainers().getSize());

	}

	@Test
	void updatingContainerSizeWhileCheckingCapacity() {
		ship2.addContainer(container3);
		assertEquals(1280, ship2.getContainers().get(0).getTotalSize()); //length 20
		ship2.updateContainer(container3, 7721, 10);
		assertEquals(640, ship2.getContainers().get(0).getTotalSize()); //length 10
		ship2.updateContainer(container3, 7721, 20);
		assertEquals(1280, ship2.getContainers().get(0).getTotalSize()); //length 20
		ship2.getContainers().get(0).addPallet(pallet9); //500 in size SHOULD ADD
		ship2.getContainers().get(0).addPallet(pallet10); //500 in size SHOULD ADD
		assertEquals(1280, ship2.getContainers().get(0).getTotalSize());
		assertEquals(1000, ship2.getContainers().get(0).getCurrentTakenSize());
		ship2.updateContainer(container3, 7721, 10); //SHOULD NOT UPDATE AS CONTENTS ARE BIGGER THEN THE CAPACITY SIZE - capacity would be 640 while size is 1000
		assertEquals(1280, ship2.getContainers().get(0).getTotalSize()); //length 20
		ship2.getContainers().get(0).removePallet(pallet10);
		ship2.updateContainer(container3, 7721, 10); //SHOULD UPDATE AS CONTENTS ARE SMALLER THAN THE CAPACITY WOULD BE
		assertEquals(640, ship2.getContainers().get(0).getTotalSize()); //length 10


	}*/
}