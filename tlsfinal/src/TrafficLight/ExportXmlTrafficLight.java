/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrafficLight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void ReadFile() {
        SAXBuilder builder = new SAXBuilder();
        // File xmlFile = new File("Output/teste.xml");
        File xmlFile = new File("/home/luiz/sumo-0.16.0/docs/examples/sumo/simple_nets/box/box4l/net.net.xml");
        Map<String, List<String>> Junctions = new HashMap<String, List<String>>();
        Map<String, List<String>> Connections = new HashMap<String, List<String>>();
        int flag = 0;
        String Xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n";
        try {
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




                for (int j = 0; j < node.getAttributes().size(); j++) {

                    if (node.getName().equals("connection")) {

                        if (node.getAttributes().get(j).getName().equals("state")) {
                            int auxloc = 0;
                            for (String key : ConnectionsValues.keySet()) {

                                if (ConnectionsValues.get(key).contains(node.getAttributes().get(j).getValue())) {
                                Xml += " state=\"o\"";  
                                auxloc = 1;
                                }
                            }
                            
                            if(auxloc == 0){
                              Xml += " " + node.getAttributes().get(j).getName() + "=\"" + node.getAttributes().get(j).getValue() + "\"";  
                            }

                        } else {
                            Xml += " " + node.getAttributes().get(j).getName() + "=\"" + node.getAttributes().get(j).getValue() + "\"";
                        }
                        if (node.getAttributes().get(j).getName().equals("via")) {
                            //System.out.println("ConnectionsValues:"+ConnectionsValues);
                            //System.out.println("Other:"+node.getAttributes().get(j).getValue().toString());

                            String aux = node.getAttributes().get(j).getValue();

                            for (String key : ConnectionsValues.keySet()) {

                                if (ConnectionsValues.get(key).contains(node.getAttributes().get(j).getValue())) {
                                    //System.out.println("Temmmm");

                                    Xml += " tl=\"" + key + "\" linkIndex=\"" + ContConnection.get(key) + "\"";
                                    ContConnection.put(key, (ContConnection.get(key) + 1));
                                    break;
                                }
                            }


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



        } catch (IOException io) {
        } catch (JDOMException jdomex) {
        }

        System.out.println(Xml);

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
