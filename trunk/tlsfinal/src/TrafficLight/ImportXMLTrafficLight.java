package TrafficLight;


import static TrafficLight.ProgressMonitorExample.pbar;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ProgressMonitor;

import org.jdom2.Document;
/*import org.jdom.Document;
 import org.jdom.Element;
 import org.jdom.JDOMException;
 import org.jdom.input.SAXBuilder;
 */
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ImportXMLTrafficLight {

	public Map<String, List<List<String>>> From_To = new HashMap<String, List<List<String>>>();;
        public static  Map<String, String> ConvertConnection = new HashMap<String, String>();

	public Map<String, List<String>> GetFromToInformation(String Arquivo) {
                SAXBuilder builder = new SAXBuilder();
		// File xmlFile = new File("Output/teste.xml");
		File xmlFile = new File(Arquivo);
		Map<String, List<String>> Junctions = new HashMap<String, List<String>>();
                Map<String, List<String>> InternalJunctions = new HashMap<String, List<String>>();
		Map<String, List<String>> Connections = new HashMap<String, List<String>>();
                Map<String, List<String>> NewConnections = new HashMap<String, List<String>>();
                
                //Map<String, List<String>> Edges = new HashMap<String, List<String>>();
		
                
                try {
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
             
			// Obtém todas as juctions do arquivo de rede
			@SuppressWarnings("rawtypes")
			List list = rootNode.getChildren("junction");
                        for (int i = 0; i < list.size(); i++) {
				Element node = (Element) list.get(i);
                                if(!node.getAttributeValue("id").toString().substring(0, 1).equals(":")){
                                   Junctions.put(node.getAttributeValue("id"),
						Arrays.asList(node.getAttributeValue("intLanes").split(
								" ")));
                                }else{
                                  InternalJunctions.put(node.getAttributeValue("id"),
						Arrays.asList(node.getAttributeValue("incLanes").split(
								" ")));
                                    
                                }
				
			}
                         
			list.clear();
                        
                        
                        // Obtém todas as edges do arquivo de rede
			/*
			List edges = rootNode.getChildren("edge");
                        for (int i = 0; i < edges.size(); i++) {
				Element node = (Element) edges.get(i);
                                
                                String EdgeId = node.getAttributeValue("id");
                                List lanes =  node.getChildren("lane");
                                List <String> LanesId = new ArrayList<String>();
                                for(int j=0; j< lanes.size();j++){
                                  Element lane = (Element) lanes.get(j);
                                  if( lane.getAttribute("id") != null){
                                  LanesId.add(lane.getAttributeValue("id"));
                                  }
                                }
                                
                                if(Edges.containsKey(EdgeId)){
                                    List <String> LanesOld = Edges.get(EdgeId);
                                    LanesOld.addAll(LanesId);
                                    Edges.put(EdgeId, LanesOld);
                                    
                                }else{
                                    Edges.put(EdgeId, LanesId);	
                                }
			}
			edges.clear();
                                
                        
                        //System.out.println("Edges:"+ Edges)
                        */


			// Obtém todas as conections do arquivo de rede
			list = rootNode.getChildren("connection");
                        
			for (int i = 0; i < list.size(); i++) {
				Element node = (Element) list.get(i);
				// verifica se existe a definição de uma via
				if (node.getAttributeValue("via")!= null) {
					String aux = node.getAttribute("from").toString();
					// Verifica se não eh uma conexão interna gerada
					//if (!aux.contains("[Attribute: from=\":")) {       
						Connections.put(node.getAttributeValue("via"), Arrays
								.asList(node.getAttributeValue("from"),
										node.getAttributeValue("to"),node.getAttributeValue("fromLane"),node.getAttributeValue("toLane")));
					//}
				}
			}
                        
                        ////System.out.println("\n Old Connection:"+Connections);
                        
                        
    // #----------------------- TRADUZ AS INTERNAL CONNECTIONS ----------------------# Map<String, List<String>> Connections
                        for(String Connection : Connections.keySet()){
                            // Caso From seja internal
                            if(Connections.get(Connection).get(0).subSequence(0,1).equals(":")){
                                List<String> laneId = InternalJunctions.get(Connection);
                                List<String> Auxconn = Connections.get(laneId.get(0));
                                
                                //ConvertConnection.put(Connections.get(Connection).get(0), Auxconn.get(0));
                                //ConvertConnection.put(Connection, Auxconn.get(0));
                                ConvertConnection.put(Connection, laneId.get(0));
                                
                                NewConnections.put(Connection, Auxconn);
                            } else{
                                NewConnections.put(Connection, Connections.get(Connection));
                            }
                        }
                        
                        Connections.clear();
                        Connections=  NewConnections;

                        ////System.out.println("Junction:\n"+Junctions);
                        ////System.out.println("Connection:\n"+Connections);
                        
// #----------------------- GERA A ESTRUTURA SEMAFORICA FROM/TO ----------------------# 
			

			for( String intLaneJunction : Junctions.keySet()){
				List<List<String>> values = new ArrayList<List<String>>();
				for( String idConexao :  Connections.keySet()){
					 if(Junctions.get(intLaneJunction).contains(idConexao)){
                                                ArrayList lista1 = new ArrayList<String>(); 
                                                lista1.add("Name"); 
                                                lista1.add("0");
                                                List<String> auxValues = Connections.get(idConexao);     
                                                List<String> novaLista = new ArrayList<String>(Connections.get(idConexao));  
                                                novaLista.addAll(lista1);  
						//values.add(Connections.get(idConexao));
                                                values.add(novaLista);    
					 }
				}
                                
				From_To.put(intLaneJunction, values);	
                                
                                
                                
			}
                        
                        ////System.out.println("Fromto \n"+From_To);
                        

			// //System.out.println(DePara);
			
			//GetXMLDEPARA();
			// from="1i" to="3o" fromLane="0" toLane="0" via=":0_12_0"

		} catch (IOException io) {
			////System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			////System.out.println(jdomex.getMessage());
		}
		return null;
	}
	

	
}