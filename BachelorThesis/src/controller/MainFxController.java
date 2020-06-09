package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DBConnection;
import model.MarcasData;
import model.Search;

public class MainFxController implements Initializable 
{
	@FXML
	private TableView<MarcasData> marcasTable;

	@FXML
	private TableColumn<MarcasData, String> marcasColumn;

	@FXML
	private TextField marcaSearch, specialSearch, preSearch, postSearch;
	
	@FXML
	private Button searchBtn;

	private ObservableList<MarcasData> data;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Executors.newSingleThreadExecutor().execute(new Runnable() {
		    @Override
		    public void run() {
		    	loadDatabaseIntoTable();
		    }
		});
		
	}
	


	private void loadDatabaseIntoTable() {
		try {
			Connection conn = DBConnection.getConnection();
			this.data = FXCollections.observableArrayList();

			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM marcasofficial;");
			while (rs.next()) {
				this.data.add(new MarcasData(rs.getString(1)));
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Error" + e);
		}

		this.marcasColumn.setCellValueFactory(new PropertyValueFactory<MarcasData, String>("marcas"));

		if (data.isEmpty()) {
			loadDatabaseIntoTable();
		} else {
			this.marcasTable.setItems(null);
			this.marcasTable.setItems(this.data);
		}
	}

	@FXML
	private void searchFixHandler(ActionEvent event) {

		Executors.newSingleThreadExecutor().execute(new Runnable() {
		    @Override
		    public void run() {
		    	if (preSearch.getText().length() > 0 && postSearch.getText().length() > 0) {
		    		System.out.println("whohohoh");
					try {
						Connection conn = DBConnection.getConnection();
						data = FXCollections.observableArrayList();

						long ttMysql = new Date().getTime();
						ResultSet rs = conn.createStatement().executeQuery(
								"SELECT * FROM marcasofficial WHERE marcasofficialnames REGEXP '^" + preSearch.getText().toLowerCase() + "(.*?)" + postSearch.getText().toLowerCase() + "\\s*$'");
						while (rs.next()) {
							data.add(new MarcasData(rs.getString(1)));
						}
						System.out.println(" >>>> size is a " + (data.size()));
						rs.close();
						long ttMysqlDone = new Date().getTime();

						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					marcasColumn.setCellValueFactory(new PropertyValueFactory<MarcasData, String>("marcas"));
					marcasTable.setItems(null);
					marcasTable.setItems(data);
				} else if (preSearch.getText().length() > 0) {
		    		System.out.println("whohohoh");
					try {
						Connection conn = DBConnection.getConnection();
						data = FXCollections.observableArrayList();

						long ttMysql = new Date().getTime();
						ResultSet rs = conn.createStatement().executeQuery(
								"SELECT * FROM marcasofficial WHERE marcasofficialnames REGEXP '^\\s*" + preSearch.getText().toLowerCase() + "'");
						while (rs.next()) {
							data.add(new MarcasData(rs.getString(1)));
						}
						System.out.println(" >>>> size is a " + (data.size()));
						rs.close();
						long ttMysqlDone = new Date().getTime();

						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					marcasColumn.setCellValueFactory(new PropertyValueFactory<MarcasData, String>("marcas"));
					marcasTable.setItems(null);
					marcasTable.setItems(data);
		    	} else if (postSearch.getText().length() > 0) {
		    		System.out.println("whohohoh");
					try {
						Connection conn = DBConnection.getConnection();
						data = FXCollections.observableArrayList();

						long ttMysql = new Date().getTime();
						ResultSet rs = conn.createStatement().executeQuery(
								"SELECT * FROM marcasofficial WHERE marcasofficialnames REGEXP '" + postSearch.getText().toLowerCase() + "( )*$'");
						while (rs.next()) {
							data.add(new MarcasData(rs.getString(1)));
						}
						System.out.println(" >>>> size is a " + (data.size()));
						rs.close();
						long ttMysqlDone = new Date().getTime();

						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					marcasColumn.setCellValueFactory(new PropertyValueFactory<MarcasData, String>("marcas"));
					marcasTable.setItems(null);
					marcasTable.setItems(data);
		    		System.out.println("wheheheh");
		    	} else {
		    		System.out.println("nothing writen u nut");
		    	}
		    }
		});
	}

	@FXML
	private void searchMarcasHandler(ActionEvent event) {

		Executors.newSingleThreadExecutor().execute(new Runnable() {
		    @Override
		    public void run() {
		    	if (marcaSearch.getText().length() > 0 && specialSearch.getText().length() > 0) {
					System.out.println("YOU WROTE SOMETHING IN BOTH YOU MOFO, SO THROW AN ALERT OR SOMETHING");
				}
				if (marcaSearch.getText().length() > 0) {
					// IF YOU'VE WRITTEN SOMETING ON THE 1ST SEARCH IT SEARCHES HERE, ELSE IT WILL
					// DO THE SPECIAL SEARCH AND YOU HAVE TO IMPLEMENT IT AT THE "ELSE".
					System.out.println("You suck search");
					try {
						Connection conn = DBConnection.getConnection();
						data = FXCollections.observableArrayList();

						/*ResultSet rs = conn.createStatement().executeQuery(
								"SELECT * FROM marcasofficial WHERE marcasofficialnames LIKE '%" + marcaSearch.getText() + "%'");*/
						long ttMysql = new Date().getTime();
						ResultSet rs = conn.createStatement().executeQuery(
								"SELECT * FROM marcasofficial WHERE marcasofficialnames REGEXP '" + Search.replace2(marcaSearch.getText()) + "' OR marcasofficialnames REGEXP '" + Search.choppedToThrees(marcaSearch.getText()) + "'");
						while (rs.next()) {
							data.add(new MarcasData(rs.getString(1)));
						}
						System.out.println(" >>>> size is a " + (data.size()));
						rs.close();
						long ttMysqlDone = new Date().getTime();
						System.out.println(" >>>> Mysql regex stuff took " + (ttMysqlDone-ttMysql) + " ms");
						
						
						/* 
						 *     THIS BLOCK IS FOR SEARCHIN IN-MEMORY INSTEAD OF IN THE SQL DB. IT SUCKS AND IS SLOW
						 * 
						 * long ttStream = new Date().getTime();
						String reg2 = Search.replace2(marcaSearch.getText());
						rs = conn.createStatement().executeQuery("SELECT * FROM marcasofficial");
						data = FXCollections.observableArrayList();
						long ttStreamFilter = new Date().getTime();
						while (rs.next()) {
							data.add(new MarcasData(rs.getString(1)));
						}
						data = data.stream().filter((MarcasData m) -> {
							return m.getMarcas().matches(reg2);
						}).collect(Collectors.toCollection(FXCollections::observableArrayList));
						long ttStreamDone = new Date().getTime();
						System.out.println(" >>>> Stream total stuff took " + (ttStreamDone-ttStream) + " ms");
						System.out.println(" >>>> Stream search stuff took " + (ttStreamDone-ttStreamFilter) + " ms");
						rs.close(); */
						
					} catch (SQLException e) {
						e.printStackTrace();
					}

					marcasColumn.setCellValueFactory(new PropertyValueFactory<MarcasData, String>("marcas"));
					marcasTable.setItems(null);
					marcasTable.setItems(data);
				} else if (specialSearch.getText().length() > 0) 
				{

					// IF YOU'VE WRITTEN SOMETING ON THE 1ST SEARCH IT SEARCHES HERE, ELSE IT WILL
					// DO THE SPECIAL SEARCH AND YOU HAVE TO IMPLEMENT IT AT THE "ELSE".
					System.out.println("Youre special search");
					try {
						Connection conn = DBConnection.getConnection();
						data = FXCollections.observableArrayList();

						/*ResultSet rs = conn.createStatement().executeQuery(
								"SELECT * FROM marcasofficial WHERE marcasofficialnames LIKE '%" + marcaSearch.getText() + "%'");*/

						ResultSet rs = conn.createStatement().executeQuery(
								"SELECT * FROM marcasofficial WHERE marcasofficialnames REGEXP '" + Search.special(specialSearch.getText()) + "'"); 
						

						// ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM marcasofficial");
						
						while (rs.next()) {
							data.add(new MarcasData(rs.getString(1)));
						}
						/*data.sort((MarcasData a, MarcasData b) -> {
							StringUtils.getLevenshteinDistance(a.getMarcas(), b.getMarcas());
							return 3;
						});*/
						/*String searchString = specialSearch.getText();
						int searchLen = searchString.length();
						System.out.println("filtering");
						data = data.stream().filter((MarcasData m) -> {
							String marcas = m.getMarcas();
							int diff = StringUtils.getLevenshteinDistance(searchString, marcas);
							//double quote = (double)((double)diff/(double)searchLen);
							return diff < 4;
						}).collect(Collectors.toCollection(FXCollections::observableArrayList));*/
						System.out.println("done filtering: " + data.size());
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}

					marcasColumn.setCellValueFactory(new PropertyValueFactory<MarcasData, String>("marcas"));
					marcasTable.setItems(null);
					marcasTable.setItems(data);
					
				} else {
					System.out.println("NOTHING WRITTEN ANYWERE");
				}
		    }
		});
	}

	@FXML
	private void reloadHandler(ActionEvent event) {
		Executors.newSingleThreadExecutor().execute(new Runnable() {
		    @Override
		    public void run() {
		    	loadDatabaseIntoTable();
		    }
		});
		marcaSearch.clear();
	}
	



}
