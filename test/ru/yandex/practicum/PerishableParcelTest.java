package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.PerishableParcel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PerishableParcelTest {
    @Test
    void shouldNotBeExpiredBeforeTimeToLiveEnds() {
        PerishableParcel parcel = new PerishableParcel("творог с курагой", 2, "Владивосток", 5, 3);

        assertFalse(parcel.isExpired(7));
    }

    @Test
    void shouldNotBeExpiredOnLastValidDay() {
        PerishableParcel parcel = new PerishableParcel("говядина фермерская", 15, "Тикси", 10, 2);

        assertFalse(parcel.isExpired(12));
    }

    @Test
    void shouldBeExpiredAfterTimeToLiveEnds() {
        PerishableParcel parcel = new PerishableParcel("рыба морская", 10, "Иркутск", 8, 1);

        assertTrue(parcel.isExpired(10));
    }
}
