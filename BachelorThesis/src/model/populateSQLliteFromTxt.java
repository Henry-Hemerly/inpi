package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class populateSQLliteFromTxt {

	public void populate() throws SQLException {
		try {

            File f = new File("data.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));
            System.out.println("Reading file using Buffered Reader");
            Connection conn = null;
            PreparedStatement pstmnt = null;
           
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String line;
//                while ((line = br.readLine()) != null) {
                	//System.out.println(line);
					String sqlInsert = "LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/data.txt'\r\n" + 
							"REPLACE INTO TABLE marcasofficial\r\n" + 
							"LINES\r\n" + 
							"    TERMINATED BY '\\n' (marcasofficialnames);";

					
					try {
						conn = DBConnection.getConnection();
						pstmnt = conn.prepareStatement(sqlInsert);


						//pstmnt.setString(1);
						pstmnt.execute();

					} catch (SQLException e) {
						System.out.println(e);

					}

					conn.close();
					pstmnt.close();
                }
            //}
                
               
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
}
