package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryCostTest {
    @Test
    void shouldCalculateStandardParcelDeliveryCost() {
        StandardParcel parcel = new StandardParcel("кирпичи обыкновенные", 50, "Москва", 10);

        assertEquals(100, parcel.calculateDeliveryCost());
    }

    @Test
    void shouldCalculateFragileParcelDeliveryCost() {
        FragileParcel parcel = new FragileParcel("императорский фарфор", 3, "Санкт-Петербург", 11);

        assertEquals(12, parcel.calculateDeliveryCost());
    }

    @Test
    void shouldCalculatePerishableParcelDeliveryCost() {
        PerishableParcel parcel = new PerishableParcel("пирог черничный", 4, "Тверь", 12, 2);

        assertEquals(12, parcel.calculateDeliveryCost());
    }

    @Test
    void shouldReturnZeroCostForZeroWeightParcel() {
        StandardParcel parcel = new StandardParcel("конверт с письмом", 0, "Якутск", 13);

        assertEquals(0, parcel.calculateDeliveryCost());
    }
}
