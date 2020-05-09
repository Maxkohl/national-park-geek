package com.techelevator.npgeek.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.pageobject.FavoriteParksPage;
import com.techelevator.npgeek.pageobject.HomePage;
import com.techelevator.npgeek.pageobject.ParkDetailsPage;

public class JDBCParkDaoTest extends DAOIntegrationTest {

	private ParkDao dao;
	
	private static WebDriver webDriver;
	private HomePage homePage;
	
	@BeforeClass
	public static void openWebBrowserForTesting() {
		
		String homeDir = System.getProperty("user.home");
		System.setProperty("webdriver.chrome.driver", homeDir+"/dev-tools/chromedriver/chromedriver");
		webDriver = new ChromeDriver();
	}
	
	@Before
	public void openHomePage() {
		webDriver.get("http://localhost:8080/m3-java-capstone/");
		homePage = new HomePage(webDriver);
	}
	
	@AfterClass
	public static void closeWebBrowser() {
		webDriver.close();
	}
	
	@Before
	public void setup() {
		dao = new JDBCParkDao(super.getDataSource());
	}

	@Test
	public void get_parks_pulls_all_parks_in_table() {

		List<Park> parks = dao.getParks();

		Assert.assertEquals(10, parks.size());

	}

	@Test
	public void parks_returned_have_all_attributes() {

		List<Park> parks = dao.getParks();

		for (Park park : parks) {
			Assert.assertNotNull(park.getCode());
			Assert.assertNotNull(park.getName());
			Assert.assertNotNull(park.getState());
			Assert.assertNotNull(park.getClimate());
			Assert.assertNotNull(park.getInspoQuote());
			Assert.assertNotNull(park.getInspoQuoteSrc());
			Assert.assertNotNull(park.getDescription());

			Assert.assertNotNull(park.getAcres());
			Assert.assertNotNull(park.getAnnualVisitorCount());

			Assert.assertNotNull(park.getElevation());
			Assert.assertNotNull(park.getCampsiteCount());
			Assert.assertNotNull(park.getYearFounded());
			Assert.assertNotNull(park.getEntryFee());
			Assert.assertNotNull(park.getAnimalSpeciesCount());
			Assert.assertNotNull(park.getMilesOfTrail());
		}
	}


	@Test
	public void get_park_by_existing_uppercase_code_pulls_correct_park() {
		Park actual = dao.getParkByCode("YNP");

		Assert.assertEquals("YNP", actual.getCode());
		Assert.assertEquals("Yellowstone National Park", actual.getName());
		Assert.assertEquals("Wyoming", actual.getState());
		Assert.assertEquals("Temperate", actual.getClimate());
		Assert.assertEquals("Yellowstone Park is no more representative of America than is Disneyland.", actual.getInspoQuote());
		Assert.assertEquals("John Steinbeck", actual.getInspoQuoteSrc());
		Assert.assertEquals("Yellowstone National Park is a protected area showcasing significant geological phenomena and processes. It is also a unique manifestation of geothermal forces, natural beauty, and wild ecosystems where rare and endangered species thrive. As the site of one of the few remaining intact large ecosystems in the northern temperate zone of earth, Yellowstone’s ecological communities provide unparalleled opportunities for conservation, study, and enjoyment of large-scale wildland ecosystem processes.", actual.getDescription());

		Assert.assertEquals((long) 2219791, (long) actual.getAcres());
		Assert.assertEquals((long) 3394326, (long) actual.getAnnualVisitorCount());

		Assert.assertEquals(8000, actual.getElevation());
		Assert.assertEquals(1900, actual.getCampsiteCount());
		Assert.assertEquals(1872, actual.getYearFounded());
		Assert.assertEquals(15, actual.getEntryFee());
		Assert.assertEquals(390, actual.getAnimalSpeciesCount());
		Assert.assertEquals(900, actual.getMilesOfTrail());
	}

	@Test
	public void get_park_by_existing_lowercase_code_pulls_correct_park() {
		Park actual = dao.getParkByCode("ynp");

		Assert.assertEquals("YNP", actual.getCode());
		Assert.assertEquals("Yellowstone National Park", actual.getName());
		Assert.assertEquals("Wyoming", actual.getState());
		Assert.assertEquals("Temperate", actual.getClimate());
		Assert.assertEquals("Yellowstone Park is no more representative of America than is Disneyland.", actual.getInspoQuote());
		Assert.assertEquals("John Steinbeck", actual.getInspoQuoteSrc());
		Assert.assertEquals("Yellowstone National Park is a protected area showcasing significant geological phenomena and processes. It is also a unique manifestation of geothermal forces, natural beauty, and wild ecosystems where rare and endangered species thrive. As the site of one of the few remaining intact large ecosystems in the northern temperate zone of earth, Yellowstone’s ecological communities provide unparalleled opportunities for conservation, study, and enjoyment of large-scale wildland ecosystem processes.", actual.getDescription());

		Assert.assertEquals((long) 2219791, (long) actual.getAcres());
		Assert.assertEquals((long) 3394326, (long) actual.getAnnualVisitorCount());

		Assert.assertEquals(8000, actual.getElevation());
		Assert.assertEquals(1900, actual.getCampsiteCount());
		Assert.assertEquals(1872, actual.getYearFounded());
		Assert.assertEquals(15, actual.getEntryFee());
		Assert.assertEquals(390, actual.getAnimalSpeciesCount());
		Assert.assertEquals(900, actual.getMilesOfTrail());
	}

	@Test
	public void get_park_by_existing_mixedcase_code_pulls_correct_park() {
		Park actual = dao.getParkByCode("yNp");

		Assert.assertEquals("YNP", actual.getCode());
		Assert.assertEquals("Yellowstone National Park", actual.getName());
		Assert.assertEquals("Wyoming", actual.getState());
		Assert.assertEquals("Temperate", actual.getClimate());
		Assert.assertEquals("Yellowstone Park is no more representative of America than is Disneyland.", actual.getInspoQuote());
		Assert.assertEquals("John Steinbeck", actual.getInspoQuoteSrc());
		Assert.assertEquals("Yellowstone National Park is a protected area showcasing significant geological phenomena and processes. It is also a unique manifestation of geothermal forces, natural beauty, and wild ecosystems where rare and endangered species thrive. As the site of one of the few remaining intact large ecosystems in the northern temperate zone of earth, Yellowstone’s ecological communities provide unparalleled opportunities for conservation, study, and enjoyment of large-scale wildland ecosystem processes.", actual.getDescription());

		Assert.assertEquals((long) 2219791, (long) actual.getAcres());
		Assert.assertEquals((long) 3394326, (long) actual.getAnnualVisitorCount());

		Assert.assertEquals(8000, actual.getElevation());
		Assert.assertEquals(1900, actual.getCampsiteCount());
		Assert.assertEquals(1872, actual.getYearFounded());
		Assert.assertEquals(15, actual.getEntryFee());
		Assert.assertEquals(390, actual.getAnimalSpeciesCount());
		Assert.assertEquals(900, actual.getMilesOfTrail());
	}

	@Test
	public void get_park_by_nonexistent_code_throws_error() {
		boolean throwsException = false;

		try {
			Park park = dao.getParkByCode("KPL");
		} catch (Exception e) {
			throwsException = true;
		}

		Assert.assertTrue(throwsException);
	}
	
	@Test
	public void click_photo_link_to_park_details_page() {
		ParkDetailsPage resultPage = homePage.clickPictureLink().clickAdditionalParkInfo();
		assertEquals("Glacier National Park", resultPage.getParkName());
	
	}
}
