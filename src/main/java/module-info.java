module fr.btn.sdbm {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.btn.sdbm to javafx.fxml;
    exports fr.btn.sdbm;
}