module guiapptest.guiflowtest {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens guiapptest.guiflowtest to javafx.fxml;
    exports guiapptest.guiflowtest;
}