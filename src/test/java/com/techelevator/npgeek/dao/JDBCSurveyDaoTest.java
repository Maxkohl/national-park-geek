package com.techelevator.npgeek.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.npgeek.pageobject.FavoriteParksPage;
import com.techelevator.npgeek.pageobject.HomePage;
import com.techelevator.npgeek.pageobject.SurveyPage;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Survey;

public class JDBCSurveyDaoTest extends DAOIntegrationTest {

	private SurveyDao dao;
	private JdbcTemplate jdbc;

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
		dao = new JDBCSurveyDao(super.getDataSource());
		jdbc = new JdbcTemplate(super.getDataSource());
	}

	@Test
	public void submits_survey_successfully() {

		Survey expected = getTestSurvey("GTNP");
		Long surveyid = dao.submitSurvey(expected);

		Survey actual = getSurveyFromId(surveyid);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void pulls_correct_amt_of_favorite_parks() {

		truncateSurveyResults();

		dao.submitSurvey(getTestSurvey("GTNP"));
		dao.submitSurvey(getTestSurvey("GTNP"));
		dao.submitSurvey(getTestSurvey("GTNP"));

		dao.submitSurvey(getTestSurvey("YNP"));
		dao.submitSurvey(getTestSurvey("YNP"));

		dao.submitSurvey(getTestSurvey("YNP2"));

		Map<Park, Integer> favParks = dao.getFavoriteParks();

		Assert.assertEquals(3, favParks.size());
	}

	@Test
	public void sets_survey_count_correctly() {

		truncateSurveyResults();

		dao.submitSurvey(getTestSurvey("GTNP"));
		dao.submitSurvey(getTestSurvey("GTNP"));
		dao.submitSurvey(getTestSurvey("GTNP"));

		dao.submitSurvey(getTestSurvey("YNP"));
		dao.submitSurvey(getTestSurvey("YNP"));

		dao.submitSurvey(getTestSurvey("YNP2"));

		Map<Park, Integer> favParks = dao.getFavoriteParks();

		for (Entry<Park, Integer> entry : favParks.entrySet()) {

			Park park = entry.getKey();
			int surveyCount = entry.getValue();

			if (park.getCode().equalsIgnoreCase("GTNP")) {
				Assert.assertEquals(3, surveyCount);
			}

			else if (park.getCode().equalsIgnoreCase("YNP")) {
				Assert.assertEquals(2, surveyCount);
			}

			else if (park.getCode().equalsIgnoreCase("YNP2")) {
				Assert.assertEquals(1, surveyCount);
			}
		}	
	}

	@Test
	public void orders_parks_correctly_based_on_survey_count() {

		truncateSurveyResults();

		dao.submitSurvey(getTestSurvey("GTNP"));
		dao.submitSurvey(getTestSurvey("GTNP"));
		dao.submitSurvey(getTestSurvey("GTNP"));

		dao.submitSurvey(getTestSurvey("YNP"));
		dao.submitSurvey(getTestSurvey("YNP"));

		dao.submitSurvey(getTestSurvey("YNP2"));

		Map<Park, Integer> favParks = dao.getFavoriteParks();

		int i = 0;
		for (Entry<Park, Integer> entry : favParks.entrySet()) {

			Park park = entry.getKey();

			if (park.getCode().equalsIgnoreCase("GTNP")) {
				Assert.assertEquals(0, i);
			}

			else if (park.getCode().equalsIgnoreCase("YNP")) {
				Assert.assertEquals(1, i);
			}

			else if (park.getCode().equalsIgnoreCase("YNP2")) {
				Assert.assertEquals(2, i);
			}

			i++;
		}	
	}

	private void truncateSurveyResults() {
		String sql = "TRUNCATE survey_result CASCADE";
		jdbc.update(sql);
	}

	private Survey getTestSurvey(String parkCode) {
		Survey survey = new Survey();

		survey.setFavoriteParkCode(parkCode);
		survey.setEmail("test@test.com");
		survey.setActivityLevel("sedentary");
		survey.setState("Ohio");

		return survey;
	}

	private Survey getSurveyFromId(Long surveyid) {
		String sql = "SELECT * FROM survey_result WHERE surveyid = ?";

		SqlRowSet result = jdbc.queryForRowSet(sql, surveyid);
		result.next();

		Survey returned = new Survey();

		returned.setFavoriteParkCode(result.getString("parkcode"));
		returned.setEmail(result.getString("emailaddress"));
		returned.setState(result.getString("state"));
		returned.setActivityLevel(result.getString("activitylevel"));

		return returned;
	}
	
	@Test
	public void find_element_by_tag_name() {
		WebElement header = webDriver.findElement(By.tagName("header"));
		WebElement footer = webDriver.findElement(By.tagName("footer"));
		assertNotNull(header);
		assertNotNull(footer);
	}
	
	@Test
	public void forms_can_be_edited_and_submitted() {
		FavoriteParksPage resultPage = homePage.clickSurveyLink()
													.enterPark("Yosemite National Park")
													.enterEmail("seleniumTestEmail@gmail.com")
													.enterState("California")
													.enterActivity("inactive")
													.submitForm();

		Assert.assertTrue(resultPage.getParkNameList("Yosemite National Park").size() > 0);
	}
	  
	@Test
	public void submit_vote_for_new_park() {
		FavoriteParksPage resultPage = homePage.clickSurveyLink()
													.enterPark("Great Smoky Mountains National Park")
													.enterEmail("shrimpLover94@gmail.com")
													.enterState("Ohio")
													.enterActivity("active")
													.submitForm();

		Assert.assertTrue(resultPage.getParkNameList("Great Smoky Mountains National Park").size() > 0);
	}
}
