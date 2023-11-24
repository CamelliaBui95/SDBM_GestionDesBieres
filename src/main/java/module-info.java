module fr.btn.sdbm {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;

    opens fr.btn.sdbm to javafx.fxml;
    exports fr.btn.sdbm;
}