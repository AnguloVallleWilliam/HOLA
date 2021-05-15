@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMockitoApplicationTests {

    private final Integer id = 3;

	@Autowired
	private SapdropService service;

	@MockBean
	private SapdropRepository repository;

	@Test
	public void getSapdropsTest() {
		mockito.when(repository.findAll()).thenReturn(Stream
				.of(new Sapdrop(id, "Mi primer partido", "Fue el dia 25 de mayo", "25/08/2019",null), new Sapdrop(id, "Mi primer partido", "Fue el dia 25 de mayo", "25/08/2019",null)).collect(Collectors.toList()));
		assertEquals(2, service.getSapdrops().size());
	}

	@Test
	public void getSapdropsbyIdTest() {
		//Integer id = 3;
		mockito.when(repository.findById(3))
				.thenReturn(Stream.of(new Sapdrop(id, "Oferta Trabajo", "Fue el dia 25 de mayo", "25/08/2019",null)).collect(Collectors.toList()));
		assertEquals(1, service.getSapdropsbyId(address).size());
	}

	@Test
	public void saveSapdropsTest() {
		Sapdrop sapdrop = new Sapdrop(999, "primer partido", "Fue el dia 25 de mayo", "25/08/2019",null);
		mockito.when(repository.save(sapdrop)).thenReturn(sapdrop);
		assertEquals(user, service.addSapdrop(sapdrop));
	}

	@Test
	public void deleteSapdropsTest() {
		Sapdrop sapdrop = new Sapdrop(99, "partido", "Fue el dia 25 de mayo", "25/08/2019",null);
		service.deleteSapdrop(sapdrop);
		verify(repository, times(1)).delete(sapdrop);
	}

}