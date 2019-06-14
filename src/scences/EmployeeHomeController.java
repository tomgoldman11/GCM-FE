package scences;


import client.ChatClient;
import client.ClientConsole;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import models.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static client.ChatClient.*;
import static models.Map.MapIDtoGetLocsTours;
import static models.Map.mapNameS;


public class EmployeeHomeController implements Initializable {
    private List<Node> AnchorPaneChildrens = new ArrayList<>();
    public static int maxReqID = 0;

    private String mapName = "";
    private int mapID = -1;

    @FXML
    private Button BackBTN;

    @FXML
    private Button AddTourBTN;

    @FXML
    private Button AddLocationBTN;

    @FXML
    private TableColumn<Location, String> LocationDescriptionCOL1;

    @FXML
    private TableColumn<Tour, String> TourDescriptionCOL;

    @FXML
    private TableColumn<Tour, String> TourDurationCOL1;

    @FXML
    private TableView<Tour> ToursTV;

    @FXML
    public static TableView<Tour> ToursTV1;

    @FXML
    private TableColumn<Location, String> ClassificationCOL1;

    @FXML
    private TableColumn<Location, String> LocationsIDCOL1;

    @FXML
    private TableColumn<Location, Boolean > AccessibilityCOL1;

    @FXML
    private TableColumn<Location, Integer> LocationIDCOL1;

    @FXML
    private TableColumn<Location, Button> ActionLocationCOL;

    @FXML
    private TableColumn<Location, String> LocationNameCOL1;

    @FXML
    private TableColumn<Tour, Button> ActionTourCOL;

    @FXML
    private TableColumn<Tour, Integer> ToursIDCOL1;


    @FXML
    private TableView<Location> LocationTV;

    @FXML
    public static TableView<Location> LocationTV1;

    @FXML
    private AnchorPane ChangePanesAP;

    @FXML
    private Pane CreateLocationPane;

    @FXML
    private Button LocationCancelBTN;

    @FXML
    private Button LocationSendRquestBTN;

    @FXML
    private Label LocationChangeSL;

    @FXML
    private TextField LocationIDT;

    @FXML
    private TextField LocationNameT;

    @FXML
    private TextField ClassificationT;

    @FXML
    private TextField LocationDescriptionT;

    @FXML
    private TextField LocationAccessabilityT;

    @FXML
    private Pane CreateLocationPane1;

    @FXML
    private Button tourCancelBTN;

    @FXML
    private Button tourSendRquestBTN;

    @FXML
    private Label TourChangeSL;

    @FXML
    private TextField TourIDT;

    @FXML
    private TextField TourDescriptionT;

    @FXML
    private TextField VisitDurationT;

    @FXML
    private TextField CityIDTourt;
    @FXML
    private Button SearchBTN;

    @FXML
    private Button PersonalInfoBTN;

    @FXML
    private Button LogOutBTN;

    @FXML
    private Pane SerachCatalogPane;

    @FXML
    private Button EmployeesBTN1;

    @FXML
    private Button StatisticsBTN;

    @FXML
    private Button WaitingForApprovalBTN;

    @FXML
    private TableView<City2> SearchTTV;

    @FXML
    public static TableView<City2> SearchTTV2;

    @FXML
    private TableColumn<City2, Integer> IDCOL;

    @FXML
    private TableColumn<City2, String> NameCOL;

    @FXML
    private TableColumn<City2, String> DescriptionCOL;

    @FXML
    private TableColumn<City2, Double> VersionCOL;

    @FXML
    private TableColumn<City2, Integer> NoMapsCOL;

    @FXML
    private TableColumn<City2, Integer> ToursCOL;

    @FXML
    private TableColumn<City2, Integer> LocationsCOL;

    @FXML
    private TableColumn<City2, Double> PriceCOL;

    @FXML
    private TableColumn<City2, Button> ActionCOL;

    @FXML
    private TableView<Map> MyMapsTTV;

    @FXML
    public static TableView<Map> MyMapsTTV1;

    @FXML
    private TableColumn<Map, Integer> IDCOLMap;

    @FXML
    private TableColumn<Map, String> descriptionnCOLMap;

    @FXML
    private TableColumn<Map, String> nameCOLMap;

    @FXML
    private TableColumn<Map, Double> priceCOLMap;

    @FXML
    private TableColumn<Map, Double> VCOLMap;

    @FXML
    private TableColumn<Map, Double> tillCOLMap;

    @FXML
    private TableColumn<Map, Button> ActionCOLMap;

    @FXML
    private Button CreateMapBTN;

    @FXML
    private Button CreateCityBTN;

    @FXML
    private Button AdvancedSearchBTN;

    @FXML
    private Button TourLocationBTN;

    @FXML
    private TextField searchBox;

    @FXML
    private TextField searchBox1;

    @FXML
    private ImageView EmployeesImage;

    @FXML
    private ImageView TourLocationIMG;

    @FXML
    private ImageView StatisticsImage;

    @FXML
    private ImageView ApprovalImage;

    @FXML
    private Text FullNameT;

    @FXML
    private Text JobTitleT;

    @FXML
    private Text EmailT;

    @FXML
    private Text PhoneT;

    @FXML
    private Text EmployeeIDT;

    @FXML
    private Text PasswordT;

    @FXML
    private TableView<Employee> EmployeeTTV;

    @FXML
    public static TableView<Employee> EmployeeTTV1;

    @FXML
    private TableColumn<Employee, String> UserIDCOL;

    @FXML
    private TableColumn<Employee, Integer> EmployeeIDCOL;

    @FXML
    private TableColumn<Employee, Integer> RoleIDCOL;

    @FXML
    private TableColumn<Employee, String> JobTitleCOL;

    @FXML
    private TableColumn<Employee, String> FullNameCOL;

    @FXML
    private TableColumn<Employee, String> EmailCOL;

    @FXML
    private TableColumn<Employee, String> PhoneCOL;

    @FXML
    private TextField statisticSearchBx;

    @FXML
    private Pane CreateCityPane;

    @FXML
    private Button CancelBTN;

    @FXML
    private Button SendRquestBTN1;

    @FXML
    private Label CityChangeSL1;

    @FXML
    private TextField CityIDT;

    @FXML
    private TextField CityDescriptionT;

    @FXML
    private TextField MapClusterVersionT;

    @FXML
    private TextField MapClusterPiceT;

    @FXML
    private TextField CityNameT;

    @FXML
    private Pane CreateMapPane;

    @FXML
    private TextField MapIDT;

    @FXML
    private TextField MapCityIDT;

    @FXML
    private TextField MapNameT;

    @FXML
    private TextField MapDescriptionT;

    @FXML
    private TextField MapVersionT;

    @FXML
    private TextField MapPathT;

    @FXML
    private Button CancelMapBTN;

    @FXML
    private Button SendRquestMapBTN;

    @FXML
    private Label MapChangeSL;

    @FXML
    private Label CityChangeSL;

    @FXML
    private TextField LocationCITYid;


    //TODO add class DailyStatistic

    @FXML
    private TableView<?> DailyStatisticTTV;

    @FXML
    private TableColumn<?, ?> statisticCityIDCOL;

    @FXML
    private TableColumn<?, ?> statisticCityNameCOL;

    @FXML
    private TableColumn<?, ?> statisticNumOfPurchasesCOL;

    @FXML
    private TableColumn<?, ?> statisticOTPurchaseCOL;

    @FXML
    private TableColumn<?, ?> statisticFPurchaseCOL;

    @FXML
    private TableColumn<?, ?> statisticSubscriptionRenewalCOL;

    @FXML
    private TableColumn<?, ?> statisticDateCOL;


    @FXML
    private TableView<ChangeRequest> RequestTV;

    @FXML
    public static TableView<ChangeRequest> RequestTTV1;

    @FXML
    private TableColumn<ChangeRequest, Integer> RequestIDCOL;

    @FXML
    private TableColumn<ChangeRequest, String> RequestDescriptionCOL;

    @FXML
    private TableColumn<ChangeRequest, String> EmployeeNameCOL;

    @FXML
    private TableColumn<ChangeRequest, String> RequestDateCOL;

    @FXML
    private TableColumn<ChangeRequest, Button> ActionCOLRequest;

    @FXML
    private TextField searchBoxTour;

    @FXML
    private TextField searchBoxLocation;


    @FXML
    void LogOutEmployee(ActionEvent event) {
        ChatClient.EraseDetails();
        String LogInScene = "/scences/LogInScene.fxml";
        try {
            ClientConsole.changeScene(LogInScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void PersonalInfoEmployee(ActionEvent event) {

        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(1));
        FullNameT.setText(employee.getFullName());
        JobTitleT.setText(employee.getJobTitle());
        EmailT.setText(employee.getEmail());
        PhoneT.setText(employee.getPhone());
        EmployeeIDT.setText(Integer.toString(employee.getEmployeeID()));
        PasswordT.setText(usr.getPassword());
    }

    @FXML
    void SearchCatalogEmployee(ActionEvent event) {
        boolean flag = false;
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(0));
        SearchTTV2.getItems().removeAll();
        SearchTTV2.getItems().clear();
    String fillCityTable = "0SELECT * FROM Cities ";
    flag = ConnectionController.client.handleMessageFromClientUI(fillCityTable);
}

    public void initialize(URL location, ResourceBundle resources) {
        AnchorPaneChildrens.addAll(ChangePanesAP.getChildren());
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(0));
        if (ChatClient.employee.getRoleID() != 0 && ChatClient.employee.getRoleID() != 1){
            WaitingForApprovalBTN.setVisible(false);
            TourLocationBTN.setVisible(false);
            StatisticsBTN.setVisible(false);
            EmployeesBTN1.setVisible(false);
            EmployeesImage.setVisible(false);
            StatisticsImage.setVisible(false);
            ApprovalImage.setVisible(false);
            TourLocationIMG.setVisible(false);
        }
        SearchTTV2 = SearchTTV;
        MyMapsTTV1 = MyMapsTTV;
        EmployeeTTV1 = EmployeeTTV;
        RequestTTV1 = RequestTV;
        // cols for cities
        IDCOL.setCellValueFactory(new PropertyValueFactory<>("cityID"));
        DescriptionCOL.setCellValueFactory(new PropertyValueFactory<>("description"));
        VersionCOL.setCellValueFactory(new PropertyValueFactory<>("mapClusterVersion"));
        NoMapsCOL.setCellValueFactory(new PropertyValueFactory<>("numberMaps"));
        ToursCOL.setCellValueFactory(new PropertyValueFactory<>("numberTours"));
        LocationsCOL.setCellValueFactory(new PropertyValueFactory<>("numberLocations"));
        PriceCOL.setCellValueFactory(new PropertyValueFactory<>("mapClusterPrice"));
        NameCOL.setCellValueFactory(new PropertyValueFactory<>("cityName"));
        ActionCOL.setCellValueFactory(new PropertyValueFactory<>("download"));

        boolean flag = false;
        String fillCityTable = "0SELECT * FROM Cities ";
        flag = ConnectionController.client.handleMessageFromClientUI(fillCityTable);

        // cols for maps
        IDCOLMap.setCellValueFactory(new PropertyValueFactory<>("mapID"));
        descriptionnCOLMap.setCellValueFactory(new PropertyValueFactory<>("description"));
        nameCOLMap.setCellValueFactory(new PropertyValueFactory<>("mapName"));
        VCOLMap.setCellValueFactory(new PropertyValueFactory<>("version"));
        ActionCOLMap.setCellValueFactory(new PropertyValueFactory<>("show"));

        // cols for employees  UserIDCOL EmployeeIDCOL RoleIDCOL JobTitleCOL FullNameCOL EmailCOL PhoneCOL
        UserIDCOL.setCellValueFactory(new PropertyValueFactory<>("userID"));
        EmployeeIDCOL.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        RoleIDCOL.setCellValueFactory(new PropertyValueFactory<>("roleID"));
        JobTitleCOL.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        FullNameCOL.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        EmailCOL.setCellValueFactory(new PropertyValueFactory<>("email"));
        PhoneCOL.setCellValueFactory(new PropertyValueFactory<>("phone"));

        // cols for request
        RequestIDCOL.setCellValueFactory(new PropertyValueFactory<>("requestID"));
        RequestDescriptionCOL.setCellValueFactory(new PropertyValueFactory<>("requestDescription"));
        EmployeeNameCOL.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        RequestDateCOL.setCellValueFactory(new PropertyValueFactory<>("requestDate"));
        ActionCOLRequest.setCellValueFactory(new PropertyValueFactory<>("show"));

        //for tour-location
        mapName = mapNameS;
        MapNameT.setText(mapName);
        mapID = MapIDtoGetLocsTours;
        LocationTV1 = LocationTV;
        ToursTV1 = ToursTV;

        // cols for Locations
        LocationIDCOL1.setCellValueFactory(new PropertyValueFactory<>("locationID"));
        LocationNameCOL1.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        ClassificationCOL1.setCellValueFactory(new PropertyValueFactory<>("locationClassification"));
        LocationDescriptionCOL1.setCellValueFactory(new PropertyValueFactory<>("description"));
        AccessibilityCOL1.setCellValueFactory(new PropertyValueFactory<>("accessibility"));
        ActionLocationCOL.setCellValueFactory(new PropertyValueFactory<>("show"));
        // cols for Tours
        ToursIDCOL1.setCellValueFactory(new PropertyValueFactory<>("tourID"));
        TourDescriptionCOL.setCellValueFactory(new PropertyValueFactory<>("description"));
        TourDurationCOL1.setCellValueFactory(new PropertyValueFactory<>("visitDuration"));
        LocationsIDCOL1.setCellValueFactory(new PropertyValueFactory<>("LocationsID"));
        ActionTourCOL.setCellValueFactory(new PropertyValueFactory<>("show"));


        /////////////////
        // editable COLS
        // city
        SearchTTV.setEditable(true);   // SearchTTV2.setEditable(true);
        IDCOL.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        IDCOL.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setCityID(e.getNewValue()));
        DescriptionCOL.setCellFactory(TextFieldTableCell.forTableColumn());
        DescriptionCOL.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setDescription(e.getNewValue()));
        VersionCOL.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        VersionCOL.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setMapClusterVersion(e.getNewValue()));
        NoMapsCOL.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        NoMapsCOL.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setNumberMaps(e.getNewValue()));
        ToursCOL.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ToursCOL.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setNumberTours(e.getNewValue()));
        LocationsCOL.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        LocationsCOL.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setNumberLocations(e.getNewValue()));
        PriceCOL.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        PriceCOL.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setMapClusterPrice(e.getNewValue()));
        NameCOL.setCellFactory(TextFieldTableCell.forTableColumn());
        NameCOL.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setCityName(e.getNewValue()));
    }

    @FXML
    void searchRecord(KeyEvent event) {
        FilteredList<City2> filterData = new FilteredList<>(catalogDataS2, p -> true);
        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(city -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (city.getCityName().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (city.getDescription().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                return false;
            });
            SortedList<City2> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(SearchTTV2.comparatorProperty());
            SearchTTV2.setItems(sortedList);
        });
    }
    @FXML
    void searchRecordMap(KeyEvent event) {
        FilteredList<Map> filterData = new FilteredList<>(myMapsDataS, p -> true);
        searchBox1.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(map -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (map.getMapName().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (map.getDescription().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                return false;
            });
            SortedList<Map> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(MyMapsTTV1.comparatorProperty());
            MyMapsTTV1.setItems(sortedList);
        });
    }

    @FXML
    public static void addTextFilter(ObservableList<City> allData,
                                     TextField filterField, TableView<City> table) {

        final List<TableColumn<City, ?>> columns = table.getColumns();

        FilteredList<City> filteredData = new FilteredList<>(allData);
        filteredData.predicateProperty().bind(Bindings.createObjectBinding(() -> {
            String text = filterField.getText();

            if (text == null || text.isEmpty()) {
                return null;
            }
            final String filterText = text.toLowerCase();

            return o -> {
                for (Object value : columns) {
                    if (value != null && value.toString().toLowerCase().contains(filterText)) {
                        return true;
                    }
                }
                return false;
            };
        }, filterField.textProperty()));

        SortedList<City> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

    @FXML
    void showmaps(ActionEvent event) {
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(2));
        MyMapsTTV.getItems().removeAll();

        boolean flag = false;
        String fillAllMaps = "]SELECT mapID,mapName,description,version FROM Maps"; // mapID mapName description version
        flag = ConnectionController.client.handleMessageFromClientUI(fillAllMaps);

    }

    @FXML
    void ApprovalRequests(ActionEvent event) {
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(5));
        RequestTV.getItems().removeAll();

        boolean flag = false;
        String fillAllRequest = ">SELECT * FROM ChangeRequest"; // mapID mapName description version
        flag = ConnectionController.client.handleMessageFromClientUI(fillAllRequest);


    }

    @FXML
    void EmployeesDetails(ActionEvent event) {
        boolean flag;
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(3));
        EmployeeTTV1.getItems().removeAll();
        EmployeeTTV1.getItems().clear();
        String fillEmployeesTable = "ASELECT * FROM Employees ";
        flag = ConnectionController.client.handleMessageFromClientUI(fillEmployeesTable);
    }

    @FXML
    void Statistics(ActionEvent event) {
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(4));

    }

    @FXML
    void CreateCityFunc(ActionEvent event) {
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(6));
    }

    @FXML
    void CreateMapFunc(ActionEvent event) {
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(7));
    }

    @FXML
    void CancelChange(ActionEvent event) {
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(2));
    }

    @FXML
    void ChangeAction(ActionEvent event) {
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(3));
    }

    @FXML
    void sendRequestCityBTN(ActionEvent event) {

        String cityID = CityIDT.getText();
        String cityDescription = CityDescriptionT.getText();
        String mapClusterVersion = MapClusterVersionT.getText();
        int numMaps = 0;
        int numTours = 0;
        int numLocation = 0;
        String mapClusterPrice = MapClusterPiceT.getText();
        String cityName = CityNameT.getText();


        CityChangeSL.setText("Request sent for approval");
        CityChangeSL.setTextFill(Color.BLUE);

        String requestIDQuery = "3SELECT MAX(requestID) FROM ChangeRequest";
        boolean flag = ConnectionController.client.handleMessageFromClientUI(requestIDQuery);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        maxReqID = ChatClient.maxRequestID;

        Date currentdate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        ChangeRequest request = new ChangeRequest(maxReqID,"Creating new city: " + cityName,ChatClient.employee.getFullName(),formatter.format(currentdate) );

        String addRequest = "rINSERT INTO ChangeRequest (requestID, requestDescription, employeeName,requestDate ) " +
                "VALUES (" + request.getRequestID() + ",'" +  request.getRequestDescription() + "','" + request.getEmployeeName() + "','" + request.getRequestDate()+ "')";
        flag = ConnectionController.client.handleMessageFromClientUI(addRequest);

        String addCityRequest = "iINSERT INTO CitiesRequest (cityID, requestID, description, mapsClusterVersion, numMaps, numTours, numLocations, mapsClusterPrice, cityName) " +
                "VALUES (" + Integer.parseInt(cityID) + "," +maxReqID+ ",'" +
                cityDescription + "'," + Double.parseDouble(mapClusterVersion) + "," +
                numMaps + "," + numTours + "," + numLocation + "," + Double.parseDouble(mapClusterPrice)+ ",'" + cityName + "')";

        flag = ConnectionController.client.handleMessageFromClientUI(addCityRequest);

    }

    @FXML
    void sendRequestLocationBTN(ActionEvent event) {
        String locationID = LocationIDT.getText();
        String locationNameT = LocationNameT.getText();
        String classificationT = ClassificationT.getText();
        String locationDescriptionT = LocationDescriptionT.getText();
        String locationAccessabilityT = LocationAccessabilityT.getText();
        String cityIDLocation = LocationCITYid.getText();

        LocationChangeSL.setText("Request sent for approval");
        LocationChangeSL.setTextFill(Color.BLUE);


        String requestIDQuery = "3SELECT MAX(requestID) FROM ChangeRequest";
        boolean flag = ConnectionController.client.handleMessageFromClientUI(requestIDQuery);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        maxReqID = ChatClient.maxRequestID;

        Date currentdate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        ChangeRequest request = new ChangeRequest(maxReqID,"Creating new Location: " + mapName,ChatClient.employee.getFullName(),formatter.format(currentdate) );

        String addRequest = "rINSERT INTO ChangeRequest (requestID, requestDescription, employeeName,requestDate ) " +
                "VALUES (" + request.getRequestID() + ",'" +  request.getRequestDescription() + "','" + request.getEmployeeName() + "','" + request.getRequestDate()+ "')";
        flag = ConnectionController.client.handleMessageFromClientUI(addRequest);

        String addLocRequest = "iINSERT INTO LocationsRequest (locationID, requestID, locationName, classification, description, accessibility, cityID)" +
                "VALUES (" + Integer.parseInt(locationID) + "," + maxReqID + ",'" + locationNameT + "','" + classificationT + "','" +
                locationDescriptionT + "'," + Boolean.parseBoolean(locationAccessabilityT)+ "," + Integer.parseInt(cityIDLocation) + ")";

        flag = ConnectionController.client.handleMessageFromClientUI(addLocRequest);
    }

    @FXML
    void sendRequestMapBTN(ActionEvent event) {

        String mapID = MapIDT.getText();
        String mapCityID = MapCityIDT.getText();
        String mapName = MapNameT.getText();
        String mapDescription = MapDescriptionT.getText();
        double version = Double.parseDouble(MapVersionT.getText());
        String mapPath = MapPathT.getText();

        MapChangeSL.setText("Request sent for approval");
        MapChangeSL.setTextFill(Color.BLUE);


        String requestIDQuery = "3SELECT MAX(requestID) FROM ChangeRequest";
        boolean flag = ConnectionController.client.handleMessageFromClientUI(requestIDQuery);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        maxReqID = ChatClient.maxRequestID;

        Date currentdate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        ChangeRequest request = new ChangeRequest(maxReqID,"Creating new Map: " + mapName,ChatClient.employee.getFullName(),formatter.format(currentdate) );

        String addRequest = "rINSERT INTO ChangeRequest (requestID, requestDescription, employeeName,requestDate ) " +
                "VALUES (" + request.getRequestID() + ",'" +  request.getRequestDescription() + "','" + request.getEmployeeName() + "','" + request.getRequestDate()+ "')";
        flag = ConnectionController.client.handleMessageFromClientUI(addRequest);

        String addMapRequest = "gINSERT INTO MapsRequest (mapID, requestID, cityID, mapName, description, version, mapPath)" +
                "VALUES (" + Integer.parseInt(mapID) + "," + maxReqID + "," + Integer.parseInt(mapCityID) + ",'" +
                mapName + "','" + mapDescription + "'," + version + ",'" + mapPath + "')";

        flag = ConnectionController.client.handleMessageFromClientUI(addMapRequest);
    }

    @FXML
    void  sendRequestTourBTN(ActionEvent event) {
        String tourID = TourIDT.getText();
        String tourDescription = TourDescriptionT.getText();
        String visitDuration = VisitDurationT.getText();
        String cityIDtour = CityIDTourt.getText();

        TourChangeSL.setText("Request sent for approval");
        TourChangeSL.setTextFill(Color.BLUE);


        String requestIDQuery = "3SELECT MAX(requestID) FROM ChangeRequest";
        boolean flag = ConnectionController.client.handleMessageFromClientUI(requestIDQuery);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        maxReqID = ChatClient.maxRequestID;

        Date currentdate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        ChangeRequest request = new ChangeRequest(maxReqID,"Creating new Tour: " + mapName,ChatClient.employee.getFullName(),formatter.format(currentdate) );

        String addRequest = "rINSERT INTO ChangeRequest (requestID, requestDescription, employeeName,requestDate ) " +
                "VALUES (" + request.getRequestID() + ",'" +  request.getRequestDescription() + "','" + request.getEmployeeName() + "','" + request.getRequestDate()+ "')";
        flag = ConnectionController.client.handleMessageFromClientUI(addRequest);

        String addLocRequest = "iINSERT INTO ToursRequest (tourID, requestID, description, visitDuration, cityID)" +
                "VALUES (" + Integer.parseInt(tourID) + "," + maxReqID + ",'" + tourDescription + "','" + visitDuration + "'," +
                Integer.parseInt(cityIDtour)+ ")";

        flag = ConnectionController.client.handleMessageFromClientUI(addLocRequest);
    }

    @FXML
    void showTourLocation(ActionEvent event) {
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(10));

        String fillLocationsTable = "7SELECT * FROM Locations";
        boolean flag = ConnectionController.client.handleMessageFromClientUI(fillLocationsTable);

        String fillToursTable = "8SELECT * FROM Tours ";
        flag = ConnectionController.client.handleMessageFromClientUI(fillToursTable);

    }

    @FXML
    private void searchRecordLocation(KeyEvent ke) {
        FilteredList<Location> filterData = new FilteredList<>(locationsDataS, p -> true);
        searchBoxLocation.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(location -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (location.getLocationName().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (location.getDescription().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (location.getLocationClassification().toLowerCase().indexOf(typedText) != -1)
                {
                    return true;
                }
                return false;
            });
            SortedList<Location> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(LocationTV1.comparatorProperty());
            LocationTV1.setItems(sortedList);
        });
    }

    @FXML
    private void searchRecordTour(KeyEvent ke) {
        FilteredList<Tour> filterData = new FilteredList<>(tourDataS, p -> true);
        searchBoxTour.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(tour -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (tour.getDescription().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }
                return false;
            });
            SortedList<Tour> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(ToursTV1.comparatorProperty());
            ToursTV1.setItems(sortedList);
        });
    }

    @FXML
    void AddLocationpane(ActionEvent event) {
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(8));
    }

    @FXML
    void AddTourpane(ActionEvent event) {
        ChangePanesAP.getChildren().clear();
        ChangePanesAP.getChildren().add(AnchorPaneChildrens.get(9));
    }



}