package model;

import java.util.Date;

public class Controller {

    private Department[] departments;

    public Controller() {
        this.departments = new Department[100];
    }

    public boolean addBiodiversePlaceInDepartment(String department, String placeName, double placeSize,
            String placePhoto, Date openingDate) {
        BiodiversePlace biodiversePlace = new BiodiversePlace(placeName, placeSize, placePhoto, openingDate);

        for (int i = 0; i < departments.length; i++) {
            if (this.departments[i] != null) {
                if (this.departments[i].getName().equals(department)) {
                    return this.departments[i].addBiodiversePlace(biodiversePlace);

                }
            }

            if (this.departments[i] == null) {
                this.departments[i] = new Department(department);
                return this.departments[i].addBiodiversePlace(biodiversePlace);
            }
        }

        return false;
    }

    public String showBiodiversePlacesOrder() {
        BiodiversePlace[] sortedPlaces = new BiodiversePlace[1000]; // Assuming a maximum of 1000 places
        int index = 0;

        // Collect all BiodiversePlace objects from all departments
        for (Department department : departments) {
            if (department != null) {
                for (BiodiversePlace place : department.getBiodiversePlaces()) {
                    if (place != null) {
                        sortedPlaces[index++] = place;
                    }
                }
            }
        }

        // Sort the array by placeSize in descending order using bubble sort
        for (int i = 0; i < sortedPlaces.length - 1; i++) {
            for (int j = 0; j < sortedPlaces.length - 1 - i; j++) {
                if (sortedPlaces[j] == null || sortedPlaces[j + 1] == null) {
                    continue;
                }
                if (sortedPlaces[j].getPlaceSize() < sortedPlaces[j + 1].getPlaceSize()) {
                    BiodiversePlace temp = sortedPlaces[j];
                    sortedPlaces[j] = sortedPlaces[j + 1];
                    sortedPlaces[j + 1] = temp;
                }
            }
        }

        String result = "";
        for (int i = 0; i < sortedPlaces.length; i++) {
            if (sortedPlaces[i] != null) {
                result += sortedPlaces[i].getPlaceName() + " - " + sortedPlaces[i].getPlaceSize() + " hectáreas\n";
            }
        }

        return result;
    }

    public boolean addCommunity(int idDepartment, int idPlace, String communityName, String communityType,
            int communitySize,
            String[] problems, String nameManager, String phoneManager) {
        communityType = communityType.toUpperCase();
        CommunityType type = CommunityType.valueOf(communityType);

        if (type == null) {
            return false;
        }

        ProblemTypes[] problemsType = new ProblemTypes[4];

        for (int i = 0; i < problems.length; i++) {
            problemsType[i] = ProblemTypes.valueOf(problems[i].toUpperCase());
            if (problemsType[i] == null) {
                return false;
            }
        }

        if (this.departments[idDepartment] != null) {
            if (this.departments[idDepartment].getBiodiversePlaces()[idPlace] != null) {
                Manager manager = new Manager(nameManager, phoneManager);
                Community community = new Community(communityName, type, communitySize, manager, problemsType);
                return this.departments[idDepartment].getBiodiversePlaces()[idPlace].addCommunity(community);
            }
        }
        return false;

    }

    public String showDepartments() {
        String result = "";
        for (int i = 0; i < departments.length; i++) {
            if (this.departments[i] != null) {
                result += i + ". " + this.departments[i].getName() + "\n";
            }
        }
        return result;
    }

    public String showPlacesInDepartment(int department) {
        if (this.departments[department] != null) {
            BiodiversePlace[] places = this.departments[department].getBiodiversePlaces();
            String result = "";
            for (int j = 0; j < places.length; j++) {
                if (places[j] != null) {
                    result += j + ". " + places[j].getPlaceName() + " - " + places[j].getPlaceSize() + " hectáreas\n";
                }
            }
            return result;
        }
        return "No se encontró el departamento.";
    }

    public String showCommunityTypes() {
        String result = "";
        for (CommunityType type : CommunityType.values()) {
            result += type + "\n";
        }
        return result;
    }

    public String showProblemTypes() {
        String result = "";
        for (ProblemTypes type : ProblemTypes.values()) {
            result += type + "\n";
        }
        return result;
    }

    public String showPlacesWithoutSchoolOrHospital() {
        BiodiversePlace[] places = new BiodiversePlace[1000]; // Assuming a maximum of 1000 places
        int index = 0;

        for (Department department : departments) { // Iteracion sin contador de indice
            if (department != null) { // esto no deberia pasar, pero a veces pasa
                for (BiodiversePlace place : department.getBiodiversePlaces()) {
                    if (place != null) {
                        boolean hasNotSchool = false;
                        boolean hasNotHospital = false;

                        for (Community community : place.getCommunities()) {
                            if (community != null) {
                                for (ProblemTypes problem : community.getProblems()) {
                                    if (problem == ProblemTypes.NO_SCHOOL) {
                                        hasNotSchool = true;
                                    }
                                    if (problem == ProblemTypes.NO_HOSPITAL) {
                                        hasNotHospital = true;
                                    }
                                }
                            }
                        }

                        if (place.getCommunities()[0] == null) {
                            hasNotSchool = true;
                            hasNotHospital = true;
                        }

                        if (hasNotSchool || hasNotHospital) {
                            places[index++] = place;
                        }
                    }
                }
            }
        }

        String result = "";
        for (int i = 0; i < places.length; i++) {
            if (places[i] != null) {
                result += places[i].getPlaceName() + "\n";
            }
        }

        return result;
    }
}