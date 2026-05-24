package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private final int maxWeight;
    private final List<T> parcels = new ArrayList<>();
    private int currentWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public boolean addParcel(T parcel) {
        if (currentWeight + parcel.getWeight() > maxWeight) {
            System.out.println("Превышен максимальный вес коробки. Посылка <<" + parcel.getDescription() + ">> не добавлена.");
            return false;
        }

        parcels.add(parcel);
        currentWeight += parcel.getWeight();
        return true;
    }

    public List<T> getAllParcels() {
        return Collections.unmodifiableList(parcels);
    }
}
