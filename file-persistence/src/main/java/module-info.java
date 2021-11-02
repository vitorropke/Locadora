module br.edu.ufersa.ropke.locadoramaven {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;

	opens br.edu.ufersa.ropke.locadoramaven.controller to javafx.fxml;
	opens br.edu.ufersa.ropke.locadoramaven.model.VO to javafx.base;
	opens br.edu.ufersa.ropke.locadoramaven.model.DAO to javafx.base;
	opens br.edu.ufersa.ropke.locadoramaven.model.BO to javafx.base;

	exports br.edu.ufersa.ropke.locadoramaven;
}
