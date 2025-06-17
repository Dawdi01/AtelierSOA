package metiers;

import entities.Logement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LogementBusiness {
    private static List<Logement> logements = new ArrayList<>();

    static {
        logements.add(new Logement(1, "27, Rue des roses", "El ghazela", "Ariana", "Studio", "cuisine equipee", 300f));
        logements.add(new Logement(5, "58, Rue des roses", "El ghazela", "Ariana", "EtageVilla", "cuisine equipee", 450f));
        logements.add(new Logement(2, "201, Résidence Omrane4", "Riadh El Andalous", "Ariana", "EtageVilla", "chauffage central, ascenseur, climatisation", 700f));
        logements.add(new Logement(3, "540, Résidence Les Tulipes", "El Aouina", "Ariana", "Appartement", "S+2, chauffage central, ascenseur, climatisation", 500f));
        logements.add(new Logement(4, "78, Rue des Oranges", "Bardo", "Tunis", "EtageVilla", "chauffage central, ascenseur, climatisation", 400f));
    }

    public List<Logement> getLogements() {
        return logements;
    }

    public Logement getLogementsByReference(int reference) {
        return logements.stream()
                .filter(l -> l.getReference() == reference)
                .findFirst()
                .orElse(null);
    }

    public boolean addLogement(Logement logement) {
        return logements.add(logement);
    }

    public boolean updateLogement(int reference, Logement updatedLogement) {
        for (int i = 0; i < logements.size(); i++) {
            if (logements.get(i).getReference() == reference) {
                logements.set(i, updatedLogement);
                return true;
            }
        }
        return false;
    }

    public boolean deleteLogement(int reference) {
        Iterator<Logement> iterator = logements.iterator();
        while (iterator.hasNext()) {
            Logement l = iterator.next();
            if (l.getReference() == reference) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Logement> getLogementsByDelegation(String delegation) {
        List<Logement> result = new ArrayList<>();
        for (Logement l : logements) {
            if (l.getDelegation().equalsIgnoreCase(delegation)) {
                result.add(l);
            }
        }
        return result;
    }

    public List<Logement> getLogementsListeByref(int reference) {
        List<Logement> result = new ArrayList<>();
        for (Logement l : logements) {
            if (l.getReference() == reference) {
                result.add(l);
            }
        }
        return result;
    }

    public void setLogements(List<Logement> logements) {
        LogementBusiness.logements = logements;
    }
}
