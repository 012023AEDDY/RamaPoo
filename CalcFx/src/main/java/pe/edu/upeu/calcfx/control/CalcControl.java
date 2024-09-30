package pe.edu.upeu.calcfx.control;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.calcfx.modelo.calcTO;
import pe.edu.upeu.calcfx.servicio.CalcServiceImp;
import pe.edu.upeu.calcfx.servicio.calcServivio;

import java.util.List;


@Component
public class CalcControl {
    @Autowired
    calcServivio serviceI;

    @FXML
    TextField txtResultado;

    @FXML
    TableView tableView;

    @FXML
    TableColumn <calcTO, String>cVal1, cVal2, cResul;
    @FXML
    TableColumn<calcTO, Character>cOper;
    @FXML
    TableColumn<calcTO, Void>cOpc;
    private ObservableList<calcTO> calcTOList;
    private int indexEdit=-1;
    @FXML
    public void initialize(){
        listaOper();
    }
    int t=0;

    @FXML
    public void accionButton(ActionEvent event){
        System.out.println("Holas");
        Button button= (Button)event.getSource();
        switch (button.getId()){
            case "btn7","btn8","btn9","btn6","btn5","btn4","btn3","btn2","btn1","btn0":{
                escribirNumeros(button.getText());
            }break;
            case "btnSum", "btnMul", "btnRest", "btnDiv":{
                operador(button.getText());
            }break;
            case "btnIgual":{
                calcularResultado();
            }break;
            case "btnBorrar":{
                txtResultado.clear();
            }

        }

    }

    public void escribirNumeros(String valor){
        txtResultado.appendText(valor);
    }

    public void operador(String valor){
        txtResultado.appendText(" "+valor+" ");
    }

    public  void calcularResultado(){
        String[] valores=txtResultado.getText().split(" ");
        double val1=Double.parseDouble(String.valueOf(valores[0]));
        double val2=Double.parseDouble(String.valueOf(valores[2]));
        switch (valores[1]){
            case "+":{txtResultado.setText(String.valueOf(val1+val2));}break;
            case "-":{txtResultado.setText(String.valueOf(val1-val2));}break;
            case "/":{txtResultado.setText(String.valueOf(val1/val2));}break;
            case "*":{txtResultado.setText(String.valueOf(val1*val2));}break;
        }

        calcTO to=new calcTO();
        to.setNum1(String.valueOf(val1));
        to.setNum2(String.valueOf(val2));
        to.setOperador(valores[1].charAt(0));
        to.setId(indexEdit);

        to.setResultado(String.valueOf(txtResultado.getText()));
        if(indexEdit!=-1){
            serviceI.actualizarResultado(to, to.getId());
        }else{
            serviceI.guardarResultados(to);
        }
        indexEdit=-1;
        listaOper();

    }
    // Acción para editar una operación CRUD
    private void editOperCalc(calcTO cal, int index) {
        System.out.println("Editing: " + cal.getNum1() + " Index:"+index);
        txtResultado.setText(cal.getNum1()+" "+cal.getOperador()+" "+cal.getNum2());
                indexEdit=index;
    }
    private void deleteOperCalc(calcTO cal, int index) {//elimina y refresq ue el reporte
        System.out.println("Deleting: " + cal.getNum2());
        serviceI.eliminarResultado(cal.getId());
        listaOper();
        //tableView.getItems().remove(cal); // Elimina la operación delTableView
    }
    private void addActionButtonsToTable() {
        Callback<TableColumn<calcTO, Void>, TableCell<calcTO, Void>>
                cellFactory = param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            {
                editButton.getStyleClass().setAll("btn", "btn-success");//color de boton
                editButton.setOnAction(event -> {
                    calcTO cal = getTableView().getItems().get(getIndex());
                    editOperCalc(cal, cal.getId());
                });
                deleteButton.getStyleClass().setAll("btn", "btn-danger");//color de boton
                deleteButton.setOnAction(event -> {
                    calcTO cal = getTableView().getItems().get(getIndex());
                    deleteOperCalc(cal, cal.getId());
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(editButton, deleteButton);
                    buttons.setSpacing(10);
                    setGraphic(buttons);
                }
            }
        };
        cOpc.setCellFactory(cellFactory);//SE COLOCA LOS DOS BOTONES
    }
    public void listaOper(){
        List<calcTO> lista=serviceI.obtenerResultado();
        for (calcTO to:lista) {
            System.out.println(to.toString());
        }

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Vincular columnas con propiedades de CalcTO
        cVal1.setCellValueFactory(new PropertyValueFactory<calcTO,
                        String>("num1"));

        cVal1.setCellFactory(TextFieldTableCell.<calcTO>forTableColumn());
        cVal2.setCellValueFactory(new PropertyValueFactory<calcTO,
                String>("num2"));

        cVal2.setCellFactory(TextFieldTableCell.<calcTO>forTableColumn());
        cOper.setCellValueFactory(new
                PropertyValueFactory<>("operador"));
        cOper.setCellFactory(ComboBoxTableCell.<calcTO,// editar en la misma tabla en las 4 opciones xd
                Character>forTableColumn('+', '-', '/', '*'));
        cResul.setCellValueFactory(new PropertyValueFactory<calcTO,
                String>("resultado"));

        cResul.setCellFactory(TextFieldTableCell.<calcTO>forTableColumn());
        // Agregar botones de eliminar y modificar
        addActionButtonsToTable();
        calcTOList = FXCollections.observableArrayList(serviceI.obtenerResultado());//esto muestra la lista
        // Asignar los datos al TableView
        AnchorPane.setLeftAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);

        cOper.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25)); // 25% del ancho total

        cResul.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25)); // 25% del ancho total

        cOpc.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        tableView.setItems(calcTOList);//y al final con esta fila agregamos
    }
}
