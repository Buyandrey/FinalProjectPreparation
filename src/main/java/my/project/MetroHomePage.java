package my.project;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.checkerframework.checker.units.qual.K;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofSeconds;

public class MetroHomePage {
/*
@FindBy(how = How.XPATH, using = "/html/head/title") private SelenideElement titleOfThePage;
 */
    // Кликабельный логотип Яндекса в шапке*/
    // локатор кнопки выпадающего списка городов по имени класса
    @FindBy(how = How.CLASS_NAME, using = "select_metro__button")
            private WebElement selectCityButton;

    // локатор поля ввода «Откуда» по XPATH, поиск по плейсхолдеру
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Откуда']")
            private WebElement fieldFrom;

    // локатор поля ввода «Куда» по XPATH, поиск по плейсхолдеру
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Куда']")
    private WebElement fieldTo;


    // локатор коллекций станций «Откуда» и «Куда» маршрута по имени класса
    @FindBy(how = How.CLASS_NAME, using = "route-details-block__terminal-station")
    private ElementsCollection routeStationFromTo;

    // метод ожидания загрузки страницы: проверили видимость станции «Театральная»
    public void waitForLoadHomePage(){
        // найди веб-элемент по тексту, добавь ожидание 8 секунд
        $(byText("Театральная")).shouldBe(visible,ofSeconds(8));
    }

    // метод выбора города по названию
    public void chooseCity(String cityName){
        // кликни по выпадающему списку городов
        selectCityButton.click();
        // выбери нужный город
        $(byText(cityName)).click();
    }

    // метод ввода названия станции в поле «Откуда»
    public void setStationFrom(String stationFrom){
        // введи название станции в поле ввода, а затем с помощью клавиш «Вниз» и Enter выбрали его в выпадающем списке саджеста
        fieldFrom.sendKeys(stationFrom,Keys.DOWN,Keys.ENTER);
    }

    // метод ввода названия станции в поле «Куда»
    public void setStationTo(String stationTo){
        // введи название станции в поле ввода, а затем с помощью клавиш «Вниз» и Enter выбери его в выпадающем списке саджеста
        fieldTo.sendKeys(stationTo,Keys.DOWN,Keys.ENTER);

    }

    // метод ожидания построения маршрута: проверяется видимость кнопки «Получить ссылку на маршрут»
    public void waitForLoadRoute(){
        // ищется веб-элемент по тексту
        $(byText("Получить ссылку на маршрут")).shouldBe(visible);
    }

    // метод построения маршрута
    public void buildRoute(String valueFrom, String valueTo){
        // указание станции «Откуда»
        setStationFrom(valueFrom);
        // указание станции «Куда»
        setStationTo(valueTo);
        // ожидание построения маршрута
       waitForLoadRoute();
    }

    // метод получения станции «Откуда» построенного маршрута
    public SelenideElement getRouteStationFrom(){
        // возвращается первый элемент коллекции — станции «Откуда» и «Куда»
        return this.routeStationFromTo.get(0);
    }

    // метод получения станции «Куда» построенного маршрута
    public SelenideElement getRouteStationTo(){
        // возвращается второй элемент коллекции — станции «Откуда» и «Куда»
        return this.routeStationFromTo.get(1);
    }

}
/*
* import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofSeconds;

public class MetroHomePage {

    // локатор кнопки выпадающего списка городов по имени класса
    ...
    ... selectCityButton;

    // локатор поля ввода «Откуда» по XPATH, поиск по плейсхолдеру
    ...
    ... fieldFrom;

    // локатор поля ввода «Куда» по XPATH, поиск по плейсхолдеру
    ...
    ... fieldTo;

    // локатор коллекций станций «Откуда» и «Куда» маршрута по имени класса
    ...
    private ElementsCollection routeStationFromTo;

    // метод ожидания загрузки страницы: проверили видимость станции «Театральная»
    public void waitForLoadHomePage(){
        // найди веб-элемент по тексту, добавь ожидание 8 секунд
				$(...).shouldBe(...,ofSeconds(8));
    }

    // метод выбора города по названию
    public void chooseCity(...){
        // кликни по выпадающему списку городов
        selectCityButton...
        // выбери нужный город
				$...
    }

    // метод ввода названия станции в поле «Откуда»
    public void setStationFrom(...){
        // введи название станции в поле ввода, а затем с помощью клавиш «Вниз» и Enter выбрали его в выпадающем списке саджеста
        fieldFrom ... sendKeys(Keys.DOWN, Keys.ENTER);
    }

    // метод ввода названия станции в поле «Куда»
    public void setStationTo(...){
        // введи название станции в поле ввода, а затем с помощью клавиш «Вниз» и Enter выбери его в выпадающем списке саджеста
        fieldTo ... sendKeys(Keys.DOWN, Keys.ENTER);
    }

    // метод ожидания построения маршрута: проверяется видимость кнопки «Получить ссылку на маршрут»
    public void waitForLoadRoute(){
        // ищется веб-элемент по тексту
				$...
    }

    // метод построения маршрута
    public void buildRoute(..., ...){
        // указание станции «Откуда»
        setStationFrom(...);
        // указание станции «Куда»
        setStationTo(...);
        // ожидание построения маршрута
        ...
    }

    // метод получения станции «Откуда» построенного маршрута
    public SelenideElement getRouteStationFrom(){
        // возвращается первый элемент коллекции — станции «Откуда» и «Куда»
        return this.routeStationFromTo...
    }

    // метод получения станции «Куда» построенного маршрута
    public SelenideElement getRouteStationTo(){
        // возвращается второй элемент коллекции — станции «Откуда» и «Куда»
        return this.routeStationFromTo...
    }

}
* */