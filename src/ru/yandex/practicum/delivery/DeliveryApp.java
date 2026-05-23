package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {
    private static final int DEFAULT_BOX_MAX_WEIGHT = 100;

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<Trackable> trackableParcels = new ArrayList<>();
    private static final ParcelBox<StandardParcel> standardParcelBox = new ParcelBox<>(DEFAULT_BOX_MAX_WEIGHT);
    private static final ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(DEFAULT_BOX_MAX_WEIGHT);
    private static final ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<>(DEFAULT_BOX_MAX_WEIGHT);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt();

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportStatuses();
                    break;
                case 5:
                    showBoxContents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 - Добавить посылку");
        System.out.println("2 - Отправить все посылки");
        System.out.println("3 - Посчитать стоимость доставки");
        System.out.println("4 - Обновить местоположение отслеживаемых отправлений");
        System.out.println("5 - Показать содержимое коробки");
        System.out.println("0 - Завершить");
    }

    private static void addParcel() {
        System.out.println("Выберите тип посылки:");
        System.out.println("1 - Стандартная");
        System.out.println("2 - Хрупкая");
        System.out.println("3 - Скоропортящаяся");

        int parcelType = readInt();
        System.out.println("Введите описание:");
        String description = scanner.nextLine();
        System.out.println("Введите вес:");
        int weight = readInt();
        System.out.println("Введите адрес доставки:");
        String deliveryAddress = scanner.nextLine();
        System.out.println("Введите день отправки:");
        int sendDay = readInt();

        switch (parcelType) {
            case 1:
                StandardParcel standardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                if (standardParcelBox.addParcel(standardParcel)) {
                    allParcels.add(standardParcel);
                }
                break;
            case 2:
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                if (fragileParcelBox.addParcel(fragileParcel)) {
                    allParcels.add(fragileParcel);
                    trackableParcels.add(fragileParcel);
                }
                break;
            case 3:
                System.out.println("Введите срок хранения в днях:");
                int timeToLive = readInt();
                PerishableParcel perishableParcel = new PerishableParcel(
                        description,
                        weight,
                        deliveryAddress,
                        sendDay,
                        timeToLive
                );
                if (perishableParcelBox.addParcel(perishableParcel)) {
                    allParcels.add(perishableParcel);
                }
                break;
            default:
                System.out.println("Неверный тип посылки.");
        }
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int totalCost = 0;
        for (Parcel parcel : allParcels) {
            totalCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость доставки: " + totalCost + ".");
    }

    private static void reportStatuses() {
        System.out.println("Введите новое местоположение:");
        String newLocation = scanner.nextLine();

        for (Trackable trackableParcel : trackableParcels) {
            trackableParcel.reportStatus(newLocation);
        }
    }

    private static void showBoxContents() {
        System.out.println("Выберите тип коробки:");
        System.out.println("1 - Стандартные посылки");
        System.out.println("2 - Хрупкие посылки");
        System.out.println("3 - Скоропортящиеся посылки");

        int boxType = readInt();
        switch (boxType) {
            case 1:
                printBoxContents(standardParcelBox.getAllParcels());
                break;
            case 2:
                printBoxContents(fragileParcelBox.getAllParcels());
                break;
            case 3:
                printBoxContents(perishableParcelBox.getAllParcels());
                break;
            default:
                System.out.println("Неверный тип коробки.");
        }
    }

    private static void printBoxContents(List<? extends Parcel> parcels) {
        if (parcels.isEmpty()) {
            System.out.println("Коробка пуста.");
            return;
        }

        for (Parcel parcel : parcels) {
            System.out.println(parcel.getDescription());
        }
    }

    private static int readInt() {
        return Integer.parseInt(scanner.nextLine());
    }
}
