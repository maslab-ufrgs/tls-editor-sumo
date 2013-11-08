/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrafficLight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;

/**
 *
 * @author luiz
 */
public class ExportNetFile {

    //public static Map<String, List<String>> GeneralInfo = new HashMap<String, List<String>>();  
    //public static Map<String, List<List<String>>> PhaseInfo = new HashMap<String, List<List<String>>>();
    //public static Map<String, List<List<String>>> SavedPhaseInfo = new HashMap<String, List<List<String>>>();
    //public static Map<String, List<JTable>> PhaseTabel = new HashMap<String, List<JTable>>();
    public String GenerateTlsXmlFile(String Junc) {

        String xml = "";
        int id = 0;
        // Identifica as Conections
        for (String IdJunction : JPanelPhases.GeneralInfo.keySet()) {

            if (IdJunction.equals(Junc)) {

                String ProgramID = JPanelPhases.GeneralInfo.get(IdJunction).get(0);
                String offset = JPanelPhases.GeneralInfo.get(IdJunction).get(2);
                String tlsXml = "<tlLogic id=\"" + Junc + "\" type=\"static\" programId=\"" + ProgramID + "\" offset=\"" + offset + "\">\n";
                // Identifica as informações da fase
                id++;
                for (String phases : JPanelPhases.PhaseInfo.keySet()) {

                    // Verificam se estão na mesma conection
                    if (IdJunction == phases) {
                        // Identifica todas as fases
                        //System.out.println("JPanelPhases.PhaseInfo.get(phases):"+JPanelPhases.PhaseInfo.get(phases));
                        for (int i = 0; i < JPanelPhases.PhaseInfo.get(phases).size(); i++) {
                            //System.out.println("Phase:"+i+" Total:"+JPanelPhases.PhaseInfo.get(phases).get(0).size());
                            String duration = JPanelPhases.PhaseInfo.get(phases).get(i).get(2);

                            tlsXml = tlsXml + getPhase(IdJunction, i, duration);

                        }
                    }
                }
                tlsXml = tlsXml + "</tlLogic>";
                xml = tlsXml;
                //System.out.println(tlsXml);

            }
        }
        return xml;



    }

    public String getPhase(String IdJunction, int Fase, String duration) {
        String XmlPhase = "<phase duration=\"" + duration + "\" state=\"";
        // Identifica as proprias fases
        String state = "";

        if (JPanelPhases.SavedPhaseInfo.containsKey(IdJunction) && JPanelPhases.SavedPhaseInfo.containsKey(IdJunction)) {

            if (JPanelPhases.PhaseTabel.containsKey(IdJunction)) {
                for (int row = 0; row < JPanelPhases.PhaseTabel.get(IdJunction).get(Fase).getRowCount(); row++) {

                    if (JPanelPhases.PhaseTabel.get(IdJunction).get(Fase).getValueAt(row, 0).equals(true)) {
                        state = state + "G";
                    } else {
                        state = state + "r";
                    }
                }
                XmlPhase = XmlPhase + state + "\" />\n";
                return XmlPhase;
            }
        }


        return XmlPhase;

    }
}
