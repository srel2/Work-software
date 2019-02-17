package fxControllers.closeContracts;

import crud.controller.DaoContractsCloseController;
import crud.controller.DaoMaterialController;
import crud.model.GenericDaoImpl;
import entity.ContractsClose;
import entity.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.SessionFactory;
import utils.HibernateUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;


public class CloseContractController {

    @FXML
    private Button refreshButton;

    @FXML
    private TableView<ContractsClose> closeContractTable;


    @FXML
    private TableColumn<ContractsClose, String> columnCustomerName;

    @FXML
    private TableColumn<ContractsClose, String> columnMaterialName;

    @FXML
    private TableColumn<ContractsClose, String> columnNrTruckContract;

    @FXML
    private TableColumn<ContractsClose, String> columnNrTruck;

    @FXML
    private TableColumn<ContractsClose, String> columnAmount;

    @FXML
    private TableColumn<ContractsClose, String> columnContractName;

    @FXML
    private ChoiceBox choiceMaterialContract;



    private ObservableList<ContractsClose> data;

    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private final EntityManager entityManager = sessionFactory.createEntityManager();
    private GenericDaoImpl genericDao = new GenericDaoImpl(entityManager, ContractsClose.class);
    private DaoContractsCloseController daoContractsCloseController = DaoContractsCloseController
            .builder()
            .dao(genericDao)
            .build();


    private final EntityManager entityManagerMaterial = sessionFactory.createEntityManager();
    private GenericDaoImpl genericDaoMaterial = new GenericDaoImpl(entityManagerMaterial, Material.class);
    private DaoMaterialController daoMaterialController =  DaoMaterialController
            .builder()
            .dao(genericDaoMaterial)
            .build();

    List<ContractsClose> contractsClose;

    public void initialize() {
        contractsClose = daoContractsCloseController.selectList();
     selectMaterial(0);

    }

    public void selectMaterial(int id){
        List<ContractsClose> collect;

        if(id == 1){
          collect = contractsClose
                  .stream()
                  .filter(e -> e.getMaterialName().equals(daoMaterialController.findById(3).getName()))
                  .collect(Collectors.toList());
        }else {
          collect = contractsClose;
        }

        data = FXCollections.observableArrayList();


        data.setAll(collect);
        columnCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        columnMaterialName.setCellValueFactory(new PropertyValueFactory<>("MaterialName"));
        columnNrTruck.setCellValueFactory(new PropertyValueFactory<>("nrTruck"));
        columnNrTruckContract.setCellValueFactory(new PropertyValueFactory<>("nrTruckContract"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnContractName.setCellValueFactory(new PropertyValueFactory<>("contractName"));
        closeContractTable.setItems(null);
        closeContractTable.setItems(data);
    }




}