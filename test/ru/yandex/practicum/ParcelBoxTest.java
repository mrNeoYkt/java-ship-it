package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ParcelBoxTest {
    @Test
    void shouldAddParcelWhenMaxWeightIsNotExceeded() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        StandardParcel parcel = new StandardParcel("книга про Java", 5, "Норильск", 1);

        box.addParcel(parcel);

        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void shouldAddParcelWhenWeightEqualsMaxWeight() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        StandardParcel parcel = new StandardParcel("журналы, 5 шт.", 10, "Новосибирск", 2);

        box.addParcel(parcel);

        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void shouldNotAddParcelWhenMaxWeightIsExceeded() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        StandardParcel firstParcel = new StandardParcel("книга про Java", 5, "Норильск", 1);
        StandardParcel secondParcel = new StandardParcel("журналы, 5 шт.", 10, "Новосибирск", 2);

        box.addParcel(firstParcel);
        box.addParcel(secondParcel);

        assertEquals(1, box.getAllParcels().size());
    }
}
