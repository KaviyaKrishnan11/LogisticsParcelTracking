package com.kce.logistics.model;

import java.time.LocalDateTime;

public class DeliveryAttempt {
    private boolean successful;
    private LocalDateTime time;

    public DeliveryAttempt(boolean successful) {
        this.successful = successful;
        this.time = LocalDateTime.now();
    }

    public boolean isSuccessful() { return successful; }
    public LocalDateTime getTime() { return time; }

    @Override
    public String toString() {
        return time + ": Attempt - " + (successful ? "Successful" : "Failed");
    }
}
