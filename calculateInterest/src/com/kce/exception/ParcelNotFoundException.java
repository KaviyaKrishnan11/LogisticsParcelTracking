package com.kce.exception;

public class ParcelNotFoundException extends RuntimeException {
    public ParcelNotFoundException(String id) {
        super("Parcel with ID " + id + " not found.");
    }
}
