
package gameshop.advance.employee;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Lorenzo Di Giuseppe
 * Questa classe si occupa di fare il parsing di file XML.
 */
public class SimpleXMLParser {
    
    private final Document doc;
    private final XPath xpath;
    
    /**
     * 
     * @param fileName String
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public SimpleXMLParser(String fileName) throws ParserConfigurationException, SAXException, IOException
    {
        File fXmlFile = new File(fileName);
        
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	this.doc = dBuilder.parse(fXmlFile);
        XPathFactory xPathfactory = XPathFactory.newInstance();
        this.xpath = xPathfactory.newXPath();
    }
    
    /**
     * 
     * @param id String
     * @return String Valore contenuto nel tag corrispondente all'id passato.
     */
    public String parseByID(String id)
    {
        try {
            XPathExpression expr = xpath.compile("//*[@id = '"+id+"']");
            String evaluate = expr.evaluate(this.doc);
            return evaluate;
        } catch (XPathExpressionException ex) {
            Logger.getLogger(SimpleXMLParser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
}
