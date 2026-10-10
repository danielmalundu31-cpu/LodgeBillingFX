package com.lodgebilling;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DashboardView {

    private final Main main;
    private final String username;

    private BorderPane root;

    private TextField guestName;
    private TextField phoneNumber;
    private ComboBox<String> roomType;
    private TextField nights;
    private TextField extras;
    private TextField amountPaid;
    private TextArea notes;

    private TableView<Bill> billsTable;

    public DashboardView(Main main, String username) {
        this.main = main;
        this.username = username;

        createLayout();
    }

    public Parent getView() {
        return root;
    }

    // ==========================================================
    // MAIN LAYOUT
    // ==========================================================

    private void createLayout() {

        root = new BorderPane();

        root.setTop(createTopBar());

        root.setCenter(createDashboardPage());
    }

    // ==========================================================
    // TOP NAVIGATION
    // ==========================================================

    private HBox createTopBar() {

        HBox bar = new HBox(12);

        bar.setAlignment(Pos.CENTER_LEFT);
        bar.setPadding(new Insets(15, 25, 15, 25));

        bar.getStyleClass().add("top-bar");

        Label logo = new Label("🏨  LODGE BILLING");

        logo.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        23
                )
        );

        logo.getStyleClass().add("logo");

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button dashboardButton =
                new Button("Dashboard");

        Button newGuestButton =
                new Button("New Guest");

        Button reservationButton =
                new Button("Reservations");

        Button receiptButton =
                new Button("Receipt");

        Button helpButton =
                new Button("Help");

        Button logoutButton =
                new Button("Logout");

        dashboardButton.getStyleClass().add("nav-button");
        newGuestButton.getStyleClass().add("nav-button");
        reservationButton.getStyleClass().add("nav-button");
        receiptButton.getStyleClass().add("nav-button");
        helpButton.getStyleClass().add("nav-button");
        logoutButton.getStyleClass().add("logout-button");

        Label userLabel =
                new Label("👤 " + username);

        userLabel.getStyleClass().add("user-label");

        dashboardButton.setOnAction(
                e -> root.setCenter(createDashboardPage())
        );

        newGuestButton.setOnAction(
                e -> newGuest()
        );

        reservationButton.setOnAction(
                e -> root.setCenter(createReservationPage())
        );

        receiptButton.setOnAction(
                e -> showReceipt()
        );

        helpButton.setOnAction(
                e -> showHelp()
        );

        logoutButton.setOnAction(e -> logout());

        bar.getChildren().addAll(
                logo,
                spacer,
                dashboardButton,
                newGuestButton,
                reservationButton,
                receiptButton,
                helpButton,
                userLabel,
                logoutButton
        );

        return bar;
    }

    // ==========================================================
    // DASHBOARD
    // ==========================================================

    private VBox createDashboardPage() {

        VBox page = new VBox(20);

        page.setPadding(
                new Insets(25)
        );

        page.getStyleClass().add("page");

        Label title =
                new Label("Guest Billing Dashboard");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.getStyleClass().add("page-title");

        Label subtitle =
                new Label(
                        "Create guest bills, record payments and generate receipts."
                );

        subtitle.getStyleClass().add(
                "page-subtitle"
        );

        HBox summaryCards =
                createSummaryCards();

        GridPane billingForm =
                createBillingForm();

        HBox buttons =
                createBillingButtons();

        page.getChildren().addAll(
                title,
                subtitle,
                summaryCards,
                billingForm,
                buttons
        );

        return page;
    }

    // ==========================================================
    // SUMMARY CARDS
    // ==========================================================

    private HBox createSummaryCards() {

        HBox cards = new HBox(15);

        cards.getChildren().addAll(
                createSummaryCard(
                        "TOTAL BILLS",
                        String.valueOf(
                                LodgeData.bills.size()
                        ),
                        "Saved bills"
                ),

                createSummaryCard(
                        "RESERVATIONS",
                        String.valueOf(
                                LodgeData.reservations.size()
                        ),
                        "Current reservations"
                ),

                createSummaryCard(
                        "ROOM TYPES",
                        "3",
                        "Standard • Deluxe • Family"
                )
        );

        return cards;
    }

    private VBox createSummaryCard(
            String title,
            String value,
            String description
    ) {

        VBox card = new VBox(6);

        card.setPadding(
                new Insets(18)
        );

        card.setPrefWidth(260);

        card.getStyleClass().add(
                "summary-card"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.getStyleClass().add(
                "summary-title"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        27
                )
        );

        valueLabel.getStyleClass().add(
                "summary-value"
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.getStyleClass().add(
                "summary-description"
        );

        card.getChildren().addAll(
                titleLabel,
                valueLabel,
                descriptionLabel
        );

        return card;
    }

    // ==========================================================
    // BILLING FORM
    // ==========================================================

    private GridPane createBillingForm() {

        GridPane form = new GridPane();

        form.setHgap(18);
        form.setVgap(16);

        form.setPadding(
                new Insets(25)
        );

        form.getStyleClass().add("card");

        Label section =
                new Label("Guest & Payment Information");

        section.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        section.getStyleClass().add(
                "section-title"
        );

        form.add(
                section,
                0,
                0,
                4,
                1
        );

        guestName = new TextField();
        guestName.setPromptText(
                "Enter guest full name"
        );

        phoneNumber = new TextField();
        phoneNumber.setPromptText(
                "Enter phone number"
        );

        roomType = new ComboBox<>();

        roomType.setItems(
                FXCollections.observableArrayList(
                        "Standard",
                        "Deluxe",
                        "Family"
                )
        );

        roomType.setValue(
                "Standard"
        );

        nights = new TextField();
        nights.setPromptText(
                "Number of nights"
        );

        extras = new TextField("0");

        amountPaid = new TextField("0");

        notes = new TextArea();

        notes.setPromptText(
                "Special requests or additional notes"
        );

        notes.setPrefRowCount(3);

        addField(
                form,
                "Guest Name",
                guestName,
                1,
                0
        );

        addField(
                form,
                "Phone Number",
                phoneNumber,
                1,
                2
        );

        addField(
                form,
                "Room Type",
                roomType,
                2,
                0
        );

        addField(
                form,
                "Number of Nights",
                nights,
                2,
                2
        );

        addField(
                form,
                "Extra Charges (K)",
                extras,
                3,
                0
        );

        addField(
                form,
                "Amount Paid (K)",
                amountPaid,
                3,
                2
        );

        Label notesLabel =
                new Label("Notes");

        notesLabel.getStyleClass().add(
                "field-label"
        );

        form.add(
                notesLabel,
                0,
                4
        );

        form.add(
                notes,
                1,
                4,
                3,
                1
        );

        return form;
    }

    private void addField(
            GridPane grid,
            String labelText,
            Control control,
            int row,
            int column
    ) {

        Label label =
                new Label(labelText);

        label.getStyleClass().add(
                "field-label"
        );

        control.setPrefWidth(280);

        grid.add(
                label,
                column,
                row
        );

        grid.add(
                control,
                column + 1,
                row
        );
    }

    // ==========================================================
    // BILLING BUTTONS
    // ==========================================================

    private HBox createBillingButtons() {

        HBox buttons = new HBox(12);

        Button calculate =
                new Button("Calculate Bill");

        Button save =
                new Button("Save Bill");

        Button newGuest =
                new Button("New Guest");

        Button receipt =
                new Button("View Receipt");

        calculate.getStyleClass().add(
                "secondary-button"
        );

        save.getStyleClass().add(
                "primary-button"
        );

        newGuest.getStyleClass().add(
                "outline-button"
        );

        receipt.getStyleClass().add(
                "receipt-button"
        );

        calculate.setOnAction(
                e -> calculateBill()
        );

        save.setOnAction(
                e -> saveBill()
        );

        newGuest.setOnAction(
                e -> newGuest()
        );

        receipt.setOnAction(
                e -> showReceipt()
        );

        buttons.getChildren().addAll(
                calculate,
                save,
                newGuest,
                receipt
        );

        return buttons;
    }

    // ==========================================================
    // CALCULATE BILL
    // ==========================================================

    private double calculateTotal() {

        if (guestName.getText().isBlank()) {

            throw new IllegalArgumentException(
                    "Please enter the guest name."
            );
        }

        int numberOfNights;

        try {

            numberOfNights =
                    Integer.parseInt(
                            nights.getText().trim()
                    );

        } catch (NumberFormatException e) {

            throw new IllegalArgumentException(
                    "Number of nights must be a valid number."
            );
        }

        if (numberOfNights <= 0) {

            throw new IllegalArgumentException(
                    "Number of nights must be greater than zero."
            );
        }

        double extraAmount;

        try {

            extraAmount =
                    Double.parseDouble(
                            extras.getText().trim()
                    );

        } catch (NumberFormatException e) {

            throw new IllegalArgumentException(
                    "Extra charges must be a valid amount."
            );
        }

        double roomPrice =
                LodgeData.getRoomPrice(
                        roomType.getValue()
                );

        double roomCharge =
                roomPrice * numberOfNights;

        double discount = 0;

        if (numberOfNights >= 5) {

            discount =
                    roomCharge * 0.05;
        }

        return roomCharge
                - discount
                + extraAmount;
    }

    private void calculateBill() {

        try {

            double total =
                    calculateTotal();

            double paid =
                    Double.parseDouble(
                            amountPaid.getText().trim()
                    );

            double balance =
                    total - paid;

            showInfo(
                    "Bill Calculation",
                    String.format(
                            "Guest: %s%n%n" +
                                    "Room: %s%n" +
                                    "Nights: %s%n%n" +
                                    "Total: K %.2f%n" +
                                    "Paid: K %.2f%n" +
                                    "Balance: K %.2f",
                            guestName.getText(),
                            roomType.getValue(),
                            nights.getText(),
                            total,
                            paid,
                            balance
                    )
            );

        } catch (Exception e) {

            showError(
                    e.getMessage()
            );
        }
    }

    // ==========================================================
    // SAVE BILL
    // ==========================================================

    private void saveBill() {

        try {

            double total =
                    calculateTotal();

            double paid =
                    Double.parseDouble(
                            amountPaid.getText().trim()
                    );

            if (paid < 0) {

                throw new IllegalArgumentException(
                        "Payment cannot be negative."
                );
            }

            int nightCount =
                    Integer.parseInt(
                            nights.getText().trim()
                    );

            Bill bill =
                    new Bill(
                            LodgeData.nextBillNumber(),
                            guestName.getText().trim(),
                            roomType.getValue(),
                            nightCount,
                            total,
                            paid
                    );

            LodgeData.bills.add(bill);

            showInfo(
                    "Bill Saved",
                    "Bill " +
                            bill.getBillNumber() +
                            " has been saved successfully.\n\n" +
                            String.format(
                                    "Balance: K %.2f",
                                    bill.getBalance()
                            )
            );

        } catch (Exception e) {

            showError(
                    e.getMessage()
            );
        }
    }

    // ==========================================================
    // NEW GUEST
    // ==========================================================

    private void newGuest() {

        if (guestName != null) {

            guestName.clear();
            phoneNumber.clear();

            roomType.setValue(
                    "Standard"
            );

            nights.clear();

            extras.setText("0");

            amountPaid.setText("0");

            notes.clear();
        }

        showInfo(
                "New Guest",
                "The billing form is ready for a new guest."
        );
    }

    // ==========================================================
    // RECEIPT
    // ==========================================================

    private void showReceipt() {

        Bill bill = null;

        if (billsTable != null) {

            bill =
                    billsTable
                            .getSelectionModel()
                            .getSelectedItem();
        }

        if (bill == null && !LodgeData.bills.isEmpty()) {

            bill =
                    LodgeData.bills.get(
                            LodgeData.bills.size() - 1
                    );
        }

        if (bill == null) {

            showError(
                    "There is no saved bill available for a receipt."
            );

            return;
        }

        showReceiptWindow(bill);
    }

    private void showReceiptWindow(Bill bill) {

        Stage receiptStage =
                new Stage();

        receiptStage.initModality(
                Modality.APPLICATION_MODAL
        );

        receiptStage.setTitle(
                "Receipt - " +
                        bill.getBillNumber()
        );

        VBox receipt =
                new VBox(15);

        receipt.setPadding(
                new Insets(30)
        );

        receipt.setPrefWidth(420);

        receipt.getStyleClass().add(
                "receipt-paper"
        );

        Label lodgeName =
                new Label("🏨 LODGE BILLING");

        lodgeName.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );

        lodgeName.getStyleClass().add(
                "receipt-title"
        );

        Label receiptTitle =
                new Label("PAYMENT RECEIPT");

        receiptTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        Label line =
                new Label(
                        "────────────────────────"
                );

        Label details =
                new Label(
                        String.format(
                                "Receipt No: %s%n%n" +
                                        "Guest: %s%n" +
                                        "Room: %s%n" +
                                        "Nights: %d%n%n" +
                                        "Total Amount: K %.2f%n" +
                                        "Amount Paid:  K %.2f%n" +
                                        "Balance:      K %.2f",
                                bill.getBillNumber(),
                                bill.getGuest(),
                                bill.getRoom(),
                                bill.getNights(),
                                bill.getTotal(),
                                bill.getPaid(),
                                bill.getBalance()
                        )
                );

        details.getStyleClass().add(
                "receipt-details"
        );

        Label thanks =
                new Label(
                        "Thank you for staying with us."
                );

        thanks.getStyleClass().add(
                "receipt-thanks"
        );

        Button close =
                new Button("Close Receipt");

        close.getStyleClass().add(
                "primary-button"
        );

        close.setOnAction(
                e -> receiptStage.close()
        );

        receipt.setAlignment(
                Pos.CENTER
        );

        receipt.getChildren().addAll(
                lodgeName,
                receiptTitle,
                line,
                details,
                thanks,
                close
        );

        Scene scene =
                new Scene(
                        receipt,
                        480,
                        550
                );

        scene.getStylesheets().add(
                getClass()
                        .getResource(
                                "/com/lodgebilling/style.css"
                        )
                        .toExternalForm()
        );

        receiptStage.setScene(scene);

        receiptStage.showAndWait();
    }

    // ==========================================================
    // SAVED BILLS
    // ==========================================================

    private VBox createSavedBillsPage() {

        VBox page =
                new VBox(20);

        page.setPadding(
                new Insets(25)
        );

        page.getStyleClass().add(
                "page"
        );

        Label title =
                new Label("Saved Bills");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28
                )
        );

        billsTable =
                new TableView<>();

        TableColumn<Bill, String> billCol =
                new TableColumn<>("Bill No.");

        billCol.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getBillNumber()
                        )
        );

        TableColumn<Bill, String> guestCol =
                new TableColumn<>("Guest");

        guestCol.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getGuest()
                        )
        );

        TableColumn<Bill, String> roomCol =
                new TableColumn<>("Room");

        roomCol.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue()
                                        .getRoom()
                        )
        );

        TableColumn<Bill, String> nightsCol =
                new TableColumn<>("Nights");

        nightsCol.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.valueOf(
                                        data.getValue()
                                                .getNights()
                                )
                        )
        );

        TableColumn<Bill, String> totalCol =
                new TableColumn<>("Total");

        totalCol.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.format(
                                        "K %.2f",
                                        data.getValue()
                                                .getTotal()
                                )
                        )
        );

        TableColumn<Bill, String> paidCol =
                new TableColumn<>("Paid");

        paidCol.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.format(
                                        "K %.2f",
                                        data.getValue()
                                                .getPaid()
                                )
                        )
        );

        TableColumn<Bill, String> balanceCol =
                new TableColumn<>("Balance");

        balanceCol.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                String.format(
                                        "K %.2f",
                                        data.getValue()
                                                .getBalance()
                                )
                        )
        );

        billsTable.getColumns().addAll(
                billCol,
                guestCol,
                roomCol,
                nightsCol,
                totalCol,
                paidCol,
                balanceCol
        );

        billsTable.setItems(
                LodgeData.bills
        );

        VBox.setVgrow(
                billsTable,
                Priority.ALWAYS
        );

        Button receipt =
                new Button("View Receipt");

        Button delete =
                new Button(
                        "Delete Selected Bill"
                );

        receipt.getStyleClass().add(
                "receipt-button"
        );

        delete.getStyleClass().add(
                "danger-button"
        );

        receipt.setOnAction(
                e -> showReceipt()
        );

        delete.setOnAction(e -> {

            Bill selected =
                    billsTable
                            .getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showError(
                        "Please select a bill first."
                );

                return;
            }

            Alert confirmation =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            confirmation.setTitle(
                    "Delete Bill"
            );

            confirmation.setHeaderText(
                    "Delete " +
                            selected.getBillNumber() +
                            "?"
            );

            confirmation.setContentText(
                    "This action cannot be undone."
            );

            confirmation.showAndWait()
                    .ifPresent(response -> {

                        if (response ==
                                ButtonType.OK) {

                            LodgeData.bills.remove(
                                    selected
                            );
                        }
                    });
        });

        HBox actions =
                new HBox(
                        12,
                        receipt,
                        delete
                );

        page.getChildren().addAll(
                title,
                billsTable,
                actions
        );

        return page;
    }

    // ==========================================================
    // RESERVATIONS
    // ==========================================================

    private VBox createReservationPage() {

        VBox page =
                new VBox(20);

        page.setPadding(
                new Insets(25)
        );

        page.getStyleClass().add(
                "page"
        );

        Label title =
                new Label("Room Reservations");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28
                )
        );

        GridPane form =
                new GridPane();

        form.setHgap(15);
        form.setVgap(15);

        form.setPadding(
                new Insets(25)
        );

        form.getStyleClass().add(
                "card"
        );

        TextField name =
                new TextField();

        name.setPromptText(
                "Guest name"
        );

        TextField phone =
                new TextField();

        phone.setPromptText(
                "Phone number"
        );

        ComboBox<String> room =
                new ComboBox<>();

        room.getItems().addAll(
                "Standard",
                "Deluxe",
                "Family"
        );

        room.setValue(
                "Standard"
        );

        DatePicker checkIn =
                new DatePicker();

        DatePicker checkOut =
                new DatePicker();

        Spinner<Integer> guests =
                new Spinner<>(
                        1,
                        20,
                        1
                );

        TextArea reservationNotes =
                new TextArea();

        reservationNotes.setPromptText(
                "Reservation notes"
        );

        addField(
                form,
                "Guest Name",
                name,
                0,
                0
        );

        addField(
                form,
                "Phone",
                phone,
                1,
                0
        );

        addField(
                form,
                "Room",
                room,
                2,
                0
        );

        addField(
                form,
                "Check-in",
                checkIn,
                3,
                0
        );

        addField(
                form,
                "Check-out",
                checkOut,
                4,
                0
        );

        addField(
                form,
                "Guests",
                guests,
                5,
                0
        );

        addField(
                form,
                "Notes",
                reservationNotes,
                6,
                0
        );

        Button reserve =
                new Button(
                        "Create Reservation"
                );

        reserve.getStyleClass().add(
                "primary-button"
        );

        reserve.setOnAction(e -> {

            if (name.getText().isBlank()) {

                showError(
                        "Please enter the guest name."
                );

                return;
            }

            if (checkIn.getValue() == null ||
                    checkOut.getValue() == null) {

                showError(
                        "Please select both dates."
                );

                return;
            }

            if (checkOut.getValue()
                    .isBefore(
                            checkIn.getValue()
                    )) {

                showError(
                        "Check-out cannot be before check-in."
                );

                return;
            }

            Reservation reservation =
                    new Reservation(
                            LodgeData
                                    .nextReservationNumber(),
                            name.getText().trim(),
                            phone.getText().trim(),
                            room.getValue(),
                            checkIn.getValue()
                                    .toString(),
                            checkOut.getValue()
                                    .toString(),
                            guests.getValue(),
                            reservationNotes
                                    .getText()
                    );

            LodgeData.reservations.add(
                    reservation
            );

            showInfo(
                    "Reservation Created",
                    "Reservation " +
                            reservation
                                    .getReservationId() +
                            " created successfully."
            );

            name.clear();
            phone.clear();
            reservationNotes.clear();
        });

        page.getChildren().addAll(
                title,
                form,
                reserve
        );

        return page;
    }

    // ==========================================================
    // HELP
    // ==========================================================

    private void showHelp() {

        Alert help =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        help.setTitle(
                "Lodge Billing - Help"
        );

        help.setHeaderText(
                "How to use the Lodge Billing System"
        );

        help.setContentText(
                "1. NEW GUEST\n" +
                        "Click New Guest to clear the billing form.\n\n" +

                        "2. ENTER GUEST DETAILS\n" +
                        "Enter the guest's name and phone number.\n\n" +

                        "3. SELECT ROOM\n" +
                        "Choose Standard, Deluxe or Family.\n\n" +

                        "4. ENTER NIGHTS\n" +
                        "Enter the number of nights the guest will stay.\n\n" +

                        "5. ADD EXTRA CHARGES\n" +
                        "Enter any additional charges such as meals or services.\n\n" +

                        "6. ENTER PAYMENT\n" +
                        "Enter the amount the guest has paid.\n\n" +

                        "7. CALCULATE BILL\n" +
                        "Click Calculate Bill to see the total and balance.\n\n" +

                        "8. SAVE BILL\n" +
                        "Click Save Bill to store the transaction.\n\n" +

                        "9. RECEIPT\n" +
                        "After saving a bill, click Receipt to view the payment receipt.\n\n" +

                        "10. DELETE BILL\n" +
                        "Go to Saved Bills, select a bill and click Delete Selected Bill.\n\n" +

                        "11. RESERVATIONS\n" +
                        "Use Reservations to create future room bookings.\n\n" +

                        "IMPORTANT\n" +
                        "Bills are currently stored only while the application is running."
        );

        help.getDialogPane()
                .getStyleClass()
                .add("help-dialog");

        help.showAndWait();
    }

    // ==========================================================
    // LOGOUT
    // ==========================================================

    private void logout() {

        Alert alert =
                new Alert(
                        Alert.AlertType.CONFIRMATION,
                        "Are you sure you want to logout?",
                        ButtonType.YES,
                        ButtonType.NO
                );

        alert.setTitle(
                "Logout"
        );

        alert.setHeaderText(
                null
        );

        alert.showAndWait()
                .ifPresent(response -> {

                    if (response ==
                            ButtonType.YES) {

                        main.showLogin();
                    }
                });
    }

    // ==========================================================
    // ALERTS
    // ==========================================================

    private void showError(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(
                "Error"
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }

    private void showInfo(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}
