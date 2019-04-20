package fxControllers.msContracts;

import crud.services.ContractsOpenService;
import crud.services.MaterialService;
import crud.model.ContractsOpenBuy;
import crud.model.ContractsOpenSell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
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
    private TableColumn<ContractsOpenBuy, String> columnMaterialName;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnNrTruckContract;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnNrTruck;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnAmount;

    @FXML
    private TableColumn<ContractsOpenBuy, String> columnContractName;


    private ObservableList<ContractsOpenBuy> data;
    private ContractsOpenService<ContractsOpenBuy> contractsOpenService;
    private MaterialService materialService;

    public OpenMsContractControllerBuy(ContractsOpenService<ContractsOpenBuy> contractsOpenService, MaterialService materialService) {
        this.contractsOpenService = contractsOpenService;
        this.materialService = materialService;
    }

    public void initialize() {
        data = FXCollections.observableArrayList();

        List<ContractsOpenBuy> contractsOpenBuys = contractsOpenService
                .selectList()
                .stream()
                .filter(e -> e.getMaterialName()
                        .equals(materialService.findById(3L).getName()))
                .collect(Collectors.toList());

        data.setAll(contractsOpenBuys);

        columnCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        columnMaterialName.setCellValueFactory(new PropertyValueFactory<>("MaterialName"));
        columnNrTruck.setCellValueFactory(new PropertyValueFactory<>("nrTruck"));
        columnNrTruckContract.setCellValueFactory(new PropertyValueFactory<>("nrTruckContract"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnContractName.setCellValueFactory(new PropertyValueFactory<>("contractName"));
        openMsContractTable.setItems(null);
        openMsContractTable.setItems(data);

    }

    public void doChange(TableColumn.CellEditEvent<ContractsOpenSell, String> tableStringCellEditEvent) {
        columnNrTruck.getTypeSelector();
    }


}
