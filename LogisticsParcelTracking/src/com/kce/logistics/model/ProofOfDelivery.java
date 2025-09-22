package com.kce.logistics.model;

public class ProofOfDelivery {
    private String signature;

    public ProofOfDelivery(String signature) {
        this.signature = signature;
    }

    public String getSignature() { return signature; }

    @Override
    public String toString() {
        return "Signed by: " + signature;
    }
}
