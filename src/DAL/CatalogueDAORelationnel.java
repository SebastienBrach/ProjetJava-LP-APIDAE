package DAL;

import Metier.Catalogue;
import Metier.I_Catalogue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogueDAORelationnel implements I_CatalogueDAO {
    private Connection cn = null;

    public CatalogueDAORelationnel() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            cn = DriverManager.getConnection("jdbc:oracle:thin:@162.38.222.149:1521:iut", "brachs", "21092020");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean create(I_Catalogue catalogue) throws DAOException {
        PreparedStatement pst = null;

        try {
            pst = cn.prepareStatement("CALL newCatalogue(?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            pst.setString(1, catalogue.getNom());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public List<I_Catalogue> read() throws DAOException {
        PreparedStatement pst = null;

        try {
            pst = cn.prepareStatement("SELECT idCatalogue, nomCatalogue FROM catalogue");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet rows = null;

        try {
            rows = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            List<I_Catalogue> catalogues = new ArrayList<>();

            while (rows.next()) {
                String nomCatalogue;
                nomCatalogue = rows.getString("nomCatalogue");

                int idCatalogue;
                idCatalogue = rows.getInt("idCatalogue");

                catalogues.add(new Catalogue(nomCatalogue, idCatalogue));
            }

            return catalogues;
        } catch (SQLException e) {
            e.printStackTrace();

            throw new DAOException();
        }
    }

    @Override
    public boolean delete(I_Catalogue catalogue) throws DAOException {
        return delete(catalogue.getNom());
    }

    @Override
    public boolean delete(String nomCatalogue) throws DAOException {
        PreparedStatement pst = null;

        try {
            pst = cn.prepareStatement("DELETE FROM catalogue WHERE nomCatalogue = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            pst.setString(1, nomCatalogue);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet rows = null;

        try {
            rows = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace();
        }

        try {
            boolean isRowExist = rows.next();

//            if (!isRowExist) {
//                e.printStackTrace();
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
