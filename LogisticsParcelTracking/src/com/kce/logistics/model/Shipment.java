package com.kce.logistics.model;

import java.util.ArrayList;
import java.util.List;

public class Shipment {
    private String id;
    private List<Parcel> parcels = new ArrayList<>();

    public Shipment(String id) {
        this.id = id;
    }

    public String getId() { return id; }
    public List<Parcel> getParcels() { return parcels; }

    public void addParcel(Parcel parcel) {
        parcels.add(parcel);
    }

    public boolean isClosed() {
        for (Parcel p : parcels) {
            if (!p.isReturned() && p.getProofOfDelivery() == null) return false;
        }
        return true;
    }

    public String getSummary() {
        StringBuilder sb = new StringBuilder("Shipment " + id + " Summary:\n");
        for (Parcel p : parcels) {
            sb.append("Parcel ").append(p.getId()).append(": ").append(p.getStatus()).append("\n");
        }
        sb.append("Shipment Status: ").append(isClosed() ? "Closed" : "Open").append("\n");
        return sb.toString();
    }
}
