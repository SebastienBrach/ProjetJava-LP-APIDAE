package DAL;

import Metier.Catalogue;
import Metier.I_Catalogue;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CatalogueDAOXML implements I_CatalogueDAO {
    private String uri = "Catalogues.xml";
    private Document doc;

    public CatalogueDAOXML() {
        SAXBuilder sdoc = new SAXBuilder();
        try {
            doc = sdoc.build(uri);
        } catch (Exception e) {
            System.out.println("erreur construction arbre JDOM");
        }
    }

    @Override
    public boolean create(I_Catalogue catalogue) throws DAOException {
        try {
            Element root = doc.getRootElement();
            Element cat = new Element("catalogue");
            cat.setAttribute("nom", catalogue.getNom());
            root.addContent(cat);
            return save();
        } catch (Exception e) {
            System.out.println("erreur creer catalogue");
            return false;
        }
    }

    @Override
    public List<I_Catalogue> read() throws DAOException {
        List<I_Catalogue> l = new ArrayList<>();
        try {
            Element root = doc.getRootElement();
            List<Element> lCat = root.getChildren("catalogue");

            for (Element cat : lCat) {
                String nomCat = cat.getAttributeValue("nom");
                l.add(new Catalogue(nomCat));
            }
        } catch (Exception e) {
            System.out.println("erreur lireTous tous les catalogues");
        }
        return l;
    }

    @Override
    public boolean delete(I_Catalogue catalogue) throws DAOException {
        return this.delete(catalogue.getNom());
    }

    @Override
    public boolean delete(String nomCatalogue) throws DAOException {
        try {
            Element root = doc.getRootElement();
            Element cat = chercheCatalogue(nomCatalogue);
            if (cat != null) {
                root.removeContent(cat);
                return save();
            } else
                return false;
        } catch (Exception e) {
            System.out.println("erreur supprimer catalogue");
            return false;
        }
    }

    private boolean save() {
        System.out.println("Sauvegarde");
        XMLOutputter out = new XMLOutputter();
        try {
            out.output(doc, new PrintWriter(uri));
            return true;
        } catch (Exception e) {
            System.out.println("erreur sauvegarde dans fichier XML");
            return false;
        }
    }

    private Element chercheCatalogue(String nom) {
        Element root = doc.getRootElement();
        List<Element> lCat = root.getChildren("catalogue");
        int i = 0;
        while (i < lCat.size() && !lCat.get(i).getAttributeValue("nom").equals(nom))
            i++;
        if (i < lCat.size())
            return lCat.get(i);
        else
            return null;
    }
}
