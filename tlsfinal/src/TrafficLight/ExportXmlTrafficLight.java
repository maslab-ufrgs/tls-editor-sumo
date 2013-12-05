/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrafficLight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author luiz
 */
public class ExportXmlTrafficLight {

    public String junctionname = "";
    public Map<String, String> ConnectionsValues = new HashMap<String, String>();
    public Map<String, Integer> ContConnection = new HashMap<String, Integer>();

    public String ReadFile(String path) throws IOException {

        System.out.println("path:" + path);
        SAXBuilder builder = new SAXBuilder();
        // File xmlFile = new File("Output/teste.xml");
        File xmlFile = new File(path);
        Map<String, List<String>> Junctions = new HashMap<String, List<String>>();
        Map<String, List<String>> Connections = new HashMap<String, List<String>>();
        int flag = 0;

        try {
            String Xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n";
            Document document = (Document) builder.build(xmlFile);

            Element rootNode = document.getRootElement();

            // Obtém todas as juctions do arquivo de rede
            @SuppressWarnings("rawtypes")
            List Elements = rootNode.getChildren();

            Xml += "<" + rootNode.getName();

            // Get Net Information
            for (int i = 0; i < rootNode.getAttributes().size(); i++) {
                Xml += " " + rootNode.getAttributes().get(i).getName() + "=\""
                        + rootNode.getAttributes().get(i).getValue() + "\" ";
            }
            Xml += "> \n";

            for (int i = 0; i < Elements.size(); i++) {
                flag = 0;
                Element node = (Element) Elements.get(i);

                if (node.getName().equals("junction")) {

                    junctionname = node.getAttribute("id").getValue();

                    ConnectionsValues.put(junctionname, node.getAttribute("intLanes").getValue().toString());
                    ContConnection.put(junctionname, 0);

                    if (!JPanelPhases.GeneralInfo.containsKey(junctionname)) {
                        ContConnection.remove(junctionname);
                        ConnectionsValues.remove(junctionname);

                    }

                    ExportNetFile aux = new ExportNetFile();
                    Xml += "\n" + aux.GenerateTlsXmlFile(node.getAttribute("id").getValue()) + "\n";

                    //System.out.println("  "+ node.getAttribute("id").getValue());
                }

                // Get Elements
                Xml += "<" + node.getName();

                int auxloc = 0;
                for (int j = 0; j < node.getAttributes().size(); j++) {

                    if (node.getName().equals("connection")) {

                        if (node.getAttributes().get(j).getName().equals("via")) {
                            //System.out.println("ConnectionsValues:"+ConnectionsValues);
                            //System.out.println("Other:"+node.getAttributes().get(j).getValue().toString());

                            String aux = node.getAttributes().get(j).getValue();

                            for (String key : ConnectionsValues.keySet()) {

                                if (ConnectionsValues.get(key).contains(node.getAttributes().get(j).getValue())) {
                                    //System.out.println("Temmmm");

                                    Xml += " tl=\"" + key + "\" linkIndex=\"" + ContConnection.get(key) + "\"";
                                    ContConnection.put(key, (ContConnection.get(key) + 1));
                                    auxloc = 1;
                                    break;
                                }
                            }

                        }
                        if (node.getAttributes().get(j).getName().equals("state")) {

                            /*
                             for (String key : ConnectionsValues.keySet()) {
                             System.out.println("ConnectionsValues.get(key):"+ConnectionsValues.get(key)+" node:"+node.getAttributes().get(j).getValue());
                             if (ConnectionsValues.get(key).contains(node.getAttributes().get(j).getValue())) {
                             Xml += " state=\"o\"";  
                             auxloc = 1;
                             }
                             }
                             **/
                            System.out.println("auxloc:" + auxloc);
                            if (auxloc == 0) {
                                Xml += " " + node.getAttributes().get(j).getName() + "=\"" + node.getAttributes().get(j).getValue() + "\"";
                            } else {
                                Xml += " state=\"o\"";
                                //auxloc = 0;
                            }

                        } else {
                            Xml += " " + node.getAttributes().get(j).getName() + "=\"" + node.getAttributes().get(j).getValue() + "\"";
                        }

                    } else {
                        Xml += " " + node.getAttributes().get(j).getName() + "=\"" + node.getAttributes().get(j).getValue() + "\"";
                    }

                    //node.getAttributes()
                }

                if (node.getChildren().size() > 0) {
                    Xml += "> \n";
                    flag = 1;
                } else {
                    Xml += "/> \n";
                }

                for (int k = 0; k < node.getChildren().size(); k++) {
                    Xml += "<" + node.getChildren().get(k).getName() + " ";
                    for (int l = 0; l < node.getChildren().get(k).getAttributes().size(); l++) {

                        //countconnection = 0;
                        Xml += node.getChildren().get(k).getAttributes().get(l).getName() + "=\"" + node.getChildren().get(k).getAttributes().get(l).getValue() + "\" ";

                    }

                    Xml += "/> \n";
                }

                if (flag == 1) {
                    Xml += "</" + node.getName() + "> \n";
                }

                if (node.getName().equals("connection")) {
                } else {
                    ///List Atributes = node.getAttributes();
                }

            }
            Xml += "</" + rootNode.getName() + "> \n";

            //System.out.println(Xml);
            return Xml;
            //FileWriter fw = new FileWriter(nfile);
            //PrintWriter pw = new PrintWriter(fw);

            //pw.print(Xml);
            //pw.close();
            //fw.close();
            //FileWriter fw = new FileWriter(nfile);
            //PrintWriter pw = new PrintWriter(fw);
            //pw.print(Xml);
            //pw.close();
            //fw.close();
            //
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Error:  File Settings unknown in xml.net");
        } catch (JDOMException jdomex) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "File not found. Please indicate the original file from network.", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                String caminhoArquivo = "";
                JFileChooser arquivo = new JFileChooser();
                int retorno = arquivo.showOpenDialog(null);
                if (retorno == JFileChooser.APPROVE_OPTION) {
                    caminhoArquivo = arquivo.getSelectedFile().getAbsolutePath();
                    return ReadFile(caminhoArquivo);
                } else {

                    //não abriu
                }

            } else {

            }

            // file not found
        }

        return null;

    }

    public void Write(String path, String Xml) throws IOException {

        FileWriter fw = new FileWriter(path);
        PrintWriter pw = new PrintWriter(fw);
        pw.print(Xml);
        pw.close();
        fw.close();

    }

    public void ReadFilse(String arquivo) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(arquivo));
            String str;
            while (in.ready()) {
                str = in.readLine();
                if (str.contains("<junction")) {
                }

            }
            in.close();
        } catch (IOException e) {
        }

    }
}
