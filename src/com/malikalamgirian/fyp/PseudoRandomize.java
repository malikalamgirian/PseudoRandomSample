/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.malikalamgirian.fyp;

import java.io.File;
import java.util.Random;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

/**
 *
 * @author Wasif
 */
public class PseudoRandomize {

    /* Declarations */
    NodeList pair,
            string_Pair;
    int pairsToSave = 25;

    public boolean Randomize(String xml_File_To_Randomize_URL) throws Exception {

        /* Here we have to randomize the provided xml file by using "pairsToSave"
         * so we will delete all the other pairs randomly
         *
         * 1. Get XML Document
         * 2. Get Document Element
         * 3. Get All Pairs
         * 4. Delete pairs till they are not equal to "pairsToSave" in number
         * 5. Transform File to randomized_File_URL
         */
        Random random;
        String randomized_File_URL;
        int randomNumber;

        try {

            /* Set output file URL */
            randomized_File_URL = xml_File_To_Randomize_URL.substring(
                    0, xml_File_To_Randomize_URL.indexOf(".")) + "_randomized.xml";

            /* 1.Get XML Document */
            Document doc = com.malikalamgirian.fyp.GetXMLDocument.getXMLDocument(xml_File_To_Randomize_URL);
            
            /* 2. Get Document Element */
            Element root = doc.getDocumentElement();

            /* 2.1 rename Document Element as  Filtered Document Properly */
            doc.renameNode(root, null, root.getNodeName() + "_randomized");

            /* 3. Get all pairs */
            pair = root.getElementsByTagName("Pair");

            System.out.println("\nLenght of pair is : " + pair.getLength());
            
            /* 4. Traverse each pair */
            while (pair.getLength() > pairsToSave) {

                /* 4.1 Get a random number between 0 - pair.getLength()
                 * 4.2 Delete that pair
                 */

                /* 4.1 Get a random number between 0 and pair.getLength()*/
                random = new Random();
                randomNumber = random.nextInt(pair.getLength());

                /* 4.2 Delete the specific pair */
                root.removeChild(pair.item(randomNumber));

                pair = root.getElementsByTagName("Pair");
            }

            /* 5. Transform File to outputFileURL */
            doc.normalize();
            doc.normalizeDocument();
            root.normalize();

            
            Result dest = new StreamResult(new File(randomized_File_URL));

            GetXMLDocument.TransformXML(doc, dest);


        } catch (Exception ex) {
            throw new Exception("randomize has gotten some exceptional case. It is :" + ex + " " + ex.getMessage() );
        }

        return true;
        
    } /* ends method Randomize */

}
