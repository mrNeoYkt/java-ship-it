package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private final int maxWeight;
    private final List<T> parcels = new ArrayList<>();

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public boolean addParcel(T parcel) {
        if (getCurrentWeight() + parcel.getWeight() > maxWeight) {
            System.out.println("Превышен максимальный вес коробки. Посылка <<" + parcel.getDescription() + ">> не добавлена.");
            return false;
        }

        parcels.add(parcel);
        return true;
    }

    public List<T> getAllParcels() {
        return Collections.unmodifiableList(parcels);
    }

    private int getCurrentWeight() {
        int currentWeight = 0;
        for (T parcel : parcels) {
            currentWeight += parcel.getWeight();
        }
        return currentWeight;
    }
}
