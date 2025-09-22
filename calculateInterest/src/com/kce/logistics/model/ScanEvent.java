package com.kce.logistics.model;

import java.time.LocalDateTime;

public class ScanEvent {
    private Parcel parcel;
    private Hub hub;
    private LocalDateTime timestamp;

    public ScanEvent(Parcel parcel, Hub hub) {
        this.parcel = parcel;
        this.hub = hub;
        this.timestamp = LocalDateTime.now();
    }

    public Parcel getParcel() { return parcel; }
    public Hub getHub() { return hub; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return timestamp + ": Scanned at " + hub.getName();
    }
}
