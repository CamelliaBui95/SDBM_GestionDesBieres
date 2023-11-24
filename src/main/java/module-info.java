module fr.btn.sdbm {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;
    requires org.controlsfx.controls;

    opens fr.btn.sdbm to javafx.fxml;
    exports fr.btn.sdbm;
}