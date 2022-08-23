/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.groupes;
import utils.MyDb;

/**
 *
 * @author DINA CHIHAOUI
 */
public class ServiceGroupe implements Iservices<groupes>{
  Connection cnx = MyDb.getInstance().getCnx();
    ObservableList<groupes> groupesList = FXCollections.observableArrayList();
    @Override
    public void ajouter(groupes t) {
        String req = "INSERT INTO `groupes`(`nom`) VALUES (?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNom());
            ps.executeUpdate();
            System.out.println("PS :  ajoutée avec roupes succés!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGroupe.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<groupes> afficher() {
String req = "SELECT * FROM groupes";
        try {
            groupesList.clear();

            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                groupes gp = new groupes();
                gp.setId(rs.getInt("id"));
                gp.setNom(rs.getString("nom"));
                groupesList.add(gp);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGroupe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groupesList ;        }

    @Override
    public void supprimer(groupes t) {
 try {
            String req = "DELETE FROM `groupes` WHERE id = '" + t.getId() + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Groupes supprimer avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGroupe.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void modifier(groupes t) {
 String req = "UPDATE `groupes` SET "
                + "`nom`=? WHERE id = '" + t.getId() + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNom());
            ps.executeUpdate();
            System.out.println("PS : Catégorie de jeux Modifié avec succés!");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGroupe.class.getName()).log(Level.SEVERE, null, ex);
        }    }
    
}
