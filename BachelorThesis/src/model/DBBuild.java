package model;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class DBBuild 
{
	public static PrintWriter brandsDB;
	public static void main(String[] args) throws FileNotFoundException, SAXException, TikaException, ParserConfigurationException, UnsupportedEncodingException
	{
		String xmlERROR = "";
		try 
		{
			brandsDB = new PrintWriter("C:/Users/Henry/Desktop/INPI_marcas.txt", "UTF-8");
			File[] INPI_releases = new File("F:/Henrys/db").listFiles();
			for(File f : INPI_releases) 
			{
				Scanner s = new Scanner(f);
				if(f.getName().endsWith(".txt"))
				{                 
					while (s.hasNextLine())
					{
						String line = s.nextLine();
						if(line.contains("Marca:"))
						{
							String[] lineSplit = line.split("Marca: ");
							if (lineSplit.length > 1)
							{
								brandsDB.println(lineSplit[1]);
							}							
						}        		   
					}
				}
				else if (f.getName().endsWith(".xml"))
				{
					String xml = parseXML(f);
					brandsDB.println(xml);
				}
				else if (f.getName().endsWith(".pdf"))
				{
					String pdf = BodyContentHandler(f);
					brandsDB.println(pdf);
				}
				xmlERROR = f.getAbsolutePath();
			}
			brandsDB.close();
		} 
		catch (UnsupportedEncodingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Out of bounds in XML file at: " + xmlERROR);
		}
		cleanDB(brandsDB);
	}

	private static String parseXML(File xml) throws ParserConfigurationException, SAXException, IOException
	{
		StringBuilder result = new StringBuilder();
		File xmlFile = xml;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("nome");
		for (int temp = 0; temp < nList.getLength(); temp++) 
		{
			Node nNode = nList.item(temp);				
			String marca = nNode.getTextContent();
			result.append(marca);
			result.append(System.getProperty("line.separator"));
		}
		return result.toString();
	}

	private static String BodyContentHandler(File pdf) throws IOException, SAXException, TikaException
	{
		StringBuilder result = new StringBuilder();
		BodyContentHandler handler = new BodyContentHandler(-1);
		Metadata metadata = new Metadata();
		FileInputStream stream = new FileInputStream(pdf);
		ParseContext pcontext = new ParseContext();

		//parsing document
		PDFParser pdfparser = new PDFParser();
		pdfparser.parse(stream, handler, metadata, pcontext);

		//getting content
		String content = handler.toString();			

		List lineSplit = Arrays.asList(content.split("\n"));
		for (Object o : lineSplit)
		{
			if (o.toString().contains("Marca: "))
			{
				result.append(o.toString().substring(7));
				result.append(System.getProperty("line.separator"));
			}
		}		
		return result.toString();
	}
	
	private static PrintWriter cleanDB(PrintWriter db) throws FileNotFoundException, UnsupportedEncodingException
	{
		List oldDB = Arrays.asList(brandsDB.toString());
		PrintWriter finalDB = new PrintWriter("C:/Users/Henry/Desktop/INPI_final.txt", "UTF-8");
		List newDB = Arrays.asList(finalDB);
		for (Object o : oldDB)	
		{
			if (!newDB.contains(o.toString().trim()) && (o.toString().trim() != ""))
			{
				finalDB.append(o.toString());
				finalDB.append("\n");
				newDB = Arrays.asList(finalDB);
			}
		}
		return finalDB;
	}
}


