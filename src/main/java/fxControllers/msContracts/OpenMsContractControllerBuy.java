package fxControllers.msContracts;

import crud.controller.controllers.DaoContractsOpenBuyController;
import crud.controller.controllers.DaoMaterialController;
import crud.model.GenericDaoImpl;
import entity.ContractsOpenBuy;
import entity.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;


public class OpenMsContractControllerBuy {

    @FXML
    private Button refreshButton;

    @FXML
    private TableView<ContractsOpenBuy> openMsContractTable;

    public TableView<ContractsOpenBuy> getOpenMsContactTable() {
        return openMsContractTable;
    }

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnCustomerName;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnIdName;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnNrTruckContract;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnNrTruck;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnAmount;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnContractName;


    private ObservableList<ContractsOpenBuy> data;

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private final EntityManager entityManager = sessionFactory.createEntityManager();
    private GenericDaoImpl genericDao = new GenericDaoImpl(entityManager, ContractsOpenBuy.class);
    private DaoContractsOpenBuyController daoContractsOpenBuyController = DaoContractsOpenBuyController
            .builder()
            .dao(genericDao)
            .build();

    private final EntityManager entityManagerMaterial = sessionFactory.createEntityManager();
    private GenericDaoImpl genericDaoMaterial = new GenericDaoImpl(entityManagerMaterial, Material.class);
    private DaoMaterialController daoAllViewControllerMaterial =  DaoMaterialController.builder()
            .dao(genericDaoMaterial)
            .build();

    public void initialize() {
        data = FXCollections.observableArrayList();

        List<ContractsOpenBuy> contractsOpenBuys = daoContractsOpenBuyController
                .selectList()
                .stream()
                .filter(e->e.getIdName()
                        .equals(daoAllViewControllerMaterial.findById(3).getName()))
                .collect(Collectors.toList());

        data.setAll(contractsOpenBuys);

        columnCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        columnIdName.setCellValueFactory(new PropertyValueFactory<>("idName"));
        columnNrTruck.setCellValueFactory(new PropertyValueFactory<>("nrTruck"));
        columnNrTruckContract.setCellValueFactory(new PropertyValueFactory<>("nrTruckContract"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnContractName.setCellValueFactory(new PropertyValueFactory<>("contractName"));
        openMsContractTable.setItems(null);
        openMsContractTable.setItems(data);

    }




}
