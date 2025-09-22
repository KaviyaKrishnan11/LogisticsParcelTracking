package com.kce.logistics.model;

import java.util.ArrayList;
import java.util.List;

public class Parcel {
    private String id;
    private Customer recipient;
    private List<ScanEvent> scanEvents = new ArrayList<>();
    private List<DeliveryAttempt> deliveryAttempts = new ArrayList<>();
    private ProofOfDelivery proofOfDelivery;
    private boolean returned;

    public Parcel(String id, Customer recipient) {
        this.id = id;
        this.recipient = recipient;
    }

    public String getId() { return id; }
    public Customer getRecipient() { return recipient; }

    public List<ScanEvent> getScanEvents() { return scanEvents; }
    public List<DeliveryAttempt> getDeliveryAttempts() { return deliveryAttempts; }

    public void addScanEvent(ScanEvent scan) {
        scanEvents.add(scan);
    }

    public void addDeliveryAttempt(DeliveryAttempt attempt) {
        deliveryAttempts.add(attempt);
        if (attempt.isSuccessful()) {
            proofOfDelivery = new ProofOfDelivery("SignedByRecipient");
        } else if (deliveryAttempts.size() >= 3) {
            returned = true;
        }
    }

    public ProofOfDelivery getProofOfDelivery() { return proofOfDelivery; }
    public boolean isReturned() { return returned; }

    public String getStatus() {
        if (returned) return "Returned to Sender";
        if (proofOfDelivery != null) return "Delivered";
        return scanEvents.isEmpty() ? "Created" : "In Transit at " + scanEvents.get(scanEvents.size() - 1).getHub().getName();
    }
}
