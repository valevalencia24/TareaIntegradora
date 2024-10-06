package model;

import java.util.Date;

public class BiodiversePlace {
    private String placeName;
    private double placeSize;
    private String placePhoto;
    private Date openingDate;
    private Double resourcesNeeded; // ESTO NO ENTIENDO PA Q SIRVE
    private Animal[] animals;
    private Community[] communities;

    public BiodiversePlace(String placeName, double placeSize, String placePhoto, Date openingDate) {
        this.placeName = placeName;
        this.placeSize = placeSize;
        this.placePhoto = placePhoto;
        this.openingDate = openingDate;
        this.resourcesNeeded = 0.0;
        this.animals = new Animal[100];
        this.communities = new Community[100];
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getPlaceSize() {
        return placeSize;
    }

    public void setPlaceSize(double placeSize) {
        this.placeSize = placeSize;
    }

    public String getPlacePhoto() {
        return placePhoto;
    }

    public void setPlacePhoto(String placePhoto) {
        this.placePhoto = placePhoto;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Double getResourcesNeeded() {
        return resourcesNeeded;
    }

    public void setResourcesNeeded(Double resourcesNeeded) {
        this.resourcesNeeded = resourcesNeeded;
    }

    public Animal[] getAnimals() {
        return animals;
    }

    public void setAnimals(Animal[] animals) {
        this.animals = animals;
    }

    public Community[] getCommunities() {
        return communities;
    }

    public void setCommunities(Community[] communities) {
        this.communities = communities;
    }

    public boolean addCommunity(Community community) {
        for (int i = 0; i < communities.length; i++) {
            if (communities[i] == null) {
                communities[i] = community;
                return true;
            }
        }
        return false;
    }

    
}