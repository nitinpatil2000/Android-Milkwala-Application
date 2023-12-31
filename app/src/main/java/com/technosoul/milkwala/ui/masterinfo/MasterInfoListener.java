package com.technosoul.milkwala.ui.masterinfo;

public interface MasterInfoListener {
    void onSupplierClick();
    void onProductClick();
    void onDeliveryPersonClick();
    void onCustomerClick();
    void onRouterClick();

    void addNewSupplier();
    void addNewProduct(int id);
    void addNewDeliveryPerson();
    void addNewCustomer(int id);
    void addNewRoute(int id);
    void addNewProductForCustomer(int id);


    void onBackToPreviousScreen();
    void setActionBarTitle(String actionBarTitle);
}
