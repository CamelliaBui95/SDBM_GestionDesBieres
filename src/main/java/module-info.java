module fr.btn.sdbm {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;
    requires org.controlsfx.controls;
    //requires com.sun.javafx.charts;

    opens fr.btn.sdbm to javafx.fxml;
    exports fr.btn.sdbm;
}