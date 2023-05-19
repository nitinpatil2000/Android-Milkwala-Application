package com.technosoul.milkwala.ui.masterinfo;

public interface MasterInfoListener {
    void onSupplierClick();
    void onProductClick();
    void onDeliveryPersonClick();
    void onCustomerClick();
    void addNewSupplier();
    void addNewProduct(int id);
    void addNewDeliveryPerson();
    void addNewCustomer();
    void onBackToPreviousScreen();
    void setActionBarTitle(String actionBarTitle);
}
