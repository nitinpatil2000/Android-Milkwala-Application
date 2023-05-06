package com.technosoul.milkwala.ui.masterinfo;

public interface MasterInfoListener {
    void onSupplierClick();
    void onProductClick();
    void onDeliveryBoyClick();
    void onCustomerClick();
    void addNewSupplier();
    void addNewProduct();
    void addNewDeliveryBoy();
    void addNewCustomer();
    void onBackToPreviousScreen();
}
