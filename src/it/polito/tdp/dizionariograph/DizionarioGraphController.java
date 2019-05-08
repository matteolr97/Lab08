package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {

	private Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtLettere;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnGrafo;

    @FXML
    private Button btnRicercaVicini;

    @FXML
    private Button btnGrado;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doClear(ActionEvent event) {

    	txtResult.clear();
    	txtParola.clear();
    	txtLettere.clear();
    }

    @FXML
    void doFindGrado(ActionEvent event) {

    	

    	txtResult.appendText(model.findMaxDegree());
      	txtResult.appendText("\n");
    }

    @FXML
    void doFindVicini(ActionEvent event) {

    	String parolaInserita = txtParola.getText();
    	txtResult.appendText(model.displayNeighbours(parolaInserita).toString());
    	txtResult.appendText("\n");
    }

    @FXML
    void doGrafo(ActionEvent event) {
    	txtResult.clear();
		txtParola.clear();
		int numeroLettere = Integer.parseInt(txtLettere.getText());
		List<String> grafo = model.createGraph(numeroLettere);
		txtResult.appendText(grafo.toString());
	  	txtResult.appendText("\n");
    }

    @FXML
    void initialize() {
        assert txtLettere != null : "fx:id=\"txtLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGrafo != null : "fx:id=\"btnGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnRicercaVicini != null : "fx:id=\"btnRicercaVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGrado != null : "fx:id=\"btnGrado\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }


	public void setModel(Model model) {
		this.model= model;
	}
}
