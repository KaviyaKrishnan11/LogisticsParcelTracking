package com.kce.logistics.service;
import com.kce.exception.ParcelNotFoundException;
import com.kce.logistics.model.*;

import java.util.*;

public class LogisticService {
	private Map<String, Parcel> parcels = new HashMap<>();
    private Map<String, Shipment> shipments = new HashMap<>();
    private Map<String, Hub> hubs = new HashMap<>();

    public void createParcel(String parcelId, Customer customer) {
        parcels.put(parcelId, new Parcel(parcelId, customer));
    }

    public void createShipment(String shipmentId, List<String> parcelIds) {
        Shipment shipment = new Shipment(shipmentId);
        for (String id : parcelIds) {
            Parcel p = parcels.get(id);
            if (p != null) shipment.addParcel(p);
        }
        shipments.put(shipmentId, shipment);
    }

    public void addHub(String hubId, String name) {
        hubs.put(hubId, new Hub(hubId, name));
    }

    public void recordScan(String parcelId, String hubId) {
        Parcel parcel = parcels.get(parcelId);
        Hub hub = hubs.get(hubId);
        if (parcel == null) throw new ParcelNotFoundException(parcelId);
        parcel.addScanEvent(new ScanEvent(parcel, hub));
    }

    public void recordDeliveryAttempt(String parcelId, boolean success) {
        Parcel parcel = parcels.get(parcelId);
        if (parcel == null) throw new ParcelNotFoundException(parcelId);
        parcel.addDeliveryAttempt(new DeliveryAttempt(success));
    }

    public String getParcelStatus(String parcelId) {
        Parcel parcel = parcels.get(parcelId);
        if (parcel == null) throw new ParcelNotFoundException(parcelId);

        StringBuilder sb = new StringBuilder("Parcel " + parcelId + " Status:\n");
        sb.append("Recipient: ").append(parcel.getRecipient()).append("\n");

        for (ScanEvent e : parcel.getScanEvents()) sb.append(e).append("\n");
        for (DeliveryAttempt a : parcel.getDeliveryAttempts()) sb.append(a).append("\n");

        if (parcel.getProofOfDelivery() != null)
            sb.append("Delivered: ").append(parcel.getProofOfDelivery()).append("\n");

        if (parcel.isReturned()) sb.append("Status: Returned to sender\n");
        else sb.append("Current Status: ").append(parcel.getStatus()).append("\n");

        return sb.toString();
    }

    public String getShipmentSummary(String shipmentId) {
        Shipment shipment = shipments.get(shipmentId);
        if (shipment == null) return "Shipment not found.";
        return shipment.getSummary();
    }
}