package metiers;

import entities.Logement;
import entities.RendezVous;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RendezVousBusiness {

    private static List<RendezVous> listeRendezVous = new ArrayList<>();
    private static int compteurId = 1; // pour générer des IDs uniques

    private final LogementBusiness logementMetier = new LogementBusiness();

    public boolean addRendezVous(RendezVous rendezVous) {
        int refLogement = rendezVous.getLogement().getReference();
        Logement logement = logementMetier.getLogementsByReference(refLogement);

        if (logement != null) {
            rendezVous.setLogement(logement);
            rendezVous.setId(compteurId++); // assigner un ID unique
            return listeRendezVous.add(rendezVous);
        }
        return false;
    }

    public List<RendezVous> getListeRendezVous() {
        return new ArrayList<>(listeRendezVous);
    }

    public void setListeRendezVous(List<RendezVous> liste) {
        listeRendezVous = new ArrayList<>(liste);
    }

    public List<RendezVous> getListeRendezVousByLogementReference(int reference) {
        List<RendezVous> liste = new ArrayList<>();
        for (RendezVous r : listeRendezVous) {
            if (r.getLogement().getReference() == reference) {
                liste.add(r);
            }
        }
        return liste;
    }

    public RendezVous getRendezVousById(int id) {
        for (RendezVous r : listeRendezVous) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    public boolean deleteRendezVous(int id) {
        Iterator<RendezVous> iterator = listeRendezVous.iterator();
        while (iterator.hasNext()) {
            RendezVous r = iterator.next();
            if (r.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean updateRendezVous(int id, RendezVous updatedRdv) {
        for (int i = 0; i < listeRendezVous.size(); i++) {
            RendezVous r = listeRendezVous.get(i);
            if (r.getId() == id) {
                Logement logement = logementMetier.getLogementsByReference(updatedRdv.getLogement().getReference());
                if (logement != null) {
                    updatedRdv.setId(id);
                    updatedRdv.setLogement(logement);
                    listeRendezVous.set(i, updatedRdv);
                    return true;
                }
            }
        }
        return false;
    }
}
