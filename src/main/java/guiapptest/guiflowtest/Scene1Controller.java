package guiapptest.guiflowtest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.dialog.FontSelectorDialog;

import java.io.*;
import java.util.StringTokenizer;

public class Scene1Controller {
    @FXML
    private TextArea textArea;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Label charLabel;

    @FXML
    private Label wordCount;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    public void fileWrite() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(textArea.getScene().getWindow());
        if (file != null) {
            try {
                BufferedWriter bf = new BufferedWriter(new FileWriter(file));
                bf.write(textArea.getText());
                bf.flush();
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(textArea.getScene().getWindow());
        if (file != null) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                String everything = sb.toString();
                textArea.setText(everything);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                br.close();
            }
        }
    }

    public void getLineAndWordCount(){
        charLabel.setText("Character: " + textArea.getCaretPosition());
        StringTokenizer words = new StringTokenizer(textArea.getText());
        wordCount.setText("Word count: " + words.countTokens());
    }

    public void setTextColor(){
        textArea.setStyle("-fx-text-fill:" + toRGBCode(colorPicker.getValue()) +";");
    }

    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public void setFont() {
        FontSelectorDialog fontDialog = new FontSelectorDialog(textArea.getFont());
        fontDialog.showAndWait().ifPresent(font -> {
            textArea.setFont(font);
            textArea.setStyle("-fx-font-family: '" + font.getFamily() + "';");
            textArea.setStyle("-fx-font-size: " + font.getSize() + "px;");
        });
    }

    public void openAbout() throws IOException {
        Stage aboutWindow = new Stage();
        aboutWindow.initStyle(StageStyle.UTILITY);
        aboutWindow.setResizable(false);
        aboutWindow.setTitle("About MiniNotepad");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("aboutscene.fxml"));
        aboutWindow.setScene(new Scene(loader.load()));
        aboutWindow.show();
    }

    public void changeWordWrap(){
        textArea.setWrapText(!textArea.isWrapText());
    }
}